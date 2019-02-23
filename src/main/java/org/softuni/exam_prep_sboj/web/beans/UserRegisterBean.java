package org.softuni.exam_prep_sboj.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam_prep_sboj.domain.models.binding.UserRegisterBindingModel;
import org.softuni.exam_prep_sboj.domain.models.service.UserServiceModel;
import org.softuni.exam_prep_sboj.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel model;
    
    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getModel() {
        return model;
    }

    public void setModel(UserRegisterBindingModel model) {
        this.model = model;
    }
    
    public void register() throws IOException {
        if(!this.model.getPassword().equals(model.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords don't match!");
        }

        if (!this.userService.userRegister(this.modelMapper.map(this.model, UserServiceModel.class))) {
            throw new IllegalArgumentException("Something went wrong!");
        }
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
    }
}

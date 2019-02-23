package org.softuni.exam_prep_sboj.web.beans;

import jdk.jfr.Name;
import org.modelmapper.ModelMapper;
import org.softuni.exam_prep_sboj.domain.models.binding.UserLoginBindingModel;
import org.softuni.exam_prep_sboj.domain.models.service.UserServiceModel;
import org.softuni.exam_prep_sboj.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {
    private UserLoginBindingModel model;
    
    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.model = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getModel() {
        return model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }
    
    public void login() throws IOException {
        UserServiceModel userServiceModel = this.userService
                .login(this.modelMapper.map(this.model, UserServiceModel.class));
        
        if(userServiceModel == null){
            throw new IllegalArgumentException("Something went wrong!");
        }

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        session.setAttribute("username", userServiceModel.getUsername());
        

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");

    }
}

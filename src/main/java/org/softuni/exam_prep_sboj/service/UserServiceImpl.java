package org.softuni.exam_prep_sboj.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuni.exam_prep_sboj.domain.entities.User;
import org.softuni.exam_prep_sboj.domain.models.service.UserServiceModel;
import org.softuni.exam_prep_sboj.repository.UserRepository;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean userRegister(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        
        return this.userRepository.save(this.modelMapper.map(userServiceModel, User.class)) != null;
    }

    @Override
    public UserServiceModel login(UserServiceModel model) {
        User user = this.userRepository.findByUsername(model.getUsername());
        
        if(!DigestUtils.sha256Hex(model.getPassword()).equals(user.getPassword()) || user == null)  {
            return null;
        }
        
        return this.modelMapper.map(user, UserServiceModel.class);
    }
}

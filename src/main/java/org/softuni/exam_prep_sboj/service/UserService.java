package org.softuni.exam_prep_sboj.service;

import org.softuni.exam_prep_sboj.domain.models.service.UserServiceModel;

public interface UserService {

     boolean userRegister(UserServiceModel userServiceModel);
     
     UserServiceModel login(UserServiceModel model);
}

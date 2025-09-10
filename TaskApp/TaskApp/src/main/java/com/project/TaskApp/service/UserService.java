package com.project.TaskApp.service;


import com.project.TaskApp.dto.Response;
import com.project.TaskApp.dto.UserRequest;
import com.project.TaskApp.entity.AppUser;

public interface UserService {

    Response<?> signUp(UserRequest userRequest);
    Response<?> login(UserRequest userRequest);
    AppUser getCurrentLoggedInUser();

}

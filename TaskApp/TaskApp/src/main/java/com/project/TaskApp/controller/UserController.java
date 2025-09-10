package com.project.TaskApp.controller;

import com.project.TaskApp.dto.Response;
import com.project.TaskApp.dto.UserRequest;
import com.project.TaskApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//auto convert to json/ build rest api // class behave as a controll to handle req
@RequestMapping("/api/auth")
public class UserController {
    @Autowired//allow servise method
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response<?>> signUp(@Valid//user req valid trigger
                                                  @RequestBody //req body assign to userreq
                                                  UserRequest userRequest){
        return ResponseEntity.ok(userService.signUp(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<?>> login(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.login(userRequest));
    }
}




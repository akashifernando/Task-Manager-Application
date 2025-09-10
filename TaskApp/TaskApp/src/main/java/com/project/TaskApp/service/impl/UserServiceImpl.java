package com.project.TaskApp.service.impl;

import com.project.TaskApp.dto.Response;
import com.project.TaskApp.dto.UserRequest;
import com.project.TaskApp.entity.AppUser;
import com.project.TaskApp.enums.Role;
import com.project.TaskApp.exceptions.BadRequestException;
import com.project.TaskApp.exceptions.NotFoundException;
import com.project.TaskApp.repo.UserRepository;
import com.project.TaskApp.security.JwtUtils;
import com.project.TaskApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired// inject dependency
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public Response<?> signUp(UserRequest userRequest) {
        log.info("Inside signUp()");
        Optional<AppUser> existingUser = userRepository.findByUsername(userRequest.getUsername());

        if (existingUser.isPresent()){
            throw new BadRequestException("Username already taken");
        }

        AppUser user = new AppUser();
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(Role.USER);
        //defult role
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//encode pw
        //save user
        userRepository.save(user);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("user registered sucessfully")
                .build();

    }

    @Override
    public Response<?> login(UserRequest userRequest) {

        log.info("Inside login()");


        AppUser user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(()-> new NotFoundException("User Not Found"));
        boolean match = passwordEncoder.matches(userRequest.getPassword().trim(), user.getPassword());
        log.info("Attempting login for username: {}", userRequest.getUsername());
        log.info("Password match result: {}", match);

        if (!match) {
            throw new BadRequestException("Invalid password");
        }

        String token = jwtUtils.generateToken(user.getUsername());
//token generate
        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("login successful")
                .data(token)
                .build();
    }

    @Override
    public AppUser getCurrentLoggedInUser() {
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
//get username auth user
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException("User not found"));

    }
}

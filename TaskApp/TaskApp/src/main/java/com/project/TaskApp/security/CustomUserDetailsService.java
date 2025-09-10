package com.project.TaskApp.security;



import com.project.TaskApp.entity.AppUser;
import com.project.TaskApp.exceptions.NotFoundException;
import com.project.TaskApp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//load user details from  database
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService //load user data during login
{
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException("User not found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }

}




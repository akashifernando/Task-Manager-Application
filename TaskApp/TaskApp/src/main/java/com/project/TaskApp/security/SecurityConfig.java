//package com.project.TaskApp.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//    @EnableWebSecurity
//    public class SecurityConfig {
//
//       @Bean
//       public PasswordEncoder passwordEncoder(){
//           return new BCryptPasswordEncoder();
//       }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
////                .formLogin(form -> form
////                        // Using default login page provided by Spring Security on '/login'
////                        .defaultSuccessUrl("/home", true)
////                        .permitAll()
////                )
//                .logout(logout -> logout
//                        .permitAll()
//                )
//                .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity in this basic setup
//
//        return http.build();
//    }
//}
//
//

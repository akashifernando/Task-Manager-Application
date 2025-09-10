package com.project.TaskApp.repo;
import com.project.TaskApp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//Provides CRUD save,find custom lookups for AppUser entities.
public interface UserRepository extends JpaRepository //Spring Data JPA auto-generate impl at runtime
        //abstract data access layer
        <AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
//handle not found case explicitly,avoiding null-pointer exceptions.
}
package com.devstack.ecom.Upscale.repository;

import com.devstack.ecom.Upscale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}

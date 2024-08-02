package com.devstack.ecom.Upscale.repository;

import com.devstack.ecom.Upscale.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface UserRoleRepo extends JpaRepository<UserRole, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM user_role WHERE role_name=?1")
    Optional<UserRole> findUserRoleByRoleName(String role);

    @Query(nativeQuery = true, value = "SELECT * FROM user_role")
    List<UserRole> findAll();

}

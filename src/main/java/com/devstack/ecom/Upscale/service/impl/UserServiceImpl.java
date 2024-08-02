package com.devstack.ecom.Upscale.service.impl;

import com.devstack.ecom.Upscale.dto.request.RequestUserDto;
import com.devstack.ecom.Upscale.entity.User;
import com.devstack.ecom.Upscale.entity.UserRole;
import com.devstack.ecom.Upscale.exception.DuplicateEntryException;
import com.devstack.ecom.Upscale.exception.EntryNotFoundException;
import com.devstack.ecom.Upscale.repository.UserRepo;
import com.devstack.ecom.Upscale.repository.UserRoleRepo;
import com.devstack.ecom.Upscale.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(RequestUserDto dto, String roleType) {
        Optional<User> byEmail = userRepo.findByEmail(dto.getEmail());
        if (byEmail.isPresent()){
            throw new DuplicateEntryException("user already exists");
        }

        Optional<UserRole> selectedRole = userRoleRepo.findUserRoleByRoleName(roleType);
        if (selectedRole.isEmpty()) {
            throw new EntryNotFoundException("Role not found");
        }

        HashSet<UserRole> objects = new HashSet<>();
        objects.add(selectedRole.get());

        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .email(dto.getEmail())
                .displayName(dto.getDisplayName())
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .roles(objects)
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        userRepo.save(user);
    }

    @Override
    public void initializeAdmin() {
        Optional<User> byEmail = userRepo.findByEmail("admin@gmail.com");
        if (byEmail.isEmpty()) {
            Optional<UserRole> selectedRole = userRoleRepo.findUserRoleByRoleName("ADMIN");
            if (selectedRole.isEmpty()) {
                throw new EntryNotFoundException("Role not found");
            }
            HashSet<UserRole> objects = new HashSet<>();
            objects.add(selectedRole.get());
            User user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .email("admin@gmail.com")
                    .displayName("Abc Xyz")
                    .isEnabled(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonExpired(true)
                    .roles(objects)
                    .password(passwordEncoder.encode("1234"))
                    .build();
            userRepo.save(user);
        }
    }
}

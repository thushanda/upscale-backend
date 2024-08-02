package com.devstack.ecom.Upscale.service.impl;

import com.devstack.ecom.Upscale.dto.request.RequestUserRoleDto;
import com.devstack.ecom.Upscale.entity.UserRole;
import com.devstack.ecom.Upscale.repository.UserRoleRepo;
import com.devstack.ecom.Upscale.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;

    @Override
    public void create(RequestUserRoleDto dto) {
        UserRole build = UserRole.builder()
                .roleId(UUID.randomUUID().toString())
                .roleName(dto.getRoleName())
                .description(dto.getDescription())
                .build();
        userRoleRepo.save(build);
    }

    @Override
    public void initializerUserRoles() {
        List<UserRole> list = userRoleRepo.findAll();
        if (list.isEmpty()) {
            userRoleRepo.saveAll(List.of(
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .description("Description")
                            .roleName("USER")
                            .build(),
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .description("Description")
                            .roleName("CUSTOMER")
                            .build(),
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .description("Description")
                            .roleName("ADMIN")
                            .build(),
                    UserRole.builder()
                            .roleId(UUID.randomUUID().toString())
                            .description("Description")
                            .roleName("MANAGER")
                            .build()
            ));

        }
    }
}

package com.devstack.ecom.Upscale.service;

import com.devstack.ecom.Upscale.dto.request.RequestUserRoleDto;

public interface UserRoleService {
    void create(RequestUserRoleDto dto);
    void initializerUserRoles();
}

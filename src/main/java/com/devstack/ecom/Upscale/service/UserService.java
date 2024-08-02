package com.devstack.ecom.Upscale.service;

import com.devstack.ecom.Upscale.dto.request.RequestUserDto;

public interface UserService {
    void create(RequestUserDto dto, String roleType);
    void initializeAdmin();
}

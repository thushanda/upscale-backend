package com.devstack.ecom.Upscale.api;

import com.devstack.ecom.Upscale.dto.request.RequestUserDto;
import com.devstack.ecom.Upscale.dto.request.RequestUserRoleDto;
import com.devstack.ecom.Upscale.service.UserRoleService;
import com.devstack.ecom.Upscale.service.UserService;
import com.devstack.ecom.Upscale.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user_roles")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;
    @PostMapping("/create")
    public ResponseEntity<StandardResponse> createRole(@RequestBody RequestUserRoleDto dto){
        userRoleService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "User role was created!...",
                        null
                ), HttpStatus.CREATED
        );
    }
}

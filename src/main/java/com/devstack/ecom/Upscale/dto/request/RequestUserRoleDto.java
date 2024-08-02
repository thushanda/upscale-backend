package com.devstack.ecom.Upscale.dto.request;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RequestUserRoleDto {
    @Column(name = "role_name", length = 100, unique = true)
    private String roleName;

    @Column(name = "description", length = 80)
    private String description;
}

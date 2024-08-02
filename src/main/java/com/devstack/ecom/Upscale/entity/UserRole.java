package com.devstack.ecom.Upscale.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @Column(name = "role_id", length = 80)
    private String roleId;

    @Column(name = "role_name", length = 80)
    private String roleName;

    @Column(name = "description", length = 80)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

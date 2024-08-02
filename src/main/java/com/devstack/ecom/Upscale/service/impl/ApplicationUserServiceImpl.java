package com.devstack.ecom.Upscale.service.impl;

import com.devstack.ecom.Upscale.auth.ApplicationUser;
import com.devstack.ecom.Upscale.entity.User;
import com.devstack.ecom.Upscale.entity.UserRole;
import com.devstack.ecom.Upscale.exception.EntryNotFoundException;
import com.devstack.ecom.Upscale.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.devstack.ecom.Upscale.security.ApplicationUserRole.*;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> selectedUser = userRepo.findByEmail(username);
        if (selectedUser.isEmpty()){
            throw new EntryNotFoundException(String.format("username %s not found", username));
        }
        Set<SimpleGrantedAuthority> grantedAuthoritySet = new HashSet<>();

        for(UserRole user:selectedUser.get().getRoles()){
            if (user.getRoleName().equals("USER")){
                grantedAuthoritySet.addAll(USER.grantedAuthorities());
            }
            if (user.getRoleName().equals("CUSTOMER")){
                grantedAuthoritySet.addAll(CUSTOMER.grantedAuthorities());
            }
            if (user.getRoleName().equals("ADMIN")){
                grantedAuthoritySet.addAll(ADMIN.grantedAuthorities());
            }
            if (user.getRoleName().equals("MANAGER")){
                grantedAuthoritySet.addAll(MANAGER.grantedAuthorities());
            }
        }

        return new ApplicationUser(
                grantedAuthoritySet,
                selectedUser.get().getPassword(),
                selectedUser.get().getEmail(),
                selectedUser.get().isAccountNonExpired(),
                selectedUser.get().isAccountNonLocked(),
                selectedUser.get().isCredentialsNonExpired(),
                selectedUser.get().isEnabled()
        );

    }
}

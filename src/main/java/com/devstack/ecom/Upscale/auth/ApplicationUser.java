package com.devstack.ecom.Upscale.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class ApplicationUser implements UserDetails {

    private final Set<? extends GrantedAuthority> authorities;
    private final String password;
    private final String userName;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public ApplicationUser(Set<? extends GrantedAuthority> authorities, String password, String userName,
                           boolean isAccountNonExpired, boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired, boolean isEnabled) {
        this.authorities = authorities;
        this.password = password;
        this.userName = userName;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

//    application.jwt.secretKey=sdlkfjsldkflksdfjlksdfjlksdjflksdjflksdjflkjsflkjsdf
//    application.jwt.tokenPrefix=Bearer
//    application.jwt.tokenExpirationDays=90

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}


//new version
//private final PasswordEncoder passwordEncoder;
//    private final ApplicationUserServiceImpl applicationUserService;
//    private final SecretKey secretKey;
//    private final JwtConfig jwtConfig;
//
//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserServiceImpl applicationUserService, SecretKey secretKey, JwtConfig jwtConfig) {
//        this.passwordEncoder = passwordEncoder;
//        this.applicationUserService = applicationUserService;
//        this.secretKey = secretKey;
//        this.jwtConfig = jwtConfig;
//    }
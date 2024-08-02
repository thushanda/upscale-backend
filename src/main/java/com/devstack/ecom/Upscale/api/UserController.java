package com.devstack.ecom.Upscale.api;

import com.devstack.ecom.Upscale.dto.request.RequestUserDto;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/visitor/signup")
    public ResponseEntity<StandardResponse> createProduct(@RequestBody RequestUserDto dto){
        userService.create(dto, "USER");
        return new ResponseEntity<>(
                new StandardResponse(
                        201,
                        "User was created!...",
                        null
                ), HttpStatus.CREATED
        );
    }
}

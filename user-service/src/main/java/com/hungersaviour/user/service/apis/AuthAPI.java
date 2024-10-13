package com.hungersaviour.user.service.apis;

import com.hungersaviour.user.service.dtos.AuthRequestDTO;
import com.hungersaviour.user.service.dtos.SignUpRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class AuthAPI {

    @PostMapping("/signup")
    public void registerUser(@RequestBody SignUpRequestDTO signUpRequestDTO){

    }

    @PostMapping("/login")
    public void authenticateUser(@RequestBody AuthRequestDTO authRequestDTO){

    }


}

package com.hungersaviour.user.service.apis;

import com.hungersaviour.user.service.dtos.AuthRequestDTO;
import com.hungersaviour.user.service.dtos.SignUpRequestDTO;
import com.hungersaviour.user.service.entities.UserEntity;
import com.hungersaviour.user.service.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class AuthAPI {

    private final AuthenticationManager authenticationManager;

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public void registerUser(@RequestBody SignUpRequestDTO signUpRequestDTO){

    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication= this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getUsername(),
                        authRequestDTO.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = (UserEntity) authentication.getPrincipal();
        return  this.authenticationService.generateToken(authentication);
    }


}

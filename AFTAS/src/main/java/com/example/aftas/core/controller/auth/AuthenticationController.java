package com.example.aftas.core.controller.auth;

import com.example.aftas.core.dao.model.dto.Get.GetUserDto;
import com.example.aftas.core.dao.model.dto.Store.UserDto;
import com.example.aftas.core.dao.model.dto.request.AuthenticationRequest;
import com.example.aftas.core.dao.model.dto.request.RegisterRequest;
import com.example.aftas.core.dao.model.dto.response.AuthenticationResponse;
import com.example.aftas.core.dao.model.entity.User;
import com.example.aftas.core.service.auth.AuthenticationService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(AppEndpoints.AUTHENTICATION_ENDPOINT)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/regis")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(authenticationService.register(request));
    }
     @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
         return ResponseEntity.ok(authenticationService.authenticate(request));

    }

    @PostMapping("/refresh")
    public void refresh(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        authenticationService.refresh(request,response);
    }

    @GetMapping("/user")
    public ResponseEntity<GetUserDto> getAuthenticatedUser (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof User user) {

            return ResponseEntity.ok(
                    GetUserDto.builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .role(user.getRole().name())
                            .permissions(user.getRole().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                            .identityNumber(user.getIdentityNumber())
                            .nationality(user.getNationality())
                            .accessionDate(user.getAccessionDate())
                            .build()
            );
        }

        return ResponseEntity.badRequest().build();
    }

}

package com.example.aftas.core.controller;


import com.example.aftas.core.dao.model.dto.Get.GetUserDto;
import com.example.aftas.core.dao.model.dto.Store.UserDto;
import com.example.aftas.core.dao.model.dto.request.RegisterRequest;
import com.example.aftas.core.dao.model.dto.response.AuthenticationResponse;
import com.example.aftas.core.dao.model.dto.update.UpdateUserDto;
import com.example.aftas.core.service.UserService;
import com.example.aftas.core.service.auth.AuthenticationService;
import com.example.aftas.shared.Const.AppEndpoints;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))

@RequestMapping(AppEndpoints.USER_ENDPOINT)
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;


    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody @Valid RegisterRequest userDto) {
        AuthenticationResponse addedUser = authenticationService.register(userDto);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<GetUserDto>> getAllUsers() {
        List<GetUserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{num}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<GetUserDto> getUserByNum(@PathVariable Integer num) {
        GetUserDto member = userService.getUserByNum(num);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:change_role')")
    public GetUserDto update(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUserRole(updateUserDto);
    }


}

package com.ClothesFriends.ClothesFriendsBackEnd.controller;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.CreateUserDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.LogUserDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.AuthenticationResponse;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody CreateUserDTO request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value ="/login", consumes = "application/json")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LogUserDTO request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }



}

package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.DTO.Inspiration.CreateUserDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.DTO.User.LogUserDTO;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Authentication.AuthenticationResponse;
import com.ClothesFriends.ClothesFriendsBackEnd.model.Authentication.Token;
import com.ClothesFriends.ClothesFriendsBackEnd.model.User.User;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.TokenRepository;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.User.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(CreateUserDTO request) {

        // check if user already exist. if exist than authenticate the user
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = repository.save(user);

        // Call the createUserDirectories function here
        createUserDirectories(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, user.getId());
    }

    public void createUserDirectories(User user) {
        String userDirectoryPath = "C:/Users/lizlu/OneDrive/Documentos/3ero/LabI/ClothesFriends-FrontEnd/public/images/" + user.getId();
        String[] subDirectories = {"profilepicture", "clothingitem", "outfit", "inspo"};

        try {
            // Create the user's directory
            Files.createDirectories(Paths.get(userDirectoryPath));

            // Create the subdirectories
            for (String subDirectory : subDirectories) {
                Files.createDirectories(Paths.get(userDirectoryPath, subDirectory));
            }
        } catch (IOException e) {
            // Handle the exception, e.g. by logging it
            e.printStackTrace();
        }
    }


    public AuthenticationResponse authenticate(LogUserDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, user.getId());

    }
    public void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

}

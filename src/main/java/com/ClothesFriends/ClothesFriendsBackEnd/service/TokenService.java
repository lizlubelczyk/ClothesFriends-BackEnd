package com.ClothesFriends.ClothesFriendsBackEnd.service;

import com.ClothesFriends.ClothesFriendsBackEnd.model.Token;
import com.ClothesFriends.ClothesFriendsBackEnd.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
    public Token getTokenByUserId(Integer userId) {
        return tokenRepository.findByUserId(userId);
    }

    public void deleteTokenById(Integer id) {
        tokenRepository.deleteById(id);
    }

    public List<Token> getAllTokensByUser(Integer userId) {
        return tokenRepository.findAllTokensByUser(userId);
    }
}

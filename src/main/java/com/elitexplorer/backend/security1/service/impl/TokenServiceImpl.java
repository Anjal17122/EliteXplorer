package com.elitexplorer.backend.security1.service.impl;

import com.elitexplorer.backend.security.constants.SecurityConstants;
import com.elitexplorer.backend.security1.model.AuthToken;
import com.elitexplorer.backend.security1.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class TokenServiceImpl implements TokenService {
    private Map<String, AuthToken> authMapToken = new HashMap<>();

    @Override
    public Authentication getAuthByToken(String token) {
        AuthToken authToken = authMapToken.getOrDefault(token, new AuthToken());
        authToken.renew();

        return !authToken.isExpired() ? authToken.getAuthentication() : null;
    }

    @Override
    public String setNewTokenByUsername(Authentication authentication) {
        AuthToken token = getNewToken(authentication.getName());
        token.setAuthentication(authentication);
        authMapToken.put(token.getToken(), token);

        return token.getToken();
    }

    private AuthToken getNewToken(String username) {
        AuthToken authToken = new AuthToken();
        authToken.setDateTime(LocalDateTime.now());

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()
                        + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        authToken.setToken(SecurityConstants.TOKEN_PREFIX+token);
        authMapToken.put(authToken.getToken(), authToken);
        return authToken;
    }
}

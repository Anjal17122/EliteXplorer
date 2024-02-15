package com.elitexplorer.backend.security1.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    Authentication getAuthByToken(String token);

    String setNewTokenByUsername(Authentication authentication);
}

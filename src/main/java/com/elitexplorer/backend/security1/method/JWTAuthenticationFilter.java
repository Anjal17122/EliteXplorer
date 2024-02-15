package com.elitexplorer.backend.security1.method;



import com.elitexplorer.backend.security.repository.SecUserCredentialRepository;
import com.elitexplorer.backend.security1.model.MyUserDetails;
import com.elitexplorer.backend.security1.model.SecUser;
import com.elitexplorer.backend.security1.model.dto.LoginFailedResponse;
import com.elitexplorer.backend.security1.model.dto.LoginSuccessResponse;
import com.elitexplorer.backend.security1.securityutils.JwtUtils;
import com.elitexplorer.backend.security1.securityutils.UserStatus;
import com.elitexplorer.backend.security1.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public static String usernamess;

    @Autowired
    JwtUtils jwtUtils;

    @Resource
    private SecUserCredentialRepository secUserCredentialRepository;

    public JWTAuthenticationFilter( AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {

        try {
            SecUser creds = new ObjectMapper().readValue(req.getInputStream(), SecUser.class);
            usernamess = creds.getUserName();
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUserName(),
                    creds.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            System.out.println("Exception in Attempt : ");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        if (((MyUserDetails) auth.getPrincipal()).getSecUser().getUserStatus() == UserStatus.disabled) {
            String json = new Gson().toJson(new LoginFailedResponse(false, "User is disabled"));
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json);
            logger.info("json: " + json);
        } else if (((MyUserDetails) auth.getPrincipal()).getSecUser().getUserStatus() == UserStatus.locked) {
            String json = new Gson().toJson(new LoginFailedResponse(false, "Account is locked"));
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json);
            logger.info("json: " + json);
        } else {

            /*SecUserCredential user = ((MyUserDetails) auth.getPrincipal())
                    .getSecUser()
                    .getPersonPerma()
                    .getUser();
            user.resetWrongPasswordCount();
            secUserCredentialRepository.save(user);*/

            String token = tokenService.setNewTokenByUsername(auth);
            String wardId = "";
            if (((MyUserDetails) auth.getPrincipal()).getSecUser().getWardId()!= null){
                wardId = ((MyUserDetails) auth.getPrincipal()).getSecUser().getWardId();
            }
            String json = new Gson()
                    .toJson(new LoginSuccessResponse(true, ((MyUserDetails) auth.getPrincipal()).getUsername(), token,
                            ((MyUserDetails) auth.getPrincipal()).getRoles(), wardId));

            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json);
            logger.info("json: " + json);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse res,
                                              AuthenticationException failed) throws IOException, ServletException {
//        SecUserCredential secUserCredential = secUserCredentialRepository.findByUsername(usernamess);
//        System.out.println(secUserCredential.getPassword());
        String json = new Gson().toJson(new LoginFailedResponse(false, "Incorrect username or password!!, You have only few attempts left"));
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json);
        logger.info("json: " + json);
    }
}
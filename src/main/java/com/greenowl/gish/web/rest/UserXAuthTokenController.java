package com.greenowl.gish.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.greenowl.gish.security.xauth.Token;
import com.greenowl.gish.security.xauth.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping("/api")
public class UserXAuthTokenController {

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;

    private static final Logger LOG = LoggerFactory.getLogger(UserXAuthTokenController.class);

    @RequestMapping(value = "/authenticate",
            method = {RequestMethod.POST, RequestMethod.GET})
    @Timed
    public Token authorize(@RequestParam(required = false) String username,
                           @RequestParam String password) {
        LOG.debug("Attempting to authenticate with username = {}", username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(username);
        return tokenProvider.createToken(details);
    }

}

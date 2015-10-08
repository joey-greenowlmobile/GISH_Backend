package com.greenowl.gish.security.xauth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The security token.
 */
public class Token {

    String token;
    long expires;

    @JsonProperty("social_authentication")
    boolean socialAuthentication = false; // Tells Client, User authenticated via 3rd party service.

    public Token(String token, long expires){
        this.token = token;
        this.expires = expires;
    }

    public Token(String token, long expires, boolean socialAuth){
        this.token = token;
        this.expires = expires;
        this.socialAuthentication = socialAuth;
    }

    public boolean isSocialAuthentication() {
        return socialAuthentication;
    }

    public String getToken() {
        return token;
    }

    public long getExpires() {
        return expires;
    }
}

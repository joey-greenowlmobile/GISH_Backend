package com.greenowl.gish.factory;

import com.greenowl.gish.domain.Authority;
import com.greenowl.gish.domain.User;

import java.util.Set;

public class UserFactory {

    private UserFactory() {
    }

    /**
     * Create A new User Entity.
     *
     * @param login
     * @param firstName
     * @param lastName
     * @param region
     * @param encryptedPassword
     * @param authorities
     * @return
     */
    public static User create(String login, String firstName, String lastName, String region,
                              String encryptedPassword, Set<Authority> authorities) {
        User newUser = new User();
        newUser.setLogin(login);
        // new user gets initially a generated password, to avoid having to pick a password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        //for now, hard code
        String langKey = "en";
        newUser.setLangKey(langKey);
        // activation key is not needed for now.
        newUser.setRegion(region);
        newUser.setAuthorities(authorities);
        //Add a default profile Picture
        return newUser;
    }


}

package com.greenowl.gish.security;

import com.greenowl.gish.domain.Authority;
import com.greenowl.gish.domain.User;
import com.greenowl.gish.repository.UserRepository;
import com.greenowl.gish.service.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional // Authenticates a user based on their login or email
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating user with email {}", login);
        User userFromDatabase = userRepository.findOneByLogin(login); // User wants to login with email
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("Unable to find user [" + login + "].");
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(login,
                userFromDatabase.getPassword(), grantedAuthorities);
    }


}

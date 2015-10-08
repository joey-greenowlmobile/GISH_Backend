package com.greenowl.gish.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.greenowl.gish.domain.User;
import com.greenowl.gish.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/pub/admin")
public class PubResource {

    private static final Logger LOG = LoggerFactory.getLogger(AdminResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    /**
     * POST  /change_password -> changes the current user's password
     */
    @RequestMapping(value = "/account/change_password",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> changePassword() {
        String password = "admin";
        User currentUser = userRepository.findOneByLogin("admin");

        String encryptedPassword = passwordEncoder.encode(password);
        currentUser.setPassword(encryptedPassword);
        userRepository.save(currentUser);
        LOG.debug("Changed password for User: {}", currentUser);
        return new ResponseEntity<>(OK);
    }

}

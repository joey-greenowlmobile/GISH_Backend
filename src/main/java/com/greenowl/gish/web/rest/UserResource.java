package com.greenowl.gish.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.greenowl.gish.domain.User;
import com.greenowl.gish.repository.UserRepository;
import com.greenowl.gish.service.UserService;
import com.greenowl.gish.web.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static com.greenowl.gish.service.util.UserUtil.getUserDTOs;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    @Inject
    private UserRepository userRepository;

    /**
     * GET  /users -> get all users.
     */
    @RequestMapping(value = "/users",
            method = GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserDTO> getAll() {
        LOG.debug("REST request to get all Users");
        List<User> users = userRepository.findAll();
        return getUserDTOs(users);
    }

}

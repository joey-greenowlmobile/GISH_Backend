package com.greenowl.gish.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.greenowl.gish.domain.Authority;
import com.greenowl.gish.domain.User;
import com.greenowl.gish.repository.UserRepository;
import com.greenowl.gish.security.AuthoritiesConstants;
import com.greenowl.gish.security.SecurityUtils;
import com.greenowl.gish.service.UserService;
import com.greenowl.gish.service.register.RegistrationService;
import com.greenowl.gish.service.util.UserUtil;
import com.greenowl.gish.web.rest.dto.PasswordUpdateDTO;
import com.greenowl.gish.web.rest.dto.UserDTO;
import com.greenowl.gish.web.rest.dto.user.CreateAccountRequest;
import com.greenowl.gish.web.rest.dto.user.UpdateAccountRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.greenowl.gish.exception.ErrorResponseFactory.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger LOG = LoggerFactory.getLogger(AccountResource.class);

    private static final String USERNAME_TAKEN = "username is already in use!";

    private static final String USER_NOT_REMOVED = "Unable to remove user.";

    private static final String USER_NOT_FOUND = "Unable to find user.";

    @Inject
    private UserRepository userRepository;

    @Inject
    private RegistrationService registrationService;

    @Inject
    private UserService userService;

    /**
     * POST  /register -> register the user.
     */
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> registerAccount(@Valid @RequestBody CreateAccountRequest req) {
        User user = userRepository.findOneByLogin(req.getLogin()); //login available
        if (user != null) {
            return new ResponseEntity<>(genericBadReq(USERNAME_TAKEN, "/register"),
                    BAD_REQUEST);
        }
        UserDTO dto = registrationService.register(req);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    /**
     * GET  /authenticate -> check if the user is authenticated, and return its login.
     */
    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public String isAuthenticated(HttpServletRequest request) {
        LOG.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * GET  /account -> get the current user.
     */
    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserDTO> getAccount() {
        User user = userService.getUserWithAuthorities();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<String> roles = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList());
        UserDTO userDTO = UserUtil.getUserDTO(user, roles);
        LOG.debug("Returning user back to client: {}", userDTO);
        return new ResponseEntity<>(userDTO, OK);
    }

    /**
     * DELETE  /account -> Remove a particular account form data store.
     */
    @RequestMapping(value = "/account/{login}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> deleteAccount(@PathVariable("login") final String login) {
        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(notFound(USER_NOT_FOUND, "/account/" + login), HttpStatus.NOT_FOUND);
        }
        boolean deleted = userService.deleteUser(user);
        if (!deleted) {
            return new ResponseEntity<>(genericNotModified(USER_NOT_REMOVED, "/account/" + login), HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(OK);
    }

    /**
     * POST  /account -> update the current user information.
     */
    @RequestMapping(value = "/account",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @RolesAllowed(AuthoritiesConstants.USER)
    public ResponseEntity<?> saveAccount(@RequestBody UpdateAccountRequest req) {
        userService.updateUserInformation(SecurityUtils.getCurrentLogin(),
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                req.getRegion());
        return new ResponseEntity<>(OK);
    }

    /**
     * POST  /change_password -> changes the current user's password
     */
    @RequestMapping(value = "/account/change_password",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> changePassword(@RequestBody PasswordUpdateDTO dto) {
        String password = dto.getPassword();
        if (StringUtils.isEmpty(password) || password.length() < 5 || password.length() > 50) {
            return new ResponseEntity<>(genericBadReq("Password must be between 5 and 50 characters",
                    "/account/change_password"), BAD_REQUEST);
        }
        userService.changePassword(password);
        return new ResponseEntity<>(OK);
    }


}

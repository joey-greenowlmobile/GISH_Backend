package com.greenowl.gish.service;

import com.greenowl.gish.domain.Authority;
import com.greenowl.gish.domain.User;
import com.greenowl.gish.repository.AuthorityRepository;
import com.greenowl.gish.repository.UserRepository;
import com.greenowl.gish.security.AuthoritiesConstants;
import com.greenowl.gish.security.SecurityUtils;
import com.greenowl.gish.service.util.UserUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Service class for managing users.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;


    @Async
    public void updateUserInformation(String login, String firstName, String lastName, String email, String region) {
        User user = userRepository.findOneByLogin(login);
        if (user == null) {
            LOG.warn("Unable to update user information for login = {}", login);
            return;
        }

        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setLastName(lastName);

        if (UserUtil.isValidEmail(email)) {
            user.setEmail(email);
        }
        if (region != null) user.setRegion(region);
        userRepository.save(user);
        LOG.debug("Changed Information for User: {}", user);
    }


    public void changePassword(String password) {
        User currentUser = getCurrentUser();
        String encryptedPassword = passwordEncoder.encode(password);
        currentUser.setPassword(encryptedPassword);
        userRepository.save(currentUser);
        LOG.debug("Changed password for User: {}", currentUser);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        User currentUser = getCurrentUser();
        currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }

    /**
     * Retrieve user from data store and fully load all associations
     *
     * @return
     */
    @Transactional(readOnly = true)
    public User getFatUser() {
        return getFatUser(SecurityUtils.getCurrentLogin());
    }

    /**
     * Retrieve user from data store and fully load all associations
     *
     * @return
     */
    @Transactional(readOnly = true)
    public User getFatUser(String login) {
        User user = getUser(login);
        user.getAuthorities().size();
        return user;
    }

    /**
     * Retrieves currently authenticated user without doing any additional table joins.
     *
     * @return
     */
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        return getUser(SecurityUtils.getCurrentLogin());
    }

    /**
     * Pull user from data store with login = :login without any further joins.
     *
     * @param login
     * @return
     */
    public User getUser(String login) {
        return userRepository.findOneByLogin(login);
    }

    /**
     * Remove a user from data store, return true if user is successfully removed.
     *
     * @param user
     * @return
     */
    @Transactional
    public boolean deleteUser(User user) {
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.ADMIN);

        User foundUser = userRepository.findOneByLogin(user.getLogin());
        foundUser.getAuthorities().size();

        if (foundUser.getAuthorities().contains(authority)) {
            return false;
        }
        userRepository.delete(foundUser);
        return !userRepository.exists(foundUser.getLogin());
    }

}

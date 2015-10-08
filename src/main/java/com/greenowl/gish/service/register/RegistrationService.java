package com.greenowl.gish.service.register;

import com.greenowl.gish.domain.Authority;
import com.greenowl.gish.domain.User;
import com.greenowl.gish.factory.UserFactory;
import com.greenowl.gish.repository.AuthorityRepository;
import com.greenowl.gish.repository.UserRepository;
import com.greenowl.gish.service.util.UserUtil;
import com.greenowl.gish.web.rest.dto.UserDTO;
import com.greenowl.gish.web.rest.dto.user.CreateAccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static com.greenowl.gish.security.AuthoritiesConstants.USER;

@Service
public class RegistrationService {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    public UserDTO register(CreateAccountRequest req) {
        return createUserInformation(req.getLogin(), req.getFirstName(), req.getLastName(),
                req.getRegion(), req.getPassword());
    }

    /**
     * Create a new user and persist that user into the data store.
     *
     * @param login
     * @param firstName
     * @param lastName
     * @return newUser
     */
    private UserDTO createUserInformation(String login, String firstName, String lastName,
                                          String region, String desiredPassword) {
        Authority authority = authorityRepository.findOne(USER);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        String encryptedPassword = passwordEncoder.encode(desiredPassword);
        User newUser = UserFactory.create(login, firstName, lastName, region, encryptedPassword, authorities);
        userRepository.save(newUser);
        LOG.debug("Created Information for User: {}", newUser);
        UserDTO dto = UserUtil.getUserDTO(newUser);
        LOG.info("Returning newly created user {}", dto);
        return dto;
    }


}

package com.greenowl.gish.service.util;

import com.greenowl.gish.domain.User;
import com.greenowl.gish.security.AuthoritiesConstants;
import com.greenowl.gish.web.rest.dto.UserDTO;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ahmed on 2015-06-01.
 */
public class UserUtil {

    private UserUtil() {
    }


    /**
     * Get a List of UserDTO objects from a List of Users
     *
     * @param users
     * @return
     */
    public static List<UserDTO> getUserDTOs(Collection<User> users) {
        final List<UserDTO> dtos = new ArrayList<>();
        if (users == null || users.size() == 0) {
            return dtos;
        }
        users.forEach((User user) -> {
            if (!user.getLogin().equalsIgnoreCase("anonymousUser") &&
                    !user.getLogin().equals("system")) {
                dtos.add(getUserDTO(user));
            }
        });
        return dtos;
    }

    /**
     * Retrieve a User DTO object from a user entity.
     *
     * @param user
     * @return
     */
    public static UserDTO getUserDTO(User user) {
        return getUserDTO(user, Collections.singletonList(AuthoritiesConstants.USER));
    }

    /**
     * Retrieve a User DTO object from a user entity.
     *
     * @param user
     * @return
     */
    public static UserDTO getUserDTO(User user, List<String> roles) {
        return new UserDTO(user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getLangKey(),
                roles,
                user.getRegion());
    }


    public static boolean isValidEmail(String input) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(input);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}

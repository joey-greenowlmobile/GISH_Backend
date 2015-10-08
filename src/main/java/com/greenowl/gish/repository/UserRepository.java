package com.greenowl.gish.repository;

import com.greenowl.gish.domain.User;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.createdDate > ?1")
    List<User> findUsersBeforeCreationDate(DateTime dateTime);

    User findOneByLogin(String login);
}

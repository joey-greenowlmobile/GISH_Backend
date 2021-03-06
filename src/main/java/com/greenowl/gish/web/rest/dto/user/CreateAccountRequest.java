package com.greenowl.gish.web.rest.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountRequest {

    @NotNull
    @Size(min = 5, max = 250)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    private String region;

    @NotNull
    private String password;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String login, String firstName, String lastName, String region, String password) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.password = password;
    }

    public CreateAccountRequest(String login, String firstName, String lastName, String password, Boolean socialUser) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", region='" + region + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.epam.www.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**

 * Created by Farkas on 2017.02.19..
 */
public class CredentialDTO {

    @Email(message = "Email must be valid!")
    private String email = "";
    @NotEmpty(message = "Password must not be empty!")
    private String password = "";


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

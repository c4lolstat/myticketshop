package com.epam.www.dto;

import javax.validation.constraints.Size;

/**

 * Created by Farkas on 2017.02.19..
 */
public class CredentialDTO {

    @Size(min = 1, message = "may not be empty")
    private String email = "";
    @Size(min = 1, message = "may not be empty")
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

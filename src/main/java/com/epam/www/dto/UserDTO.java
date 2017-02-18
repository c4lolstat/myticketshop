package com.epam.www.dto;

import com.epam.www.dataaccess.entity.User;

/**
 * Created by Farkas on 2017.02.11..
 */
public class UserDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private long account;
    private String discount;

    public UserDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.account = user.getAccount();
        this.discount = user.getDiscount();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public long getAccount() {
        return account;
    }

    public String getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                ", discount='" + discount + '\'' +
                '}';
    }
}

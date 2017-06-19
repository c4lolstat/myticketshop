package com.epam.www.dto;

import com.epam.www.dataaccess.entity.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Created by Farkas on 2017.02.11..
 */
public class UserDTO {

    private int id;
    private String firstName = "";
    private String lastName = "";
    @NotEmpty(message = "Password must not be empty!")
    private String password = "";
    @Email(message = "Email must be valid!")
    private String email = "";
    @Min(value = 0, message = "Account cannot be negative!")
    private BigDecimal account;
    private String discount = "";

    public UserDTO(){}

    public UserDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.account = user.getAccount();
        this.discount = user.getDiscount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAccount(BigDecimal account) {
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

    public BigDecimal getAccount() {
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

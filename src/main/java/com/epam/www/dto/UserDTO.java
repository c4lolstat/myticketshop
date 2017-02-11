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

    public static class Builder {

        private String firstName = "";
        private String lastName = "";
        private String password = "";
        private String email = "";
        private long account = 0;
        private String discount = "";

        public Builder setFirstName(final String  firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(final String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder setPassword(final String password){
            this.password = password;
            return this;
        }

        public Builder setEmail(final String email){
            this.email = email;
            return this;
        }

        public Builder setAccount(final long account){
            this.account = account;
            return this;
        }

        public Builder setDiscount(final String discount){
            this.discount = discount;
            return this;
        }

        public UserDTO build(){
            return new UserDTO(this);
        }
    }

    private UserDTO(Builder builder){
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        password = builder.password;
        account = builder.account;
        discount = builder.discount;
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
        return "Builder{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                ", discount='" + discount + '\'' +
                '}';
    }
}

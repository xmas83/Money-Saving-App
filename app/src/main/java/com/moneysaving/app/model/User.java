package com.moneysaving.app.model;

import java.sql.Timestamp;

public class User {

    private static final String accountType = "User";
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String emailAddress;
    private Timestamp dateCreated;

    /**
     * Instantiates a new user.
     */

    public User() {

    }


    /**
     *  Instantiates a new user.
     *
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @param emailAddress
     * @param dateCreated
     */
    public User(String firstName, String lastName, String userName, String password,
                String emailAddress, Timestamp dateCreated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.dateCreated = dateCreated;
    }

    public static String getAccountType() {
        return accountType;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}

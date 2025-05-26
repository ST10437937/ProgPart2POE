package com.newapp;

import java.util.regex.*;
import java.io.*;
import java.util.*;

public class UserAuthentication {
    private String firstName;
    private String lastName;
    private String username;
    private String userPassword;
    private String contactNumber;

    public UserAuthentication(String firstName, String lastName, String username, String userPassword, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userPassword = userPassword;
        this.contactNumber = contactNumber;
    }

    
    UserAuthentication() {
        throw new UnsupportedOperationException("Not supported yet."); // Default constructor
    }

    // Validate Username format
    public boolean isUsernameValid() {
        return username.contains("_") && username.length() <= 5;
    }

    // Check if the password is strong
    public boolean isPasswordStrong() {
        return userPassword.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(userPassword).find() &&
               Pattern.compile("[0-9]").matcher(userPassword).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(userPassword).find();
    }

    // check contact number format
    public boolean isContactNumberValid() {
        return contactNumber.matches("^\\+\\d{1,4}\\d{1,10}$");
    }

    // Register a new user
    public String registerNewUser() {
        if (!isUsernameValid()) {
            return "Username is not correctly formatted; please ensure it contains an underscore and is no more than five characters long.";
        }
        if (!isPasswordStrong()) {
            return "Password is not strong enough; please ensure it contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!isContactNumberValid()) {
            return "Contact number incorrectly formatted or missing international code.";
        }

        try (FileWriter fileWriter = new FileWriter("users.txt", true)) {
            fileWriter.write(username + "," + userPassword + "," + firstName + "," + lastName + "," + contactNumber + "\n");
        } catch (IOException e) {
            return "Error saving user data.";
        }
        return "User registered successfully.";
    }

    // Authenticate user credentials
    public boolean authenticateUser(String inputUsername, String inputPassword) {
        try {
            Scanner scanner = new Scanner(new File("users.txt"));
            while (scanner.hasNextLine()) {
                String[] details = scanner.nextLine().split(",");
                if (details.length >= 5 &&
                    details[0].equals(inputUsername) &&
                    details[1].equals(inputPassword)) {
                    this.firstName = details[2];
                    this.lastName = details[3];
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    // feedback on login success
    public String getLoginFeedback(boolean success) {
        if (success) {
            return "Welcome " + firstName + " " + lastName + ", it's great to see you again.";
        } else {
            return "Incorrect Username or password, please try again.";
        }
    }
}

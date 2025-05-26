package com.newapp;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Register - InterConnect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setLayout(new GridLayout(8, 2, 15, 15));
        frame.getContentPane().setBackground(new Color(255, 192, 203)); // Pink outer background

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JTextField contactField = new JTextField();
        JButton registerButton = new JButton("Create Account");
        JButton backToLoginButton = new JButton("Return to Login");

        frame.add(new JLabel("First Name:"));
        frame.add(firstNameField);
        frame.add(new JLabel("Last Name:"));
        frame.add(lastNameField);
        frame.add(new JLabel("Username:"));
        frame.add(userField);
        frame.add(new JLabel("Password:"));
        frame.add(passField);
        frame.add(new JLabel("Contact Number (+code):"));
        frame.add(contactField);
        frame.add(new JLabel(""));
        frame.add(registerButton);
        frame.add(new JLabel(""));
        frame.add(backToLoginButton);

        // Set button colors and fonts
        Font buttonFont = new Font("Bookman Old Style", Font.BOLD, 16);
        registerButton.setBackground(new Color(255, 192, 203)); // Pink background
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(buttonFont);

        backToLoginButton.setBackground(new Color(255, 192, 203)); // Pink background
        backToLoginButton.setForeground(Color.WHITE);
        backToLoginButton.setFont(buttonFont);

        // Set font for labels and text fields
        Font labelFont = new Font("Bookman Old Style", Font.PLAIN, 16);
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(labelFont);
                comp.setForeground(Color.BLACK);
            } else if (comp instanceof JTextField || comp instanceof JPasswordField) {
                comp.setFont(labelFont);
                comp.setBackground(Color.WHITE);
            }
        }

        registerButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String userId = userField.getText();
            String password = new String(passField.getPassword());
            String contact = contactField.getText();

            UserAuthentication userAccount = new UserAuthentication(firstName, lastName, userId, password, contact);
            String feedback = userAccount.registerNewUser();

            JOptionPane.showMessageDialog(frame, feedback);
        });

        backToLoginButton.addActionListener(e -> {
            frame.dispose();
            UserLoginForm.main(null);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
package com.newapp;

import javax.swing.*;
import java.awt.*;
import com.newapp.interconnectapp.MessageApp;  

public class UserLoginForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("User  Login - InterConnect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 2, 15, 15));
        frame.getContentPane().setBackground(new Color(255, 192, 203)); // Pink outer background

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton submitButton = new JButton("Access Account");

        frame.add(new JLabel("Username:"));
        frame.add(userField);
        frame.add(new JLabel("Password:"));
        frame.add(passField);
        frame.add(new JLabel(""));
        frame.add(submitButton);

        // Set button colors and font
        submitButton.setBackground(new Color(255, 192, 203)); // Pink background
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Bookman Old Style", Font.BOLD, 16));

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

        submitButton.addActionListener(e -> {
            String userId = userField.getText();
            String password = new String(passField.getPassword());

            UserAuthentication userAccount = new UserAuthentication("", "", "", "", "");
            boolean isAuthenticated = userAccount.authenticateUser(userId, password);
            String feedbackMessage = userAccount.getLoginFeedback(isAuthenticated);

            JOptionPane.showMessageDialog(frame, feedbackMessage);

            if (isAuthenticated) {
                frame.dispose();
                launchMainMenu(userId);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void launchMainMenu(String username) {
        JOptionPane.showMessageDialog(null, "Welcome to InterConnectApp");

        while (true) {
            String[] options = {"Send Messages", "Coming Soon", "Quit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Please choose an option:",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0:
                    MessageApp.launch(username);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Feature coming soon. Stay tuned!");
                    break;
                case 2:
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }
}
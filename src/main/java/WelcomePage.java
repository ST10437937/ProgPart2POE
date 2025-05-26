package com.newapp;

import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("InterConnect - Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        // Outer pink panel
        JPanel outerPanel = new JPanel();
        outerPanel.setBounds(20, 20, 560, 340);
        outerPanel.setBackground(new Color(255, 192, 203)); // Pink color
        outerPanel.setLayout(null);
        frame.add(outerPanel);

        // Inner white panel for content
        JPanel innerPanel = new JPanel();
        innerPanel.setBounds(5, 5, 550, 330);
        innerPanel.setBackground(new Color(255, 255, 255));
        innerPanel.setLayout(null);
        outerPanel.add(innerPanel);

        // InterConnect title with 2-color style
        JLabel title = new JLabel();
        title.setBounds(150, 30, 300, 40);
        title.setFont(new Font("Bookman Old Style", Font.BOLD, 36));
        title.setText("<html><span style='color:#ff5722;'>Inter</span><span style='color:#ffc107;'>Connect</span></html>");
        innerPanel.add(title);

        // Sign Up button
        JButton signupButton = new JButton("Join Us");
        signupButton.setBounds(185, 100, 180, 45);
        signupButton.setBackground(new Color(255, 192, 203)); // Pink background
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2, true)); // deeper pink border
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.setOpaque(true);
        innerPanel.add(signupButton);

        // Login button underneath Sign Up button
        JButton loginButton = new JButton("Access Account");
        loginButton.setBounds(185, 160, 180, 45); 
        loginButton.setBackground(new Color(255, 192, 203)); // Pink background
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2, true));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setOpaque(true);
        innerPanel.add(loginButton);

        // Button actions
        signupButton.addActionListener(e -> {
            frame.dispose();
            RegistrationForm.main(null);
        });

        loginButton.addActionListener(e -> {
            frame.dispose();
            UserLoginForm.main(null);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

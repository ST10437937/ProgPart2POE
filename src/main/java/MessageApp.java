package com.newapp.interconnectapp;

import javax.swing.*;

public class MessageApp {
    public static void launch(String username) {
        JOptionPane.showMessageDialog(null, "Welcome to InterConnectApp, " + username + "!");

        while (true) {
            String[] options = {"Send Messages", "Coming Soon", "Quit"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Select an option:",
                    "InterConnectApp Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == 0) { // Send Messages
                int numMessages = 0;

                try {
                    String input = JOptionPane.showInputDialog("How many messages would you like to send?");
                    if (input == null) continue;
                    numMessages = Integer.parseInt(input);

                    for (int i = 1; i <= numMessages; i++) {
                        String recipient = JOptionPane.showInputDialog("Enter recipient phone number for message " + i + ":");
                        if (recipient == null) break;

                        String content = JOptionPane.showInputDialog("Enter the message content for message " + i + ":");
                        if (content == null) break;

                        Message msg = new Message(recipient, content);

                        if (!msg.checkMessageID()) {
                            JOptionPane.showMessageDialog(null, "Message ID is too long.");
                            continue;
                        }

                        if (!msg.checkRecipientCell()) {
                            JOptionPane.showMessageDialog(null, "Recipient number must be less than 10 characters and start with '+'.");
                            continue;
                        }

                        String result = msg.sendMessage();
                        JOptionPane.showMessageDialog(null, result);
                    }

                    JOptionPane.showMessageDialog(null, "Total messages sent: " + Message.returnTotalMessages());

                    int showMsgs = JOptionPane.showConfirmDialog(null, "Would you like to see all stored messages?", "Show Messages", JOptionPane.YES_NO_OPTION);
                    if (showMsgs == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, Message.printMessages());
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }

            } else if (choice == 1) {
                JOptionPane.showMessageDialog(null, "Feature coming soon!");
            } else {
                JOptionPane.showMessageDialog(null, "Thank you for using InterConnectApp. Goodbye!");
                break;
            }
        }
    }
}
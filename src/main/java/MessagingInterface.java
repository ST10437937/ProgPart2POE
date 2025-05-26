
package com.newapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessagingInterface {
    private static final List<Message> messages = new ArrayList<>();
    private static String currentRecipient = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("InterConnect - Messaging");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 192, 203));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        // Contacts panel
        JPanel userContactsPanel = new JPanel();
        userContactsPanel.setPreferredSize(new Dimension(250, 600));
        userContactsPanel.setBackground(new Color(245, 245, 245));
        userContactsPanel.setLayout(new BoxLayout(userContactsPanel, BoxLayout.Y_AXIS));

        JLabel contactsHeader = new JLabel("User  Contacts");
        contactsHeader.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
        contactsHeader.setForeground(new Color(255, 105, 180));
        userContactsPanel.add(contactsHeader);

        String[] users = {"Alice Johnson", "Bob Brown", "Charlie Davis", "Diana Prince"};

        for (String user : users) {
            JButton userButton = new JButton(user);
            userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            userButton.setBackground(Color.WHITE);
            userButton.setForeground(new Color(255, 105, 180));
            userButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
            userButton.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180)));
            userButton.setFocusPainted(false);
            userButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            userButton.addActionListener(e -> {
                currentRecipient = user;
                messageArea.append("\n--- Chatting with " + user + " ---\n");
            });
            userContactsPanel.add(userButton);
        }

        // Conversation panel
        JPanel conversationPanel = new JPanel(new BorderLayout());
        conversationPanel.setBackground(Color.WHITE);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        messageArea.setBackground(new Color(250, 250, 250));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        conversationPanel.add(scrollPane, BorderLayout.CENTER);

        JTextField messageInputField = new JTextField();
        messageInputField.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
        messageInputField.setBackground(new Color(245, 245, 245));
        messageInputField.setForeground(new Color(255, 105, 180));
        messageInputField.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180)));

        messageInputField.addActionListener(e -> sendMessage(messageInputField));

        JButton sendMsgButton = new JButton("Send Message");
        sendMsgButton.setBackground(new Color(255, 192, 203));
        sendMsgButton.setForeground(Color.WHITE);
        sendMsgButton.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
        sendMsgButton.setFocusPainted(false);
        sendMsgButton.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180)));
        sendMsgButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendMsgButton.addActionListener(e -> sendMessage(messageInputField));

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.add(messageInputField, BorderLayout.CENTER);
        inputPanel.add(sendMsgButton, BorderLayout.EAST);

        conversationPanel.add(inputPanel, BorderLayout.SOUTH);

        mainPanel.add(conversationPanel, BorderLayout.CENTER);
        mainPanel.add(userContactsPanel, BorderLayout.EAST);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JTextArea messageArea;

    private static void sendMessage(JTextField messageInputField) {
        String text = messageInputField.getText().trim();

        if (currentRecipient == null) {
            JOptionPane.showMessageDialog(null, "Please select a recipient.");
            return;
        }

        if (!text.isEmpty()) {
            Message msg = new Message(currentRecipient, text);
            messages.add(msg);
            messageArea.append("You: " + text + "\n");
            messageInputField.setText("");
            saveMessagesToJSON();
        }
    }

    private static void saveMessagesToJSON() {
        JSONArray array = new JSONArray();
        for (Message msg : messages) {
            JSONObject obj = new JSONObject();
            obj.put("id", msg.getId());
            obj.put("hash", msg.getHash());
            obj.put("recipient", msg.getRecipient());
            obj.put("message", msg.getContent());
            array.add(obj);
        }

        try (FileWriter fw = new FileWriter("messages.json")) {
            fw.write(array.toJSONString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save messages:\n" + e.getMessage());
        }
    }

    static class Message {
        private final String id;
        private final String recipient;
        private final String content;
        private final String hash;

        public Message(String recipient, String content) {
            this.id = UUID.randomUUID().toString();
            this.recipient = recipient;
            this.content = content;
            this.hash = Integer.toHexString((recipient + content).hashCode());
        }

        public String getId() { return id; }
        public String getRecipient() { return recipient; }
        public String getContent() { return content; }
        public String getHash() { return hash; }
    }
}


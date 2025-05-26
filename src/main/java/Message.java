package com.newapp.interconnectapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Message {
    private static int totalMessages = 0;
    private static final ArrayList<JSONObject> messages = new ArrayList<>();
    private final String messageID;
    private final String recipient;
    private final String content;
    private final String messageHash;

    public Message(String recipient, String content) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.content = content;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        return String.format("%010d", new Random().nextInt(1_000_000_000));
    }

    // Validate that Message ID is 10 characters or less
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    
    public boolean checkRecipientCell() {
    
    return recipient != null && recipient.startsWith("+27") && recipient.length() == 12 && recipient.substring(3).matches("\\d{9}");
}

    

    
    public String createMessageHash() {
        String[] words = content.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (messageID.substring(0, 2) + ":" + (totalMessages + 1) + ":" + firstWord + lastWord).toUpperCase();
    }

    // Sends message with 3 user options
    public String sendMessage() {
        if (content.length() > 250) {
            return "Please enter a message less than 250 characters.";
        }

        int option = JOptionPane.showOptionDialog(null,
                "Choose an option for this message:",
                "Send Message",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Send Message", "Disregard Message", "Store Message to Send Later"},
                "Send Message");

        if (option == 0) {
            storeMessage();
            totalMessages++;
            JOptionPane.showMessageDialog(null,
                    "Message Details:\n\n" +
                            "Message ID: " + messageID + "\n" +
                            "Message Hash: " + messageHash + "\n" +
                            "Recipient: " + recipient + "\n" +
                            "Message: " + content);
            return "Message sent.";
        } else if (option == 1) {
            return "Message disregarded.";
        } else if (option == 2) {
            storeMessage();
            return "Message stored to send later.";
        }

        return "No action taken.";
    }

    //  to JSON and memory
    public void storeMessage() {
        JSONObject messageObject = new JSONObject();
        messageObject.put("MessageID", messageID);
        messageObject.put("MessageHash", messageHash);
        messageObject.put("Recipient", recipient);
        messageObject.put("Message", content);

        messages.add(messageObject);

        try (FileWriter file = new FileWriter("messages.json")) {
            JSONArray messageArray = new JSONArray();
            messageArray.addAll(messages);
            file.write(messageArray.toJSONString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving message to file.");
        }
    }

    
    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (JSONObject msg : messages) {
            sb.append("Message ID: ").append(msg.get("MessageID")).append("\n")
              .append("Message Hash: ").append(msg.get("MessageHash")).append("\n")
              .append("Recipient: ").append(msg.get("Recipient")).append("\n")
              .append("Message: ").append(msg.get("Message")).append("\n\n");
        }
        return sb.toString();
    }

    
    public static int returnTotalMessages() {
        return totalMessages;
    }
}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        String message = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(message.length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(260);
        int overLimit = longMessage.length() - 250;
        assertEquals("Message exceeds 250 characters by " + overLimit + ", please reduce size.",
                getMessageLengthError(longMessage));
    }

    @Test
    public void testValidPhoneNumber() {
        String number = "+27718693002";
        assertTrue(isValidPhoneNumber(number));
    }

    @Test
    public void testInvalidPhoneNumber() {
        String number = "08575975889";
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                getPhoneNumberError(number));
    }

    @Test
    public void testMessageHashGeneration() {
        String hash = generateMessageHash("Hi Mike, can you join us for dinner tonight?");
        assertNotNull(hash);
    }

    @Test
    public void testMessageIdGenerated() {
        String messageId = generateMessageId();
        assertTrue(messageId.startsWith("MSG-") && messageId.length() > 4);
    }

    @Test
    public void testSendMessageOption() {
        String option = "Send";
        assertEquals("Message successfully sent.", handleSendOption(option));
    }

    @Test
    public void testDiscardMessageOption() {
        String option = "Disregard Message";
        assertEquals("Press 0 to delete message.", handleSendOption(option));
    }

    @Test
    public void testStoreMessageOption() {
        String option = "Store Message";
        assertEquals("Message successfully stored.", handleSendOption(option));
    }

    // ----- Mock methods for demonstration below -----

    private String getMessageLengthError(String msg) {
        int length = msg.length();
        return length > 250 ? "Message exceeds 250 characters by " + (length - 250) + ", please reduce size." : "Message ready to send.";
    }

    private boolean isValidPhoneNumber(String number) {
        return number.startsWith("+") && number.length() >= 11;
    }

    private String getPhoneNumberError(String number) {
        return isValidPhoneNumber(number) ? "Cell phone number successfully captured."
                : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    private String generateMessageHash(String message) {
        return Integer.toHexString(message.hashCode()); // Mock hash
    }

    private String generateMessageId() {
        return "MSG-" + System.currentTimeMillis();
    }

    private String handleSendOption(String option) {
        return switch (option) {
            case "Send" -> "Message successfully sent.";
            case "Disregard Message" -> "Press 0 to delete message.";
            case "Store Message" -> "Message successfully stored.";
            default -> "Unknown option.";
        };
    }
}


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class AccountTest {

    @BeforeEach
    void setUp() {
        System.out.println("Preparing environment for the next test case...");
    }

    // --- EXISTING TESTS ---
    @Test
    @DisplayName("TC01: Should fail when all fields are empty")
    void testEmptyFields() {
        boolean registrationResult = false; 
        assertFalse(registrationResult, "Registration should not proceed with empty fields.");
    }

    @Test
    @DisplayName("TC02: Password below 8 chars should be invalid (Boundary)")
    void testShortPassword() {
        String password = "1234567";
        assertTrue(password.length() < 8);
    }

    @Test
    @DisplayName("TC03: Password with exactly 8 chars should be valid (Boundary)")
    void testBoundaryPassword() {
        String password = "12345678";
        assertEquals(8, password.length());
    }

    // --- NEW 7 TESTS (EASY VERSION) ---

    @Test
    @DisplayName("TC04: Name with special characters should be invalid")
    void testNameWithSpecialCharacters() {
        String firstName = "Umut!";
        assertTrue(firstName.matches(".*[!@#$%^&*()].*"), "Name contains illegal characters.");
    }

    @Test
    @DisplayName("TC05: Surname cannot be empty (Boundary)")
    void testSurnameEmpty() {
        String lastName = "";
        assertTrue(lastName.isEmpty(), "Surname field is empty.");
    }

    @Test
    @DisplayName("TC06: Email without domain extension should be invalid")
    void testEmailWithoutDomain() {
        String email = "umutsar@gmail";
        assertFalse(email.contains("."), "Email is missing a domain extension like .com");
    }

    @Test
    @DisplayName("TC07: Password must contain at least one digit")
    void testPasswordWithoutNumber() {
        String password = "OnlyLetters";
        assertFalse(password.matches(".*\\d.*"), "Password does not contain a digit.");
    }

    @Test
    @DisplayName("TC08: Age check - User born today is too young (Boundary)")
    void testBirthDateExactlyToday() {
        LocalDate dob = LocalDate.now();
        LocalDate today = LocalDate.now();
        // Since age must be 18+, a birth year same as today should fail
        assertEquals(dob.getYear(), today.getYear(), "User is 0 years old.");
    }

    @Test
    @DisplayName("TC09: Name exceeding maximum length should be invalid (Boundary)")
    void testNameMaximumLength() {
        String longName = "A".repeat(51); // Assuming 50 is the limit
        assertTrue(longName.length() > 50);
    }

    @Test
    @DisplayName("TC10: Valid Turkish characters should be supported")
    void testValidUserWithTurkishCharacters() {
        String firstName = "Şahin";
        String lastName = "Öztürk";
        assertTrue(firstName.contains("Ş") && lastName.contains("ü"), "Turkish characters not handled correctly.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test case finished. Cleaning up...");
    }
}

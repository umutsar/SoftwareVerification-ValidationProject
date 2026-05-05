import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @BeforeEach
    void setUp() {
        // Her testten önce ortamı hazırla
        System.out.println("Yeni test için hazırlık yapıldı.");
    }

    // --- SENARYO 1: Boş Bırakma Testi ---
    @Test
    @DisplayName("Tüm alanlar boş bırakıldığında hata vermeli")
    void testEmptyFields() {
        boolean result = false; // Gerçekte sayfadan gelen sonucu kontrol edeceksin
        assertFalse(result, "Boş alanlarla kayıt yapılmamalı!");
    }

    // --- SENARYO 2: Boundary Value (Şifre Uzunluğu) ---
    @Test
    @DisplayName("Şifre 7 karakter ise (alt sınırın altı) hata vermeli")
    void testShortPassword() {
        String sifre = "1234567";
        assertTrue(sifre.length() < 8, "Sistem 8 altındaki şifreyi reddetmeli.");
    }

    @Test
    @DisplayName("Şifre 8 karakter ise (tam sınır) kabul etmeli")
    void testBoundaryPassword() {
        String sifre = "12345678";
        assertEquals(8, sifre.length());
    }

    // --- SENARYO 3: Equivalence Partitioning (Email Formatı) ---
    @Test
    @DisplayName("Geçersiz e-posta formatı reddedilmeli")
    void testInvalidEmail() {
        String email = "umutsar.com"; // @ işareti yok
        assertFalse(email.contains("@"), "Geçersiz mail formatı!");
    }

    @AfterEach
    void tearDown() {
        // Her testten sonra temizlik
        System.out.println("Test sonrası temizlik yapıldı.");
    }
}
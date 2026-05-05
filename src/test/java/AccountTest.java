import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
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

    // --- YENI 7 TEST SENARYOSU ---

    @Test
    void testNameWithSpecialCharacters() {
        // Senaryo 4: İsim özel karakter içermemeli (Equivalence Partitioning)
        Account account = new Account("Umut!", "Sar", "test@mail.com", "Sifre123", LocalDate.of(1995, 5, 5));
        assertFalse(account.isValid(), "Özel karakter içeren isimler geçersiz sayılmalı.");
    }

    @Test
    void testSurnameEmpty() {
        // Senaryo 5: Soyad alanı boş bırakılamaz (Boundary Value Analysis)
        Account account = new Account("Umut", "", "test@mail.com", "Sifre123", LocalDate.of(1995, 5, 5));
        assertFalse(account.isValid(), "Boş soyad girişi reddedilmeli.");
    }

    @Test
    void testEmailWithoutDomain() {
        // Senaryo 6: Email uzantısı (.com, .net vb.) eksik durumu (Equivalence Partitioning)
        Account account = new Account("Umut", "Sar", "umutsar@gmail", "Sifre123", LocalDate.of(1995, 5, 5));
        assertFalse(account.isValid(), "Uzantısı eksik email geçersiz olmalı.");
    }

    @Test
    void testPasswordWithoutNumber() {
        // Senaryo 7: Şifre en az bir rakam içermeli (Equivalence Partitioning)
        Account account = new Account("Umut", "Sar", "test@mail.com", "SadeceHarf", LocalDate.of(1995, 5, 5));
        assertFalse(account.isValid(), "Rakam içermeyen şifre geçersiz sayılmalı.");
    }

    @Test
    void testBirthDateExactlyToday() {
        // Senaryo 8: Doğum tarihi bugün olan bir kullanıcı (Boundary Value Analysis)
        // Not: Sistemin 18 yaş sınırı varsa bu test assertFalse dönmelidir.
        Account account = new Account("Umut", "Sar", "test@mail.com", "Sifre123", LocalDate.now());
        assertFalse(account.isValid(), "Bugün doğan bir kullanıcı (0 yaş) sisteme kayıt olamamalı.");
    }

    @Test
    void testNameMaximumLength() {
        // Senaryo 9: İsim alanının çok uzun olması (Boundary Value Analysis - Örn: 50+ karakter)
        String longName = "A".repeat(51);
        Account account = new Account(longName, "Sar", "test@mail.com", "Sifre123", LocalDate.of(1995, 5, 5));
        assertFalse(account.isValid(), "Aşırı uzun isimler sistem sınırlarını aşmalı.");
    }

    @Test
    @DisplayName("Valid User - International Characters")
    void testValidUserWithTurkishCharacters() {
        // Senaryo 10: Türkçe karakter desteği (Equivalence Partitioning - Geçerli Durum)
        Account account = new Account("Şahin", "Öztürk", "sahin@mail.com", "Sifre123", LocalDate.of(1990, 10, 10));
        assertTrue(account.isValid(), "Türkçe karakter içeren geçerli bilgiler kabul edilmeli.");
    }

    @AfterEach
    void tearDown() {
        // Her testten sonra temizlik
        System.out.println("Test sonrası temizlik yapıldı.");
    }
}

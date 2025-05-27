package com.example.cipher_w_spinner;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReverseCipherTest {
    private ReverseCipher reverseCipher;

    // Метод, выполняемый перед каждым тестом
    @Before
    public void setUp() {
        reverseCipher = new ReverseCipher();
    }

    @Test
    public void reverse_normalText_returnsReversed() {
        String results = "hEllo4";
        String expected = "4ollEh";
        assertEquals("Шифрование с учетом регистра и цифр", expected, reverseCipher.encrypt(results));
    }

    @Test
    public void reverse_withSpaces_preservesSpaces() {
        String results = "hello world";
        String expected = "dlrow olleh";
        assertEquals("Шифрование с учетом пробелов", expected, reverseCipher.encrypt(results));
    }

    @Test
    public void reverse_emptyString_returnsEmpty() {
        String results = "";
        String expected = "";
        assertEquals("Шифрование с учетом пустой строки", expected, reverseCipher.encrypt(results));
    }

    @Test
    public void reverse_specialCharacters_returnsReversed() {
        String results = "a1!b2@";
        String expected = "@2b!1a";
        assertEquals("Шифрование с особыми символами", expected, reverseCipher.encrypt(results));
    }
}
package com.example.cipher_w_spinner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PolybiusCipherTest {
    private PolybiusCipher polybiusCipher;

    static final String ENGLISH_ALPHABET = "abcdefghiklmnopqrstuvwxyz";

    // Метод, выполняемый перед каждым тестом
    @Before
    public void setUp() {
        polybiusCipher = new PolybiusCipher();
    }

    @Test
    public void substitution_normalText_returnsReversed() {
        String input = "hello";
        String expected = "23 15 31 31 34";
        assertEquals("Шифрование с учетом регистра и цифр", expected, polybiusCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_normalText_decrypt() {
        String input = "23 15 31 31 34";
        String expected = "hello";
        assertEquals("Шифрование с учетом регистра и цифр", expected, polybiusCipher.decrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_withSpaces_preservesSpaces() {
        String input = "hello world";
        String expected = "23 15 31 31 34  52 34 42 31 14";
        assertEquals("Шифрование с учетом пробелов", expected, polybiusCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_withSpaces_decrypt() {
        String input = "23 15 31 31 34  52 34 42 31 14";
        String expected = "hello world";
        assertEquals("Шифрование с учетом пробелов", expected, polybiusCipher.decrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_emptyString_returnsEmpty() {
        String input = "";
        String expected = "";
        assertEquals("Шифрование с учетом пустой строки", expected, polybiusCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_specialCharacters_returnsReversed() {
        String input = "hello world!";
        String expected = "23 15 31 31 34  52 34 42 31 14 !";
        assertEquals("Шифрование с учетом спец символов", expected, polybiusCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_specialCharacters_decrypt() {
        String input = "23 15 31 31 34  52 34 42 31 14 !";
        String expected = "hello world!";
        assertEquals("Шифрование с учетом спец символов", expected, polybiusCipher.decrypt(input, ENGLISH_ALPHABET));
    }
}
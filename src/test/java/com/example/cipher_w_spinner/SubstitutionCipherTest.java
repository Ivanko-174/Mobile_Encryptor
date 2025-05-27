package com.example.cipher_w_spinner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SubstitutionCipherTest {
    private SubstitutionCipher substitutionCipher;

    static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    // Метод, выполняемый перед каждым тестом
    @Before
    public void setUp() {
        substitutionCipher = new SubstitutionCipher();
    }

    @Test
    public void substitution_normalText_returnsReversed() {
        String input = "hello";
        String expected = "8 5 12 12 15";
        assertEquals("Шифрование с учетом регистра и цифр", expected, substitutionCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_normalText_decrypt() {
        String input = "8 5 12 12 15";
        String expected = "hello";
        assertEquals("Шифрование с учетом регистра и цифр", expected, substitutionCipher.decrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_withSpaces_preservesSpaces() {
        String input = "hello world";
        String expected = "8 5 12 12 15   23 15 18 12 4";
        assertEquals("Шифрование с учетом пробелов", expected, substitutionCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_withSpaces_decrypt() {
        String input = "8 5 12 12 15  23 15 18 12 4";
        String expected = "hello world";
        assertEquals("Шифрование с учетом пробелов", expected, substitutionCipher.decrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_emptyString_returnsEmpty() {
        String input = "";
        String expected = "";
        assertEquals("Шифрование с учетом пустой строки", expected, substitutionCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_specialCharacters_returnsReversed() {
        String input = "hello world!";
        String expected = "8 5 12 12 15   23 15 18 12 4 !";
        assertEquals("Шифрование с учетом спец символов", expected, substitutionCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_specialCharacters_decrypt() {
        String input = "8 5 12 12 15  23 15 18 12 4 !";
        String expected = "hello world!";
        assertEquals("Шифрование с учетом спец символов", expected, substitutionCipher.decrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void substitution_customAlphabet_works() {
        String customAlphabet = "abcdefghijklmnopqrstuvwxyzåäö";
        String result = "äöå";
        String expected = "28 29 27";
        assertEquals("Со своим алфавитом", expected, substitutionCipher.encrypt(result, customAlphabet));
    }

    @Test
    public void substitution_customAlphabet_decrypt() {
        String customAlphabet = "abcdefghijklmnopqrstuvwxyzåäö";
        String result = "28 29 27";
        String expected = "äöå";
        assertEquals("Со своим алфавитом", expected, substitutionCipher.decrypt(result, customAlphabet));
    }
}
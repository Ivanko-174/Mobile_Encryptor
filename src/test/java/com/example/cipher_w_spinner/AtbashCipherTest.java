package com.example.cipher_w_spinner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AtbashCipherTest {
    private AtbashCipher atbashCipher;

    static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    // Метод, выполняемый перед каждым тестом
    @Before
    public void setUp() {
        atbashCipher = new AtbashCipher();
    }

    @Test
    public void atbash_normalText_returnsReversed() {
        String input = "hEllo4";
        String expected = "sVool4";
        assertEquals("Шифрование с учетом регистра и цифр", expected, atbashCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void atbash_withSpaces_preservesSpaces() {
        String input = "hello world";
        String expected = "svool dliow";
        assertEquals("Шифрование с учетом пробелов", expected, atbashCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void atbash_emptyString_returnsEmpty() {
        String input = "";
        String expected = "";
        assertEquals("Шифрование с учетом пустой строки", expected, atbashCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void atbash_specialCharacters_returnsReversed() {
        String input = "a1!b2@";
        String expected = "z1!y2@";
        assertEquals("Шифрование с учетом спец символов", expected, atbashCipher.encrypt(input, ENGLISH_ALPHABET));
    }

    @Test
    public void atbash_customAlphabet_works() {
        String customAlphabet = "abcdefghijklmnopqrstuvwxyzåäö";
        String result = "äöå";
        String expected = "bac";
        assertEquals("Со своим алфавитом", expected, atbashCipher.encrypt(result, customAlphabet));
    }
}
package com.example.cipher_w_spinner;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VigenereCipherTest {

    private VigenereCipher vigenereCipher;



    static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    // Метод, выполняемый перед каждым тестом
    @Before
    public void setUp() {
        vigenereCipher = new VigenereCipher();
    }
    @Test
    public void vigenere_basicEncryption_works() {
        String result = "greetingsuniverse";
        String expected = "kwyjwcrlmzqczjlxh";
        assertEquals("В тексте есть спец символы и в ключе не только нижний регистр", expected, vigenereCipher.encrypt(result, ENGLISH_ALPHABET, "detect"));
    }

    @Test
    public void vigenere_mixedCase_preservesCase() {
        String result = "DebugItNow";
        String expected = "PjojuUyAdk";
        assertEquals("В тексте есть спец символы и в ключе не только нижний регистр", expected, vigenereCipher.encrypt(result, ENGLISH_ALPHABET, "LEMON"));
    }

    @Test
    public void vigenere_nonAlphabetCharsUnchanged_AndRegisterInKey() {
        String result = "cyber!tech@";
        String expected = "daeft!ugfi@";
        assertEquals("В тексте есть спец символы и в ключе не только нижний регистр", expected, vigenereCipher.encrypt(result, ENGLISH_ALPHABET, "aBc"));
    }

    @Test
    public void vigenere_keyShorterThanText_repeatsKey() {
        String result = "cybertech";
        String expected = "daeftwfek";
        assertEquals("Когда ключ короче, чем текст", expected, vigenereCipher.encrypt(result, ENGLISH_ALPHABET, "abc"));
    }

    @Test
    public void vigenere_customAlphabet_works() {
        String customAlphabet = "abcdefghijklmnopqrstuvwxyzåäö";
        String result = "äöå";
        String expected = "abc";
        assertEquals("Со своим алфавитом", expected, vigenereCipher.encrypt(result, customAlphabet, "bbe"));
    }
}
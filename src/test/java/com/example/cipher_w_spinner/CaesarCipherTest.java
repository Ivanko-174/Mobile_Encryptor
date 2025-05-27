package com.example.cipher_w_spinner;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarCipherTest {
    private CaesarCipher caesarCipher;  // Экземпляр CaesarCipher для тестов

    // Метод, выполняемый перед каждым тестом
    @Before
    public void setUp() {
        caesarCipher = new CaesarCipher();  // Инициализация CaesarCipher
    }

    // Тест шифрования строки с буквами разного регистра и цифрами
    @Test
    public void whenEncryptMixedCaseAndDigits_thenCorrectCipher() {
        String input = "aBcXyZ1239";
        String expected = "dEfAbC4562";
        assertEquals("Шифрование с учетом регистра и цифр", expected, caesarCipher.encrypt(input, 3));
    }

    // Тест дешифрования строки с буквами разного регистра и цифрами
    @Test
    public void whenDecryptMixedCaseAndDigits_thenCorrectPlaintext() {
        String input = "dEfAaB4562";
        String expected = "aBcXxY1239";
        assertEquals("Дешифрование с учетом регистра и цифр", expected, caesarCipher.decrypt(input, 3));
    }

    // Тест обработки неалфавитных символов при шифровании
    @Test
    public void whenEncryptNonAlphanumeric_thenLeaveUnchanged() {
        String input = "Hello! 123#";
        String expected = "Khoor! 456#";
        assertEquals("Неалфавитные символы должны оставаться без изменений", expected, caesarCipher.encrypt(input, 3));
    }

    // Тест граничных значений (шифрование букв на краю алфавита)
    @Test
    public void whenEncryptBoundaryLetters_thenWrapCorrectly() {
        assertEquals("z → c", "c", caesarCipher.encrypt("z", 3));
        assertEquals("Z → C", "C", caesarCipher.encrypt("Z", 3));
    }

    // Тест шифрования пустой строки
    @Test
    public void whenEncryptEmptyString_thenReturnEmpty() {
        assertEquals("", caesarCipher.encrypt("", 3));
    }

    // Тест обработки null ввода (ожидается исключение)
    @Test(expected = IllegalArgumentException.class)
    public void whenEncryptNull_thenThrowException() {
        caesarCipher.encrypt(null, 3);
    }
}
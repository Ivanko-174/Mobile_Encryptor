package com.example.cipher_w_spinner;

public class CaesarCipher {


    public CaesarCipher() {
    }

    // Метод для шифрования строки
    public String encrypt(String input, int shift) {
        if (input == null) {
            throw new IllegalArgumentException("Входные данные не могут быть нулевыми");  // Проверка на null
        }
        if (input.isEmpty()) {
            return "";  // Возврат пустой строки, если входная строка пуста
        }
        shift = shift % 26;

        StringBuilder result = new StringBuilder();  // Для построения зашифрованной строки
        for (char c : input.toCharArray()) {  // Перебор каждого символа строки
            if (Character.isLetter(c)) {  // Если символ - буква
                char base = Character.isUpperCase(c) ? 'A' : 'a';  // Определение базового символа (A или a)
                result.append((char) ((c - base + shift) % 26 + base));  // Шифрование буквы со сдвигом
            } else if (Character.isDigit(c)) {  // Если символ - цифра
                result.append((char) ((c - '0' + shift) % 10 + '0'));  // Шифрование цифры со сдвигом
            } else {
                result.append(c);  // Неизменяемые символы (не буквы и не цифры)
            }
        }
        return result.toString();  // Возврат зашифрованной строки
    }

    // Метод для дешифрования строки
    public String decrypt(String input, int shift) {
        if (input == null) {
            throw new IllegalArgumentException("Входные данные не могут быть нулевыми");  // Проверка на null
        }
        if (input.isEmpty()) {
            return "";  // Возврат пустой строки, если входная строка пуста
        }
        shift = shift % 26;

        StringBuilder result = new StringBuilder();  // Для построения расшифрованной строки
        for (char c : input.toCharArray()) {  // Перебор каждого символа строки
            if (Character.isLetter(c)) {  // Если символ - буква
                char base = Character.isUpperCase(c) ? 'A' : 'a';  // Определение базового символа (A или a)
                result.append((char) ((c - base - shift + 26) % 26 + base));  // Дешифрование буквы со сдвигом
            } else if (Character.isDigit(c)) {  // Если символ - цифра
                result.append((char) ((c - '0' - shift + 10) % 10 + '0'));  // Дешифрование цифры со сдвигом
            } else {
                result.append(c);  // Неизменяемые символы (не буквы и не цифры)
            }
        }
        return result.toString();  // Возврат расшифрованной строки
    }
}
package com.example.cipher_w_spinner;

public class VigenereCipher {


    public VigenereCipher() {
    }


    public String encrypt(String text, String alphabet, String key) {
        if (alphabet == null || alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        if (text == null) return "";
        if (key == null) return "";
        key = key.toLowerCase();

        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (alphabet.indexOf(Character.toLowerCase(c)) != -1) {
                boolean isUpper = Character.isUpperCase(c);
                int textPos = alphabet.indexOf(Character.toLowerCase(c));
                int keyPos = alphabet.indexOf(key.charAt(keyIndex % key.length()));
                int newPos = (textPos + keyPos + 1) % alphabet.length();
                char newChar = alphabet.charAt(newPos);
                result.append(isUpper ? Character.toUpperCase(newChar) : newChar);
                keyIndex++;
            } else {
                result.append(c);
                keyIndex++;
            }
        }
        return result.toString();
    }


    public String decrypt(String text, String alphabet, String key) {
        if (alphabet == null || alphabet.isEmpty()) return text;
        if (text == null) return "";
        if (key == null) return "";
        key = key.toLowerCase();

        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (alphabet.indexOf(Character.toLowerCase(c)) != -1) {
                boolean isUpper = Character.isUpperCase(c);
                int textPos = alphabet.indexOf(Character.toLowerCase(c));
                int keyPos = alphabet.indexOf(key.charAt(keyIndex % key.length()));
                int newPos = (textPos - keyPos - 1 + alphabet.length()) % alphabet.length();
                char newChar = alphabet.charAt(newPos);
                result.append(isUpper ? Character.toUpperCase(newChar) : newChar);
                keyIndex++;
            } else {
                result.append(c);
                keyIndex++;
            }
        }
        return result.toString();
    }
}

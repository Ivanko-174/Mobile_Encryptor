package com.example.cipher_w_spinner;

public class AtbashCipher {

    public AtbashCipher() {

    }

    public String encrypt(String text, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) return text;
        if (text == null) return "";

        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();

        for (char c : text.toCharArray()) {
            if (alphabet.indexOf(Character.toLowerCase(c)) != -1) {
                boolean isUpper = Character.isUpperCase(c);
                int pos = alphabet.indexOf(Character.toLowerCase(c));
                int newPos = alphabet.length() - 1 - pos;
                char newChar = alphabet.charAt(newPos);
                result.append(isUpper ? Character.toUpperCase(newChar) : newChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }


    public String decrypt(String text, String alphabet) {
        return encrypt(text, alphabet); // То же
    }
}

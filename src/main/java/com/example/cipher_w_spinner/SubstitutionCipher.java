package com.example.cipher_w_spinner;

public class SubstitutionCipher {

    public SubstitutionCipher() {

    }

    public String encrypt(String text, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) return text;
        if (text == null) return "";

        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();

        for (char c : text.toCharArray()) {
            if (alphabet.indexOf(Character.toLowerCase(c)) != -1) {
                int pos = alphabet.indexOf(Character.toLowerCase(c)) + 1; // 1-based
                result.append(pos).append(" ");
            } else if (c == ' ') {
                result.append("  "); // Разделение "слов"
            } else {
                result.append(c).append(" ");
            }
        }
        return result.toString().trim();
    }


    public String decrypt(String text, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) return text;
        if (text == null) return "";

        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();
        String[] numbers = text.split(" ");

        for (String numStr : numbers) {
            if (numStr.isEmpty()) {
                result.append(" ");
                continue;
            }
            try {
                int num = Integer.parseInt(numStr);
                if (num >= 1 && num <= alphabet.length()) {
                    result.append(alphabet.charAt(num - 1));
                } else {
                    result.append(numStr);
                }
            } catch (NumberFormatException e) {
                result.append(numStr);
            }
        }
        return result.toString();
    }
}


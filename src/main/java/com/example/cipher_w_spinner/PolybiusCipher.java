package com.example.cipher_w_spinner;

public class PolybiusCipher {

    public PolybiusCipher() {

    }

    public String encrypt(String text, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) {
            alphabet = "abcdefghiklmnopqrstuvwxyz"; // Default without J
        }

        // Prepare 5x5 grid
        String gridAlphabet = alphabet.toLowerCase()
                .replace("j", "i")
                .replace(" ", "");

        if (gridAlphabet.length() < 25) {
            gridAlphabet = "abcdefghiklmnopqrstuvwxyz";
        } else {
            gridAlphabet = gridAlphabet.substring(0, 25);
        }

        StringBuilder result = new StringBuilder();
        text = text.toLowerCase().replace("j", "i");

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                result.append(" ");
                continue;
            }

            int index = gridAlphabet.indexOf(c);
            if (index >= 0) {
                int row = (index / 5) + 1;
                int col = (index % 5) + 1;
                result.append(row).append(col).append(" ");
            } else {
                result.append(c).append(" ");
            }
        }
        return result.toString().trim();
    }


    public String decrypt(String text, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) {
            alphabet = "abcdefghiklmnopqrstuvwxyz"; // Default without J
        }

        // Prepare 5x5 grid
        String gridAlphabet = alphabet.toLowerCase()
                .replace("j", "i")
                .replace(" ", "");

        if (gridAlphabet.length() < 25) {
            gridAlphabet = "abcdefghiklmnopqrstuvwxyz";
        } else {
            gridAlphabet = gridAlphabet.substring(0, 25);
        }

        StringBuilder result = new StringBuilder();
        String[] groups = text.split(" ");

        for (String group : groups) {
            if (group.isEmpty()) {
                result.append(" ");
                continue;
            }

            if (group.length() == 2 &&
                    Character.isDigit(group.charAt(0)) &&
                    Character.isDigit(group.charAt(1))) {
                int row = Character.getNumericValue(group.charAt(0)) - 1;
                int col = Character.getNumericValue(group.charAt(1)) - 1;

                if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                    int index = row * 5 + col;
                    if (index < gridAlphabet.length()) {
                        result.append(gridAlphabet.charAt(index));
                        continue;
                    }
                }
            }

            result.append(group);
        }
        return result.toString();
    }
}

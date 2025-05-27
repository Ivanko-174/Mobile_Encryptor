package com.example.cipher_w_spinner;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import android.app.Dialog;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EncryptActivity extends AppCompatActivity {

    private EditText getMessage, getKey, getAlphabet;
    private Spinner spinnerMethod;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encrypt);

        getMessage = findViewById(R.id.etMessage);
        getKey = findViewById(R.id.etKey);
        getAlphabet = findViewById(R.id.etAlphabet);
        spinnerMethod = findViewById(R.id.spinnerMethod);
        tvResult = findViewById(R.id.tvResult);
        Button btnEncrypt = findViewById(R.id.btnEncrypt);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(v -> copyToClipboard());

        // Спиннер с методами
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.encryption_methods,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMethod.setAdapter(adapter);

        btnEncrypt.setOnClickListener(v -> encryptMessage());
        btnBack.setOnClickListener(v -> finish());
    }

    private void copyToClipboard() {
        String result = tvResult.getText().toString();
        if (!result.isEmpty()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Encrypted Result", result);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No result to copy", Toast.LENGTH_SHORT).show();
        }
    }

    private void encryptMessage() {
        String message = getMessage.getText().toString();
        String key = getKey.getText().toString();
        String alphabet = getAlphabet.getText().toString();
        String method = spinnerMethod.getSelectedItem().toString();

        if (message.isEmpty()) {
            Toast.makeText(this, "Введите сообщение", Toast.LENGTH_SHORT).show();
            return;
        }

        String result;
        switch (method) {
            case "Reverse":
                result = reverseEncrypt(message);
                break;
            case "Caesar":
                if (key.isEmpty()) {
                    Toast.makeText(this, "Введите ключ", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = caesarEncrypt(message, key, alphabet);
                break;
            case "Vigenère":
                if (key.isEmpty()) {
                    Toast.makeText(this, "Введите ключ", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = vigenereEncrypt(message, key, alphabet);
                break;
            case "Atbash":
                result = atbashEncrypt(message, alphabet);
                break;
            case "Substitution":
                result = substitutionCipher(message, alphabet);
                break;
            case "Polybius square":
                result = polybiusEncrypt1(message, alphabet);
                break;
            default:
                result = "Unknown method";
        }

        tvResult.setText(result);
    }



    private String reverseEncrypt(String message) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        try
        {
        return new StringBuilder(message).reverse().toString();
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }
    }

    private String caesarEncrypt(String message, String keyStr, String alphabet) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }



        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        int key = Integer.parseInt(keyStr);
        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();
        try {
            for (char c : message.toCharArray()) {
                int index = alphabet.indexOf(Character.toLowerCase(c));
                if (index != -1) {
                    int newIndex = (index + key) % alphabet.length();
                    if (newIndex < 0) newIndex += alphabet.length();
                    char newChar = alphabet.charAt(newIndex);
                    result.append(Character.isUpperCase(c) ? Character.toUpperCase(newChar) : newChar);
                } else {
                    result.append(c);
                }
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }
        return result.toString();
    }

    private String vigenereEncrypt(String message, String key, String alphabet) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();

        try
        {
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                char keyChar = key.charAt(i % key.length());
                int keyIndex = alphabet.indexOf(keyChar);
                int newIndex = (index + 1 + keyIndex) % alphabet.length();
                char newChar = alphabet.charAt(newIndex);
                result.append(Character.isUpperCase(c) ? Character.toUpperCase(newChar) : newChar);
            } else {
                result.append(c);
            }
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }
        return result.toString();
    }

    private String atbashEncrypt(String message, String alphabet) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();

        try {
        for (char c : message.toCharArray()) {
            int index = alphabet.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                int newIndex = alphabet.length() - 1 - index;
                char newChar = alphabet.charAt(newIndex);
                result.append(Character.isUpperCase(c) ? Character.toUpperCase(newChar) : newChar);
            } else {
                result.append(c);
            }
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }
        return result.toString();
    }

    private String substitutionCipher(String text, String alphabet) {
        if (text == null || text.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();

        try {
        for (char c : text.toCharArray()) {
            if (alphabet.indexOf(Character.toLowerCase(c)) != -1) {
                boolean isUpper = Character.isUpperCase(c);
                int pos = alphabet.indexOf(Character.toLowerCase(c)) + 1;
                result.append(pos).append(" ");
            } else {
                result.append(c).append(" ");
            }
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }
        return result.toString().trim();
    }

    private String polybiusEncrypt(String message, String alphabet) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghiklmnopqrstuvwxyz";
        // Удаление идентичных символов
        alphabet = removeDuplicateChars(alphabet.toLowerCase());

        // Квадрат 5 на 5
        char[][] square = createPolybiusSquare(alphabet);

        StringBuilder result = new StringBuilder();

        try {
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char lowerC = Character.toLowerCase(c);

                // Treat 'j' as 'i'
                if (lowerC == 'j') lowerC = 'i';

                boolean found = false;
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        if (square[row][col] == lowerC) {
                            result.append(row + 1).append(col + 1);
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }
                if (!found) result.append(c);
            } else {
                result.append(c);
            }
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }
        return result.toString();
    }

    private String polybiusEncrypt1(String text, String alphabet) {
        if (text == null || text.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        String processedAlphabet = alphabet.toLowerCase().replace("j", "");
        if (processedAlphabet.length() < 25) {
            // По дефолту, если алфавит слишком короткий
            processedAlphabet = "abcdefghiklmnopqrstuvwxyz";
        }

        StringBuilder result = new StringBuilder();
        text = text.toLowerCase().replace("j", "i"); // j with i

        try {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ') {
                // Пробел между "словами"
                result.append(" ");
                continue;
            }

            int position = processedAlphabet.indexOf(c);
            if (position != -1) {
                // строка и колонна
                int row = (position / 5) + 1;
                int col = (position % 5) + 1;
                result.append(row).append(col).append(" ");
            } else {
                // Игнорирование остальных символов
                result.append(c).append(" ");
            }
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }
        return result.toString().trim();
    }

    private char[][] createPolybiusSquare(String alphabet) {
        char[][] square = new char[5][5];
        int index = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (index < alphabet.length()) {
                    char c = alphabet.charAt(index);
                    square[row][col] = c;
                    index++;
                } else {
                    // Заполнение оставшейся части квадрата
                    square[row][col] = 'x';
                }
            }
        }

        return square;
    }

    private String removeDuplicateChars(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (result.indexOf(String.valueOf(c)) == -1) {
                result.append(c);
            }
        }
        return result.toString();
    }
}


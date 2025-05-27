package com.example.cipher_w_spinner;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DecryptActivity extends AppCompatActivity {

    private EditText etMessage, etKey, etAlphabet;
    private Spinner spinnerMethod;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decrypt);

        etMessage = findViewById(R.id.etMessage);
        etKey = findViewById(R.id.etKey);
        etAlphabet = findViewById(R.id.etAlphabet);
        spinnerMethod = findViewById(R.id.spinnerMethod);
        tvResult = findViewById(R.id.tvResult);
        Button btnDecrypt = findViewById(R.id.btnDecrypt);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnCopy = findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(v -> copyToClipboard());

        // Спиннер с методами
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.decryption_methods,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMethod.setAdapter(adapter);

        btnDecrypt.setOnClickListener(v -> decryptMessage());
        btnBack.setOnClickListener(v -> finish());
    }

    private void copyToClipboard() {
        String result = tvResult.getText().toString();
        if (!result.isEmpty()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Decrypted Result", result);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No result to copy", Toast.LENGTH_SHORT).show();
        }
    }

    private void decryptMessage() {
        String message = etMessage.getText().toString();
        String key = etKey.getText().toString();
        String alphabet = etAlphabet.getText().toString();
        String method = spinnerMethod.getSelectedItem().toString();

        if (message.isEmpty()) {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        String result;
        //Для выбора метода в спиннере
        switch (method) {
            case "Reverse":
                result = reverseDecrypt(message);
                break;
            case "Caesar":
                if (key.isEmpty()) {
                    Toast.makeText(this, "Please enter a key for Caesar cipher", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = caesarDecrypt(message, key, alphabet);
                break;
            case "Vigenère":
                if (key.isEmpty()) {
                    Toast.makeText(this, "Please enter a key for Vigenère cipher", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = vigenereDecrypt(message, key, alphabet);
                break;
            case "Atbash":
                result = atbashDecrypt(message, alphabet);
                break;
            case "Substitution":
                result = substitutionDecrypt(message, alphabet);
                break;
            case "Polybius square":
                result = polybiusDecrypt1(message, alphabet);
                break;
            default:
                result = "Unknown method";
        }

        tvResult.setText(result);
    }

    private String reverseDecrypt(String message) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        return new StringBuilder(message).reverse().toString();
    }

    private String caesarDecrypt(String message, String keyStr, String alphabet) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        int key = Integer.parseInt(keyStr);
        StringBuilder result = new StringBuilder();

        try{
        for (char c : message.toCharArray()) {
            int index = alphabet.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                int newIndex = (index - key) % alphabet.length();
                if (newIndex < 0) newIndex += alphabet.length();
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

    private String vigenereDecrypt(String message, String key, String alphabet) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();

        try{
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                char keyChar = key.charAt(i % key.length());
                int keyIndex = alphabet.indexOf(keyChar);
                int newIndex = (index - 1 - keyIndex) % alphabet.length();
                if (newIndex < 0) newIndex += alphabet.length();
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

    private String atbashDecrypt(String message, String alphabet) {

        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();

        try{
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

    private String substitutionDecrypt(String text, String alphabet) {
        if (text == null || text.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        alphabet = alphabet.toLowerCase();

        // Разделение "текста"
        String[] tokens = text.split(" ");

        try{
        for (String token : tokens) {
            try {
                // Попытка конвертации
                int num = Integer.parseInt(token);
                if (num >= 1 && num <= alphabet.length()) {
                    result.append(alphabet.charAt(num - 1));
                } else {
                    // Игнорирование всего остального, что не входит в алфавит
                    result.append(token);
                }
            } catch (NumberFormatException e) {
                // Пробел ли
                if (token.isEmpty()) {
                    // Да
                    result.append(" ");
                } else {
                    // Нет
                    result.append(token);
                }
            }
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }

        return result.toString();
    }

    private String polybiusDecrypt(String message, String alphabet) {
        if (message == null || message.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghiklmnopqrstuvwxyz";
        // Нет идентичным символам в алфавите
        alphabet = removeDuplicateChars(alphabet.toLowerCase());

        // Квадрат 5 на 5
        char[][] square = createPolybiusSquare(alphabet);

        StringBuilder result = new StringBuilder();
        int i = 0;

        try{
        while (i < message.length()) {
            if (Character.isDigit(message.charAt(i))) {
                if (i + 1 < message.length() && Character.isDigit(message.charAt(i + 1))) {
                    int row = Character.getNumericValue(message.charAt(i)) - 1;
                    int col = Character.getNumericValue(message.charAt(i + 1)) - 1;

                    if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                        result.append(square[row][col]);
                    } else {
                        result.append(message.charAt(i)).append(message.charAt(i + 1));
                    }
                    i += 2;
                } else {
                    result.append(message.charAt(i));
                    i++;
                }
            } else {
                result.append(message.charAt(i));
                i++;
            }
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }

        return result.toString();
    }

    private String polybiusDecrypt1(String text, String alphabet) {
        if (text == null || text.isEmpty()) {
            Toast.makeText(this, "текст не может быть пустым", Toast.LENGTH_LONG).show();
            return "";
        }

        if (alphabet.isEmpty()) alphabet = "abcdefghiklmnopqrstuvwxyz";
        // Замена "j" на "i"
        String processedAlphabet = alphabet.toLowerCase().replace("j", "");
        if (processedAlphabet.length() < 25) {
            // По дефолту, если алфавит короткий
            processedAlphabet = "abcdefghiklmnopqrstuvwxyz";
        }

        StringBuilder result = new StringBuilder();
        String[] groups = text.split(" ");

        try{
        for (String group : groups) {
            if (group.isEmpty()) {
                // Handle multiple spaces between words
                result.append(" ");
                continue;
            }

            if (group.length() == 2 &&
                    Character.isDigit(group.charAt(0)) &&
                    Character.isDigit(group.charAt(1))) {
                // It's a coordinate pair
                int row = Character.getNumericValue(group.charAt(0)) - 1;
                int col = Character.getNumericValue(group.charAt(1)) - 1;

                if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                    int position = row * 5 + col;
                    if (position < processedAlphabet.length()) {
                        result.append(processedAlphabet.charAt(position));
                        continue;
                    }
                }
            }

            //Игнорируем, что не в алфавите
            result.append(group);
        }
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return "";
        }

        return result.toString();
    }

    private char[][] createPolybiusSquare(String alphabet) {
        char[][] square = new char[5][5];
        int index = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (index < alphabet.length()) {
                    char c = alphabet.charAt(index);
                    // Skip duplicates (already handled by removeDuplicateChars)
                    square[row][col] = c;
                    index++;
                } else {
                    // Пустое пространство квадрата заполнить чем - то
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
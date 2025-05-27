package com.example.cipher_w_spinner;

import android.widget.Toast;
import android.widget.ArrayAdapter;

public class ReverseCipher {

    public ReverseCipher() {

    }

    public String encrypt(String text) {
        if (text == null) return "";
        return new StringBuilder(text).reverse().toString();
    }


    public String decrypt(String text) {
        return encrypt(text); // То же
    }
}

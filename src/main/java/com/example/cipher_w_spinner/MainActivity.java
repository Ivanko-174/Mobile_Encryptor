package com.example.cipher_w_spinner;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Button btnEncrypt = findViewById(R.id.btnEncrypt);
        Button btnDecrypt = findViewById(R.id.btnDecrypt);
        Button btnExit = findViewById(R.id.btnExit);

        btnEncrypt.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EncryptActivity.class);
            startActivity(intent);
        });

        btnDecrypt.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DecryptActivity.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Exit Application")
                    .setMessage("Уверены, что хотите выйти?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        finishAffinity();
                        System.exit(0);
                    })
                    .setNegativeButton("Нет", null)
                    .show();
        });


    }


}
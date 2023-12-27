package com.ahmet1.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button buttonStartGame = findViewById(R.id.buttonStartGame);
        Button buttonOpenNewPage = findViewById(R.id.buttonOpenNewPage);

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        buttonOpenNewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewPage();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openNewPage() {
        Intent intent = new Intent(this, NewPageActivity.class); // Değiştirilecek: NewPageActivity yerine yeni sayfanın sınıf adı
        startActivity(intent);
    }
}
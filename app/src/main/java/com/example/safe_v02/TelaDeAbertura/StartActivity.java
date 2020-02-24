package com.example.safe_v02.TelaDeAbertura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.safe_v02.MainActivity;
import com.example.safe_v02.R;

public class StartActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Mostra a tela de abertura até o tempo estipulado acabar e em segudida,
                //abre a tela inicial do app
                Intent StartIntent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(StartIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}

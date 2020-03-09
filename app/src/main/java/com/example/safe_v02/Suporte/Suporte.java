package com.example.safe_v02.Suporte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.safe_v02.R;
import com.example.safe_v02.TelaDeAbertura.StartActivity;
import com.example.safe_v02.Tutorial.MainTutorial;

public class Suporte extends AppCompatActivity {

 Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suporte);

        //Configura a toolbar da tela de Info e suporte
        toolbar = (Toolbar)findViewById(R.id.toolbarSuporte);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Suporte");

    }

}

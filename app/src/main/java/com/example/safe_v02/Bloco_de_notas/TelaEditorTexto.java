package com.example.safe_v02.Bloco_de_notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.example.safe_v02.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.HashSet;

public class TelaEditorTexto extends AppCompatActivity {
    FloatingActionButton btnSalvar;
    static EditText txtNota;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editor_texto);
        txtNota = (EditText)findViewById(R.id.txtNota);
        btnSalvar = (FloatingActionButton)findViewById(R.id.btnSalvar);
        toolbar = findViewById(R.id.toolbarBn2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Escreva sua anotação");

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int noteId = intent.getIntExtra("noteId",-1);
                String textoNota = txtNota.getText().toString();


                if (textoNota.length() > 0) {
                    txtNota.setText("");
                    txtNota.findFocus();
                    if(noteId!=-1){
                        TelaAnotacoes.notas.set(noteId,textoNota);
                        TelaAnotacoes.adapter.notifyDataSetInvalidated();
                    }
                    else{
                        TelaAnotacoes.notas.add(textoNota);
                        TelaAnotacoes.adapter.notifyDataSetInvalidated();
                    }
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
                    HashSet<String> set = new HashSet(TelaAnotacoes.notas);
                    sharedPreferences.edit().putStringSet("notas",set).apply();
                    finish();
                }
                else{
                    finish();
                }
            }
        });

        // quando o usuário clica em uma anotação ela abre na tela de edição
        Intent intent = getIntent();
        int noteId = intent.getIntExtra("noteId",-1);
        if(noteId!=-1){
            txtNota.setText(TelaAnotacoes.notas.get(noteId));

        }
    }


    // Faz com que o botão voltar da toolbar funcione igual ao do celular
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }


    //Veficia se há texto texto para ser salvo se o usuário clicar no botão voltar. Caso haja ele salva.
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        int noteId = intent.getIntExtra("noteId",-1);
        String textoNota = txtNota.getText().toString();

        if (textoNota.length() > 0) {
            txtNota.setText("");
            txtNota.findFocus();
            if(noteId!=-1){
                TelaAnotacoes.notas.set(noteId,textoNota);
                TelaAnotacoes.adapter.notifyDataSetInvalidated();
            }
            else{
                TelaAnotacoes.notas.add(textoNota);
                TelaAnotacoes.adapter.notifyDataSetInvalidated();
            }
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
            HashSet<String> set = new HashSet(TelaAnotacoes.notas);
            sharedPreferences.edit().putStringSet("notas",set).apply();
            finish();
        }
        else{
            super.onBackPressed();
            finish();

        }

    }
}

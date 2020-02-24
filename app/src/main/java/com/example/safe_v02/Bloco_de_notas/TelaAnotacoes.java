package com.example.safe_v02.Bloco_de_notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.safe_v02.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

public class TelaAnotacoes extends AppCompatActivity {

    ListView listView;
    FloatingActionButton btnAdd;
    static ArrayList<String> notas = new ArrayList<String>();
    static ArrayAdapter<String> adapter = null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_anotacoes);
        toolbar = findViewById(R.id.toolbarBn1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Minhas anotações");

        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        listView = (ListView) findViewById(R.id.Notas);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notas", null);

        if(set!=null){
            notas = new ArrayList(set);

        }

        //Chama a tela de edição de texto ao clicar no botão add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaAnotacoes.this, TelaEditorTexto.class);
                startActivity(intent);
            }
        });


        //Passa o adapter para a listView
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notas);
        listView.setAdapter(adapter);

        //Caso seja exercido um toque curto em um itemda listView, ele será aberto na tela de edição
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TelaAnotacoes.this, TelaEditorTexto.class);
                intent.putExtra("noteId",position);
                startActivity(intent);
            }
        });

        //Caso seja exercido um toque longo em um item da listView, será exibido um dialog perguntando se deseja apagar o item
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb= new AlertDialog.Builder(TelaAnotacoes.this);
                adb.setTitle("Excluir anotação");
                adb.setMessage("Deseja excluir essa anotação?");
                final int positionToMove = position;
                adb.setNegativeButton("NÂo",null);
                adb.setPositiveButton("SIM",new AlertDialog.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notas.remove(position);
                        adapter.notifyDataSetInvalidated();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
                        HashSet<String> set = new HashSet(notas);
                        sharedPreferences.edit().putStringSet("notas",set).apply();
                    }
                });
                adb.show();
                return true;
            }
        });
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

}

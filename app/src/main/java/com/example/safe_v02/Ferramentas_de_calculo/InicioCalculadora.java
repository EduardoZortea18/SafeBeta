package com.example.safe_v02.Ferramentas_de_calculo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.example.safe_v02.R;

public class InicioCalculadora extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner Operacao;
    Button btnProximo;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_calculadora);

        toolbar = findViewById(R.id.toolbarCalc);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Ferramentas de cálculo");

        //Seta o spinner e a sua lista de itens
        Operacao = (Spinner) findViewById(R.id.Operacao);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operacoes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Operacao.setAdapter(adapter);

        Operacao.setOnItemSelectedListener(this);

    }

    //Verifica se um item do spinner foi selecionado e executa uma ação
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String operacaoSelecionada = Operacao.getSelectedItem().toString();
        switch (operacaoSelecionada) {
            case "Equação do 2º grau":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new Bhaskara()).commit();
                break;
            case "Regra de três simples":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new RegraDeTres()).commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
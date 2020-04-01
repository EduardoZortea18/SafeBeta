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

import com.example.safe_v02.Ferramentas_de_calculo.Fisica.Calorimetria;
import com.example.safe_v02.Ferramentas_de_calculo.Fisica.Distancia;
import com.example.safe_v02.Ferramentas_de_calculo.Fisica.Eletricidade;
import com.example.safe_v02.Ferramentas_de_calculo.Fisica.Empuxo;
import com.example.safe_v02.Ferramentas_de_calculo.Fisica.MRU;
import com.example.safe_v02.Ferramentas_de_calculo.Fisica.Torricelli;
import com.example.safe_v02.Ferramentas_de_calculo.Matematica.Bhaskara;
import com.example.safe_v02.Ferramentas_de_calculo.Matematica.DistanciaEntrePontos;
import com.example.safe_v02.Ferramentas_de_calculo.Matematica.RegraDeTres;
import com.example.safe_v02.R;

public class InicioCalculadora extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner_operacoes,spinner_materias;
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

        spinner_operacoes = (Spinner) findViewById(R.id.SpinnerOperacoes);

        spinner_materias = (Spinner) findViewById(R.id.SpinnerMaterias_calculos);
        ArrayAdapter<CharSequence> adapter_materias = ArrayAdapter.createFromResource(this, R.array.array_materias, android.R.layout.simple_spinner_item);
        adapter_materias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_materias.setAdapter(adapter_materias);
        spinner_materias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String operacaoSelecionada = spinner_materias.getSelectedItem().toString();
                switch (operacaoSelecionada) {
                    case "Física":
                        ArrayAdapter<CharSequence> adapter_fisica = ArrayAdapter.createFromResource(InicioCalculadora.this, R.array.array_fisica, android.R.layout.simple_spinner_item);
                        adapter_fisica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_operacoes.setAdapter(adapter_fisica);
                        break;
                    case "Matemática":
                        ArrayAdapter<CharSequence> adapter_matematica = ArrayAdapter.createFromResource(InicioCalculadora.this, R.array.array_matematica, android.R.layout.simple_spinner_item);
                        adapter_matematica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_operacoes.setAdapter(adapter_matematica);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_operacoes.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String operacaoSelecionada = spinner_operacoes.getSelectedItem().toString();
        switch (operacaoSelecionada) {
            case "Calorimetria":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new Calorimetria()).commit();
                break;
            case "Distância":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new Distancia()).commit();
                break;
            case "Distância entre dois pontos":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new DistanciaEntrePontos()).commit();
                break;
            case "Empuxo":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new Empuxo()).commit();
                break;
            case "Eletricidade":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new Eletricidade()).commit();
                break;
            case "Equação de Torricelli":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new Torricelli()).commit();
                break;
            case "MRU":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_calculadora, new MRU()).commit();
                break;
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
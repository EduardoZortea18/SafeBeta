package com.example.safe_v02.Ferramentas_de_calculo.Matematica;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safe_v02.R;

import java.text.DecimalFormat;

public class Equacao_primeiro_grau extends Fragment {

    TextView txtResultado;
    EditText txtA,txtB;
    Button btnResolver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equacao_primeiro_grau, container, false);

        txtA = (EditText)view.findViewById(R.id.txtPrimeiroGrau1);
        txtB = (EditText)view.findViewById(R.id.txtPrimeiroGrau2);
        txtResultado = (TextView)view.findViewById(R.id.txtPrimeiroGrau3);

        btnResolver = (Button)view.findViewById(R.id.btnPrimeiroGrau);
        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a=0,b=0,x=0;
                if((txtA.getText().toString().length()>0)&&(txtB.getText().toString().length()>0)){
                    a=Float.parseFloat(txtA.getText().toString());
                    b=Float.parseFloat(txtB.getText().toString());
                    x=((0-b)/a);
                    DecimalFormat df = new DecimalFormat("0.0");
                    txtResultado.setText("X = "+df.format(x));
                }
                else{
                    Toast.makeText(getActivity(), "Você deve preencher todos os campos para resolver a equação", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}

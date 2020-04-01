package com.example.safe_v02.Ferramentas_de_calculo.Matematica;


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

public class DistanciaEntrePontos extends Fragment {

    EditText txtX1,txtY1,txtX2,txtY2;
    Button btnResolver;
    TextView txtResultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_distancia_entre_pontos, container, false);

        txtX1 = (EditText)view.findViewById(R.id.txtDistanciaPontos1);
        txtY1 = (EditText)view.findViewById(R.id.txtDistanciaPontos2);
        txtX2 = (EditText)view.findViewById(R.id.txtDistanciaPontos3);
        txtY2 = (EditText)view.findViewById(R.id.txtDistanciaPontos4);
        txtResultado = (TextView)view.findViewById(R.id.txtDistanciaPonto5);


        btnResolver = (Button)view.findViewById(R.id.btnDistanciaPontos);
        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x1=0,x2=0,y1=0,y2=0;
                double distancia=0;

                if((txtX1.getText().toString().length()>0)&&(txtY1.getText().toString().length()>0)&&(txtX2.getText().toString().length()>0)&&(txtY2.getText().toString().length()>0)){
                    x1=Float.parseFloat(txtX1.getText().toString());
                    y1=Float.parseFloat(txtY1.getText().toString());
                    x2=Float.parseFloat(txtX2.getText().toString());
                    y2=Float.parseFloat(txtY2.getText().toString());

                    distancia = Math.sqrt((Math.pow((x2-x1),2))+(Math.pow((y2-y1),2)));
                    DecimalFormat df = new DecimalFormat("0.00");
                    txtResultado.setText("Distância = "+df.format(distancia));
                }
                else{
                    Toast.makeText(getActivity(), "Você deve preencher todos os campos para continuar", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}

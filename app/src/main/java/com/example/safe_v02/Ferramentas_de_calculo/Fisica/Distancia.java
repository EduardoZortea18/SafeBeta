package com.example.safe_v02.Ferramentas_de_calculo.Fisica;

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

import com.example.safe_v02.R;


public class Distancia extends Fragment {
EditText txtVelocidade,txtTempo;
TextView txtDistancia;
Button btnDistancia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_distancia, container, false);

        txtVelocidade = (EditText)view.findViewById(R.id.txtDistancia1);
        txtTempo = (EditText)view.findViewById(R.id.txtDistancia2);
        txtDistancia = (TextView)view.findViewById(R.id.txtDistancia3);
        btnDistancia = (Button)view.findViewById(R.id.btnDistancia);

        btnDistancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                float v=0,d=0,t=0;
                if((txtVelocidade.getText().toString().length()>0)&&(txtTempo.getText().toString().length()>0)){
                    v=Float.parseFloat(txtVelocidade.getText().toString());
                    t=Float.parseFloat(txtTempo.getText().toString());
                    d=(v*t);
                    txtDistancia.setText("d = "+d+"m");
                }
            }
        });

        return  view;
    }
}

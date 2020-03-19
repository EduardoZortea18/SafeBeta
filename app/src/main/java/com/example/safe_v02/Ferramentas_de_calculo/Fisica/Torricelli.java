package com.example.safe_v02.Ferramentas_de_calculo.Fisica;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.safe_v02.R;

import java.text.DecimalFormat;


public class Torricelli extends Fragment {

TextView txtVFinal;
EditText txtVInicial,txtAceleracao,txtEspaco;
Button btnTorricelli;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_torricelli, container, false);

        txtVInicial = (EditText)view.findViewById(R.id.txtTorricelli1);
        txtAceleracao = (EditText)view.findViewById(R.id.txtTorricelli2);
        txtEspaco = (EditText)view.findViewById(R.id.txtTorricelli3);
        txtVFinal = (TextView)view.findViewById(R.id.txtTorricelli4);
        btnTorricelli = (Button)view.findViewById(R.id.btnTorricelli);

        btnTorricelli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double vf=0,v0=0,a=0,s=0;
                DecimalFormat df= new DecimalFormat("0.00");
                if((txtVInicial.getText().toString().length()>0)&&(txtAceleracao.getText().toString().length()>0)&&(txtEspaco.getText().toString().length()>0)){
                    v0=Double.parseDouble(txtVInicial.getText().toString());
                    a=Double.parseDouble(txtAceleracao.getText().toString());
                    s=Double.parseDouble(txtEspaco.getText().toString());
                    vf=Math.sqrt(((v0*v0)+((2*a)*s)));
                    txtVFinal.setText("vf = "+ df.format(vf)+"m/s");
                }
            }
        });

        return  view;
    }

}

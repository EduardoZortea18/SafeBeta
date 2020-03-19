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

public class MRU extends Fragment {
EditText txtespacoInicial,txtVelocidade,txtTempo;
TextView txtEspacoPercorrido;
Button btnMRU;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mru, container, false);

        txtespacoInicial = (EditText)view.findViewById(R.id.txtMRU1);
        txtVelocidade = (EditText)view.findViewById(R.id.txtMRU2);
        txtTempo = (EditText)view.findViewById(R.id.txtMRU3);
        txtEspacoPercorrido = (TextView)view.findViewById(R.id.txtMRU4);
        btnMRU = (Button)view.findViewById(R.id.btnMRU);

        btnMRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float S=0,So=0,V=0,t=0;
                DecimalFormat df = new DecimalFormat("0.00");
                if((txtespacoInicial.getText().toString().length()>0)&&(txtVelocidade.getText().toString().length()>0)&&(txtTempo.getText().toString().length()>0)){
                    So=Float.parseFloat(txtespacoInicial.getText().toString());
                    V=Float.parseFloat(txtVelocidade.getText().toString());
                    t=Float.parseFloat(txtTempo.getText().toString());
                    S=(So+(V*t));
                    txtEspacoPercorrido.setText("S = "+df.format(S)+"m");
                }
            }
        });

        return view;
    }

}

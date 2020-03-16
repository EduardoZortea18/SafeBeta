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


public class Empuxo extends Fragment {
EditText txtDensidade,txtVolume,txtGravidade;
TextView txtEnpuxo;
Button btnenpuxo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_empuxo, container, false);

        txtDensidade = (EditText)view.findViewById(R.id.txtEnpuxo1);
        txtVolume = (EditText)view.findViewById(R.id.txtenpuxo2);
        txtGravidade = (EditText)view.findViewById(R.id.txtEnpuxo3);
        txtEnpuxo = (TextView)view.findViewById(R.id.txtenpuxo4);
        btnenpuxo = (Button)view.findViewById(R.id.btnEnpuxo);

        btnenpuxo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                float d=0,V=0,g=0,E=0;
                if((txtDensidade.getText().toString().length()>0)&&(txtVolume.getText().toString().length()>0)&&(txtGravidade.getText().toString().length()>0)){

                    d = Float.parseFloat(txtDensidade.getText().toString());
                    V = Float.parseFloat(txtVolume.getText().toString());
                    g = Float.parseFloat(txtGravidade.getText().toString());
                    E=(d*V*g);
                    txtEnpuxo.setText("E = "+E+"N");
                }
            }
        });

        return view;
    }

}

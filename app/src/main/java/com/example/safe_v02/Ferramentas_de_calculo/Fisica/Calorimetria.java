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


public class Calorimetria extends Fragment {
EditText txtMassa,txtCalor,txtVarTemperatura;
TextView txtQCalor;
Button btnCalorimetria;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calorimetria, container, false);

        txtMassa = (EditText)view.findViewById(R.id.txtCalorimetria1);
        txtCalor = (EditText)view.findViewById(R.id.txtCalorimetria2);
        txtVarTemperatura = (EditText)view.findViewById(R.id.txtCalorimetria3);
        txtQCalor = (TextView)view.findViewById(R.id.txtCalorimetria4);

        btnCalorimetria = (Button)view.findViewById(R.id.btnCalorimetria);
        btnCalorimetria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float q=0,m=0,c=0,varT=0;
                if((txtMassa.getText().toString().length()>0)&&(txtCalor.getText().toString().length()>0)&&(txtVarTemperatura.getText().toString().length()>0)){
                    m = Float.parseFloat(txtMassa.getText().toString());
                    c = Float.parseFloat(txtCalor.getText().toString());
                    varT = Float.parseFloat(txtVarTemperatura.getText().toString());
                    q=(m*c*varT);
                    txtQCalor.setText("Q = "+q+"cal");
                }
            }
        });

        return view;
    }


}

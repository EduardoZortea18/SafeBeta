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

public class Eletricidade extends Fragment {
EditText txtResistencia,txtCorrente;
TextView txtDiferenca;
Button btnEletricidade;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_eletricidade, container, false);

        txtResistencia=(EditText)view.findViewById(R.id.txtEletricidade1);
        txtCorrente=(EditText)view.findViewById(R.id.txtEletricidade2);
        txtDiferenca=(TextView)view.findViewById(R.id.txtEletricidade3);
        btnEletricidade=(Button)view.findViewById(R.id.btnEletricidade);

        btnEletricidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float U=0,R=0,i=0;

                if((txtResistencia.getText().toString().length()>0)&&(txtCorrente.getText().toString().length()>0)){
                    R=Float.parseFloat(txtResistencia.getText().toString());
                    i=Float.parseFloat(txtCorrente.getText().toString());
                    U=(R*i);
                    txtDiferenca.setText("U = "+U+"V");
                }
            }
        });

        return view;
    }


}

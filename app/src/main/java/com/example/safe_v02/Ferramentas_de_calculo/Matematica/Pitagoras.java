package com.example.safe_v02.Ferramentas_de_calculo.Matematica;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safe_v02.R;

import java.text.DecimalFormat;


public class Pitagoras extends Fragment {
EditText txtN1,txtN2;
TextView txtResultado;
int operacao=0;
RadioGroup radioGroupPitagoras;
Button btnResolver;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pitagoras, container, false);

        txtN1 = (EditText)view.findViewById(R.id.txtPitagoras1);
        txtN2 = (EditText)view.findViewById(R.id.txtPitagoras2);
        txtResultado = (TextView)view.findViewById(R.id.txtPitagoras3);

        radioGroupPitagoras = (RadioGroup)view.findViewById(R.id.radioGroupPitagoras);
        radioGroupPitagoras.check(R.id.radioPitagoras1);
        radioGroupPitagoras.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioPitagoras1:
                        operacao=0;
                        txtN1.setHint("Cateto a");
                        txtN2.setHint("Cateto b");
                        txtResultado.setText("Valor da hipotenusa = ");
                        break;
                    case R.id.radioPitagoras2:
                        operacao=1;
                        txtN1.setHint("Cateto");
                        txtN2.setHint("Hipotenusa");
                        txtResultado.setText("Valor do catetoX = ");
                        break;
                }
            }
        });
        
        btnResolver = (Button)view.findViewById(R.id.btnPitagoras);
        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double n1=0,n2=0,n3=0;
                DecimalFormat df = new DecimalFormat("0.00");
                
                if((txtN1.getText().toString().length()>0)&&(txtN2.getText().toString().length()>0)){
                    n1=Double.parseDouble(txtN1.getText().toString());
                    n2=Double.parseDouble(txtN2.getText().toString());
                   if ((n1>0)&&(n2>0)){
                       switch (operacao){
                           case 0:
                                n3=Math.sqrt(((n1*n1)+(n2*n2)));
                               txtResultado.setText("Valor da hipotenusa = "+df.format(n3));
                               break;
                           case 1:
                               n3=Math.sqrt(((n2*n2)-(n1*n1)));
                               txtResultado.setText("Valor do catetoX = "+df.format(n3));
                               break;
                       }
                   }
                   else{
                       Toast.makeText(getActivity(), "Você deve informar valores maiores que 0 para continuar", Toast.LENGTH_SHORT).show();
                   }
                }
                else{
                    Toast.makeText(getActivity(), "Você deve preencher todos os campos para continuar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    return view;
    }

}

package com.example.safe_v02.Ferramentas_de_calculo.Matematica;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safe_v02.R;


public class Bhaskara extends Fragment {
    TextView txtX1,txtX2;
    EditText txtA,txtB,txtC;
    Button btnResolverBhaskara;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bhaskara, container, false);
        txtA = (EditText)view.findViewById(R.id.txtA);
        txtB = (EditText)view.findViewById(R.id.txtB);
        txtC = (EditText)view.findViewById(R.id.txtC);
        btnResolverBhaskara = (Button)view.findViewById(R.id.btnResolverBhaskara);
        txtX1 = (TextView)view.findViewById(R.id.txtX1);
        txtX2 = (TextView)view.findViewById(R.id.txtX2);

        btnResolverBhaskara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((txtA.getText().toString().length()>0)&&(txtB.getText().toString().length()>0)&&(txtC.getText().toString().length()>0)){
                    Double a,b,c,x1,x2,delta;
                    a= Double.parseDouble(txtA.getText().toString());
                    b= Double.parseDouble(txtB.getText().toString());
                    c= Double.parseDouble(txtC.getText().toString());
                    String valorX1,valorX2;
                    delta = ((b*b)-((4*a)*c));
                    if(delta<0){
                        txtX1.setText("Não existem raizes reais.");
                    }
                    else if(delta==0){
                        x1 = ((-b)+(Math.sqrt(delta)))/(2*a);
                        valorX1 = String.format("%.2f",x1);
                        txtX1.setText("Valor de X = "+x1);
                    }
                    else{
                        x1 = ((-b)+(Math.sqrt(delta)))/(2*a);
                        x2 = ((-b)-(Math.sqrt(delta)))/(2*a);
                        valorX1 = String.format("%.2f",x1);
                        valorX2 = String.format("%.2f",x2);

                        txtX1.setText("Valor de X1 = "+ valorX1);
                        txtX2.setText("Valor de X2 = "+ valorX2);

                    }
                }
                else{
                    Toast.makeText(getActivity(), "Você deve preencher todos os campos para realizar a operação", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}

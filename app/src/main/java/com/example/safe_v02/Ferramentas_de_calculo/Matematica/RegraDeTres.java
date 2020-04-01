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


public class RegraDeTres extends Fragment {
    TextView txtResultado;
    EditText txtN1,txtN2,txtN3,txtN4;
    Button btnResolver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regra_de_tres, container, false);

        txtN1 = (EditText)view.findViewById(R.id.txtn1RTS);
        txtN2 = (EditText)view.findViewById(R.id.txtn2RTS);
        txtN3 = (EditText)view.findViewById(R.id.txtn3RTS);
        txtN4 = (EditText)view.findViewById(R.id.txtn4RTS);
        btnResolver = (Button)view.findViewById(R.id.btnResolverRTS);
        txtResultado = (TextView)view.findViewById(R.id.txtResultadoRTS);


        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((txtN1.getText().toString().length()>0)&&(txtN2.getText().toString().length()>0)&&(txtN3.getText().toString().length()>0)&&(txtN4.getText().toString().length()>0)){
                    Float n1, n2, n3, n4, x;
                    String resultado;
                    if (txtN1.getText().toString().equalsIgnoreCase("x")) {
                        n2 = Float.parseFloat(txtN2.getText().toString());
                        n3 = Float.parseFloat(txtN3.getText().toString());
                        n4 = Float.parseFloat(txtN4.getText().toString());
                        x = (n3 * n2) / n4;
                        resultado = String.format("%.1f", x);
                        txtResultado.setText("Valor de X = " + resultado);
                    } else if (txtN2.getText().toString().equalsIgnoreCase("x")) {
                        n1 = Float.parseFloat(txtN1.getText().toString());
                        n3 = Float.parseFloat(txtN3.getText().toString());
                        n4 = Float.parseFloat(txtN4.getText().toString());
                        x = (n1 * n4) / n3;
                        resultado = String.format("%.1f", x);
                        txtResultado.setText("Valor de X = " + resultado);
                    } else if (txtN3.getText().toString().equalsIgnoreCase("x")) {
                        n1 = Float.parseFloat(txtN1.getText().toString());
                        n2 = Float.parseFloat(txtN2.getText().toString());
                        n4 = Float.parseFloat(txtN4.getText().toString());
                        x = (n1 * n4) / n2;
                        resultado = String.format("%.1f", x);
                        txtResultado.setText("Valor de X = " + resultado);
                    } else if (txtN4.getText().toString().equalsIgnoreCase("x")) {
                        n1 = Float.parseFloat(txtN1.getText().toString());
                        n2 = Float.parseFloat(txtN2.getText().toString());
                        n3 = Float.parseFloat(txtN3.getText().toString());
                        x = (n3 * n2) / n1;
                        resultado = String.format("%.1f", x);
                        txtResultado.setText("Valor de X = " + resultado);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Erro!", Toast.LENGTH_LONG).show();
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

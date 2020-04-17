package com.example.safe_v02.Ferramentas_de_calculo.Matematica;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safe_v02.R;

import java.text.DecimalFormat;


public class RegraDeTres extends Fragment {
    TextView txtResultado;
    EditText txtN1,txtN2,txtN3,txtN4;
    Button btnResolver;
    int operacao=0;
    RadioGroup radioGroupRTS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regra_de_tres, container, false);

        txtN1 = (EditText)view.findViewById(R.id.txtn1RTS);
        txtN2 = (EditText)view.findViewById(R.id.txtn2RTS);
        txtN3 = (EditText)view.findViewById(R.id.txtn3RTS);
        btnResolver = (Button)view.findViewById(R.id.btnResolverRTS);
        txtResultado = (TextView)view.findViewById(R.id.txtResultadoRTS);
        radioGroupRTS = (RadioGroup)view.findViewById(R.id.radioGroupRTS);
        radioGroupRTS.check(R.id.radioRTS1);

        radioGroupRTS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.radioRTS1:
                        operacao=0;
                        break;
                    case R.id.radioRTS2:
                        operacao=1;
                        break;
                }
            }
        });


        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((txtN1.getText().toString().length()>0)&&(txtN2.getText().toString().length()>0)&&(txtN3.getText().toString().length()>0)){
                    float n1=0, n2=0, n3=0,x=0;
                    DecimalFormat df = new DecimalFormat("0.00");

                    n1 = Float.parseFloat(txtN1.getText().toString());
                    n2 = Float.parseFloat(txtN2.getText().toString());
                    n3 = Float.parseFloat(txtN3.getText().toString());

                    if((n1>0)&&(n2>0)&&(n3>0)){
                        switch (operacao){
                            case 0:
                                x=((n2*n3)/n1);
                                break;
                            case 1:
                                x=((n1*n2)/n3);
                                break;
                        }
                        txtResultado.setText("Valor de X = "+df.format(x));
                    }
                    else{
                        Toast.makeText(getActivity(), "Todos os valores devem ser diferentes de 0", Toast.LENGTH_SHORT).show();
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

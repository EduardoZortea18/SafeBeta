package com.example.safe_v02.Tutorial;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safe_v02.R;


public class Tutorial1 extends Fragment {
    EditText txtNome,txtEmail,txtCurso,txtInstituicao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tutorial1, container, false);

        txtNome = (EditText)view.findViewById(R.id.txtTutorialNome);
        txtEmail = (EditText)view.findViewById(R.id.txtTutorialEmail);
        txtCurso = (EditText)view.findViewById(R.id.txtTutorialCurso);
        txtInstituicao = (EditText)view.findViewById(R.id.txtTutorialInstituicao);


        Button btnTutorial1 = (Button)view.findViewById(R.id.btnTutorial1);
        btnTutorial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((txtNome.getText().toString().length()>0)&&(txtEmail.getText().toString().length()>0)&&(txtCurso.getText().toString().length()>0)&&(txtInstituicao.getText().toString().length()>0)){
                    SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("com.example.safe_v02", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("nome",txtNome.getText().toString()).apply();
                    sharedPreferences.edit().putString("email",txtEmail.getText().toString()).apply();
                    sharedPreferences.edit().putString("curso",txtCurso.getText().toString()).apply();
                    sharedPreferences.edit().putString("instituicao",txtInstituicao.getText().toString()).apply();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_tutorial, new Tutorial2()).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Você deve preencher todos os campos para continuar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }



}
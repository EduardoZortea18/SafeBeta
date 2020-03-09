package com.example.safe_v02.Tutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.safe_v02.Materias_e_notas.Materia;
import com.example.safe_v02.Materias_e_notas.MateriaDAO;
import com.example.safe_v02.R;


public class Tutorial2 extends Fragment {
EditText txtNomeDaMateria,txtQntdNotas;
Spinner spinnerTipoMedia;
Button btnTutorial2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial2, container, false);
        txtNomeDaMateria = (EditText)view.findViewById(R.id.txtTutorialNomeMateria);
        txtQntdNotas = (EditText)view.findViewById(R.id.txtTutorialQntdNotas);
        spinnerTipoMedia = (Spinner)view.findViewById(R.id.spinnerTutorialTipoMedia);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.tipos_de_media_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoMedia.setAdapter(adapter);
        btnTutorial2 = (Button)view.findViewById(R.id.btnTutorial2);
        btnTutorial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((txtNomeDaMateria.getText().toString().length()>0)&&(txtQntdNotas.getText().toString().length()>0)&&(spinnerTipoMedia.getSelectedItem().toString().equalsIgnoreCase("Matéria")==false)){
                    int qntdNotas = Integer.parseInt(txtQntdNotas.getText().toString());
                    if(qntdNotas>0){
                        Materia materia = new Materia();
                        materia.setNome(txtNomeDaMateria.getText().toString());
                        materia.setQntdNotas(qntdNotas);
                        materia.setTipoDeMedia(spinnerTipoMedia.getSelectedItem().toString());
                        MateriaDAO materiaDAO = new MateriaDAO(getActivity());
                        materiaDAO.inserirMateria(materia);
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container_tutorial, new Tutorial3()).commit();
                    }
                    else{
                        Toast.makeText(getActivity(), "A quantidade de notas deve ser maior que 0", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Você deve preencher todos os dados corretamente para continuar.", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }


}

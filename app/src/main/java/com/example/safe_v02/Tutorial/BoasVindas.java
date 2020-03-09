package com.example.safe_v02.Tutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.safe_v02.R;


public class BoasVindas extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_boas_vindas, container, false);
        Button btnIniciarTutorial = (Button)view.findViewById(R.id.btnBoasVindas);
        btnIniciarTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_tutorial, new Tutorial1()).commit();
            }
        });
        return view;

    }


}

package com.example.safe_v02.Tutorial;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.safe_v02.MainActivity;
import com.example.safe_v02.R;


public class Tutorial3 extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial3, container, false);
        Button btnTutorial3 = (Button)view.findViewById(R.id.btnTutorial3);
        btnTutorial3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }


}

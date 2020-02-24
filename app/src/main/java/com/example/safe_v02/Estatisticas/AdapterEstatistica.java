package com.example.safe_v02.Estatisticas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.safe_v02.Materias_e_notas.Materia;
import com.example.safe_v02.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterEstatistica extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Materia> materias;

    public AdapterEstatistica(Context context, ArrayList materias) {
        super(context, R.layout.layout_estatistica, materias);
        this.context = context;
        this.materias = materias;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        Materia materia = (Materia) this.materias.get(position);
        View view = layoutInflater.inflate(R.layout.layout_estatistica, parent, false);
        TextView txtEstatisticas1 = (TextView) view.findViewById(R.id.txtEstatisticas1);
        TextView txtEstatisticas2 = (TextView) view.findViewById(R.id.txtEstatisticas2);
        txtEstatisticas1.setText(materia.getNome());
        DecimalFormat df = new DecimalFormat("0.00");
        txtEstatisticas2.setText(df.format(materia.getMedia()));
        return view;
    }
}

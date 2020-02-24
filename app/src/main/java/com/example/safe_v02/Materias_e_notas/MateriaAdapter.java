package com.example.safe_v02.Materias_e_notas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.safe_v02.R;

import java.util.ArrayList;

public class MateriaAdapter extends ArrayAdapter {
   private final Context context;
   private final ArrayList<Materia> materias;

   public MateriaAdapter(Context context, ArrayList materias) {
      super(context, R.layout.layout_materia, materias);
      this.context = context;
      this.materias = materias;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View rowView = inflater.inflate(R.layout.layout_materia, parent, false);
      TextView txtMateria = (TextView) rowView.findViewById(R.id.txtMateria);
      txtMateria.setText(materias.get(position).getNome());
      return rowView;
   }
}

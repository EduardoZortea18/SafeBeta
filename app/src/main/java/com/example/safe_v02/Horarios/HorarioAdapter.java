package com.example.safe_v02.Horarios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.safe_v02.R;

import java.util.ArrayList;

public class HorarioAdapter extends ArrayAdapter {
   private final Context context;
   private final ArrayList<Horario> horarios;

   public HorarioAdapter(Context context, ArrayList horarios) {
      super(context, 2131492929, horarios);
      this.context = context;
      this.horarios = horarios;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View rowView = inflater.inflate(R.layout.layout_horario, parent, false);
      TextView horario1 = (TextView) rowView.findViewById(R.id.txtHorario1);
      TextView horario2 = (TextView) rowView.findViewById(R.id.txtHorario2);
      Horario horario = horarios.get(position);
      horario1.setText(horario.getHoraInicio()+" - "+horario.getHoraTermino());
      horario2.setText(horario.getMateria());
      return rowView;
   }
}

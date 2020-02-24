package com.example.safe_v02.Agenda_de_eventos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.safe_v02.R;

import java.util.ArrayList;

public class EventoAdapter extends ArrayAdapter<Evento> {

    private final Context context;
    private final ArrayList<Evento> eventos;

    public EventoAdapter(Context context, ArrayList<Evento> eventos) {
        super(context, R.layout.linha_evento, eventos);
        this.context = context;
        this.eventos = eventos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_evento, parent, false);
        TextView tituloEvevnto = (TextView) rowView.findViewById(R.id.TituloEvento);
        tituloEvevnto.setText(eventos.get(position).getTituloEvento());
        return rowView;
    }

}

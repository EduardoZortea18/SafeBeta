package com.example.safe_v02.Notificacoes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.safe_v02.Agenda_de_eventos.Evento;
import com.example.safe_v02.Agenda_de_eventos.EventoDAO;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmManagerUtil {

    private Context context;

    public AlarmManagerUtil(Context context){
        this.context=context;
    }

    public void salvarAlarme(Calendar c,int idalarme, String titulo, String descricao) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        intent.putExtra("Titulo",titulo);
        intent.putExtra("Descricao",descricao);
        intent.putExtra("idAlarme",idalarme);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, idalarme, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    public void cancelarAlarme(int idAlarme,String titulo,String descricao) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        intent.putExtra("Titulo",titulo);
        intent.putExtra("Descricao",descricao);
        intent.putExtra("idAlarme",idAlarme);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, idAlarme, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);

    }

    public void recuperarAlarmes(Context context){
        EventoDAO eventoDAO = new EventoDAO(context);
        ArrayList<Evento> eventos = new ArrayList<Evento>(eventoDAO.obterTodos());
        for(int i=0;i<eventos.size();i++){
            if(eventos.get(i).getIdAlarme()!=0){
                Evento evento = new Evento();
                evento=eventos.get(i);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context, AlertReceiver.class);
                intent.putExtra("Titulo",evento.getTituloEvento());
                intent.putExtra("Descricao",evento.getDataEvento()+" às "+evento.getHorarioevento());
                intent.putExtra("idAlarme",evento.getIdAlarme());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, evento.getIdAlarme(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, evento.getIdAlarme(), pendingIntent);
            }

        }
    }

}

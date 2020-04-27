package com.example.safe_v02.Notificacoes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class AlarmManagerUtil {

    private Context context;

    public AlarmManagerUtil(Context context){
        this.context=context;
    }

    public void salvarAlarme(Calendar c, int idAlarme, String titulo, String descricao) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        intent.putExtra("Titulo",titulo);
        intent.putExtra("Descricao",descricao);
        intent.putExtra("idAlarme",idAlarme);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, idAlarme, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    public void cancelarAlarme(int idEvento,String titulo,String descricao) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        intent.putExtra("Titulo",titulo);
        intent.putExtra("Descricao",descricao);
        intent.putExtra("idAlarme",idEvento);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, idEvento, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);

    }

}

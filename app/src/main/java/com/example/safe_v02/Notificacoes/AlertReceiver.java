package com.example.safe_v02.Notificacoes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.Date;


    public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.gerarNotificacao(intent.getStringExtra("Titulo"),intent.getStringExtra("Descricao"));

        int id = intent.getIntExtra("idAlarme",-1);
        notificationHelper.getManager().notify(id, nb.build());
        Log.d("AlarmManager","Notificado");
    }

}
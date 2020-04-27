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
        NotificationCompat.Builder nb = notificationHelper.gerarNotificacao("Notificação","Tudo ok!!");

        int n = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationHelper.getManager().notify(n, nb.build());
        Log.d("AlarmManager","Notificado");
    }

}
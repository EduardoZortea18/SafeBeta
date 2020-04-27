package com.example.safe_v02.Notificacoes;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import com.example.safe_v02.Agenda_de_eventos.MeusEventos;
import com.example.safe_v02.R;

public class NotificationHelper extends ContextWrapper {

    public static final String CHANNEL_ID="Canal principal";
    private NotificationManager mManager;

    public NotificationHelper(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            criarCanaisDeNotificacao();
        }
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void criarCanaisDeNotificacao() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Canal principal", NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }


    public NotificationCompat.Builder gerarNotificacao(String Titulo, String Descricao) {
        Intent activity_intent = new Intent(this, MeusEventos.class);
        PendingIntent content_intent =   PendingIntent.getActivity(this,0,activity_intent,0);
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(Titulo)
                .setContentText(Descricao)
                .setContentIntent(content_intent)
                .setSmallIcon(R.drawable.logo_black);
    }
}

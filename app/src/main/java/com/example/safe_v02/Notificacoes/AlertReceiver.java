package com.example.safe_v02.Notificacoes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.safe_v02.Agenda_de_eventos.Evento;
import com.example.safe_v02.Agenda_de_eventos.EventoDAO;

import java.util.ArrayList;



    public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.gerarNotificacao(intent.getStringExtra("Titulo"),intent.getStringExtra("Descricao")).setAutoCancel(true);
        int id = intent.getIntExtra("idAlarme",-1);
        notificationHelper.getManager().notify(id, nb.build());
    }

//    public void excluirDoBanco(Context context,int id){
//        EventoDAO eventoDAO = new EventoDAO(context);
//        ArrayList<Evento> eventos = new ArrayList<Evento>(eventoDAO.obterTodos());
//        Evento evento = new Evento();
//        for(int i=0;i<eventos.size();i++){
//            if(eventos.get(i).getIdAlarme()==id){
//                evento=eventos.get(i);
//            }
//        }
//
//        eventoDAO.excluir(evento);
//    }

}
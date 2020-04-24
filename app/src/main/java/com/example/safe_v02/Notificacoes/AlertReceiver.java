package com.example.safe_v02.Notificacoes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.safe_v02.Agenda_de_eventos.Evento;
import com.example.safe_v02.Agenda_de_eventos.EventoDAO;
import com.example.safe_v02.Agenda_de_eventos.MeusEventos;

import java.util.ArrayList;
import java.util.Date;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        int idEvento = intent.getIntExtra("Idevento",-1);
        NotificationCompat.Builder nb = notificationHelper.gerarNotificacao(intent.getStringExtra("Titulo"),intent.getStringExtra("DataEHora"));

        notificationHelper.getManager().notify(idEvento, nb.build());

    }

//    public void excluirEvento(int Idevento){
//        EventoDAO eventoDAO = new EventoDAO(this);
//        ArrayList<Evento> eventos = new ArrayList<Evento>(eventoDAO.obterTodos());
//
//        for (int i=0;i<eventos.size();i++){
//            if(eventos.get(i).getId()==Idevento){
//                eventoDAO.excluir(eventos.get(i));
//            }
//        }
//
//    }


}
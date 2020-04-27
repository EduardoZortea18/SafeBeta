package com.example.safe_v02.Agenda_de_eventos;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.Toolbar;

import com.example.safe_v02.Notificacoes.AlertReceiver;
import com.example.safe_v02.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MeusEventos extends AppCompatActivity {
   static ArrayAdapter adapter = null;
   static ArrayList<Evento> eventos = new ArrayList<Evento>();
   ListView ListaDeEventos;
   FloatingActionButton btnCriarEvento;
   EventoDAO eventoDAO;
   Toolbar toolbar;
   TextView txtAviso;

   protected void onCreate(Bundle bundle) {
      super.onCreate(bundle);
      setContentView(R.layout.activity_meus_eventos);
      toolbar = (Toolbar)findViewById(R.id.toolbarAg1);
      setSupportActionBar(toolbar);
      getSupportActionBar().setTitle("Meus eventos");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      ListaDeEventos = (ListView)findViewById(R.id.ListaDeEventos);
      eventoDAO = new EventoDAO(this);
      carregarEventos();

      txtAviso = (TextView)findViewById(R.id.txtAvisoEventos);
      verificarEventtos();

      btnCriarEvento = (FloatingActionButton)findViewById(R.id.btnCriarEvento);
      btnCriarEvento.setOnClickListener(new OnClickListener() {
         public void onClick(View view) {
            Intent intent = new Intent(MeusEventos.this, CadastrarEvento.class);
            startActivity(intent);
         }
      });
      ListaDeEventos.setOnItemLongClickListener(new OnItemLongClickListener() {
         public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            Builder builder = new Builder(MeusEventos.this);
            builder.setTitle("Excluir evento");
            builder.setMessage("Deseja excluir esse evento?");
            builder.setNegativeButton("Não", (android.content.DialogInterface.OnClickListener)null);
            builder.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                   int idEvento = eventos.get(position).getIdCriacao();
                   String titulo=eventos.get(position).getTituloEvento();
                   String descricao=eventos.get(position).getDataEvento()+" "+eventos.get(position).getHorarioevento();
                   cancelarAlarme();
                   eventoDAO.excluir(eventos.get(position));
                   carregarEventos();
                   verificarEventtos();
               }
            });
            builder.show();
            return true;
         }
      });
      ListaDeEventos.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView parent, View view, int position, long id) {
            Intent intent = new Intent(MeusEventos.this, InfoEvento.class);
            intent.putExtra("idEvento", position);
            startActivity(intent);
         }
      });


   }

   public void verificarEventtos(){
      if(eventos.size()<=0){
         txtAviso.setText(R.string.MsgEventos);
      }
      else{
         txtAviso.setText(" ");
      }
   }

   @Override
   protected void onResume() {
      super.onResume();
      carregarEventos();
      verificarEventtos();
   }

   public void carregarEventos(){
      eventos = new ArrayList<Evento>(eventoDAO.obterTodos());
      adapter = new EventoAdapter(this, eventos);
      ListaDeEventos.setAdapter(adapter);
   }

   private void cancelarAlarme() {
      AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
      Intent intent = new Intent(this, AlertReceiver.class);
      PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

      try {
         alarmManager.cancel(pendingIntent);
         Log.e("AlarmManager", "Cancelling all pending intents");
      } catch (Exception e) {
         Log.e("AlarmManager", "AlarmManager update was not canceled. " + e.toString());
      }        Toast.makeText(this, "Alarme cancelado", Toast.LENGTH_SHORT).show();
   }

}

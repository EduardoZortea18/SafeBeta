package com.example.safe_v02.Agenda_de_eventos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.Toolbar;

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

   protected void onCreate(Bundle bundle) {
      super.onCreate(bundle);
      setContentView(R.layout.activity_meus_eventos);
      this.toolbar = (Toolbar)findViewById(R.id.toolbarAg1);
      setSupportActionBar(toolbar);
      getSupportActionBar().setTitle("Meus eventos");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      ListaDeEventos = (ListView)findViewById(R.id.ListaDeEventos);
      eventoDAO = new EventoDAO(this);
      eventos = new ArrayList<Evento>(eventoDAO.obterTodos());
      adapter = new EventoAdapter(this, eventos);
      ListaDeEventos.setAdapter(adapter);
      btnCriarEvento = (FloatingActionButton)findViewById(R.id.btnCriarEvento);
      btnCriarEvento.setOnClickListener(new OnClickListener() {
         public void onClick(View view) {
            Intent intent = new Intent(MeusEventos.this, CadastrarEvento.class);
            startActivity(intent);
         }
      });
      this.ListaDeEventos.setOnItemLongClickListener(new OnItemLongClickListener() {
         public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            Builder builder = new Builder(MeusEventos.this);
            builder.setTitle("Excluir evento");
            builder.setMessage("Deseja excluir esse evento?");
            builder.setNegativeButton("Não", (android.content.DialogInterface.OnClickListener)null);
            builder.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                  Evento evento = eventos.get(position);
                  eventoDAO.excluir(evento);
                  eventos.remove(position);
                  MeusEventos.adapter.notifyDataSetInvalidated();
               }
            });
            builder.show();
            return true;
         }
      });
      this.ListaDeEventos.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView parent, View view, int position, long id) {
            Intent intent = new Intent(MeusEventos.this, InfoEvento.class);
            intent.putExtra("idEvento", position);
            startActivity(intent);
         }
      });
   }
}

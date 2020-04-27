package com.example.safe_v02.Agenda_de_eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.safe_v02.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoEvento extends AppCompatActivity {
   FloatingActionButton btnEditarEvento;
   Toolbar toolbar;
   TextView txtInfoEvento1;
   TextView txtInfoEvento2;
   TextView txtInfoEvento3;
   TextView txtInfoEvento4;
   TextView txtInfoEvento5;

   public void carregarInformacoes() {
      int idEvento = this.getIntent().getIntExtra("idEvento", -1);
      if (idEvento > -1) {
         Evento evento = MeusEventos.eventos.get(idEvento);
         txtInfoEvento1.setText(evento.getTituloEvento());
         txtInfoEvento2.setText(evento.getDataEvento()+" às "+evento.getHorarioevento());
         txtInfoEvento3.setText("Tipo: "+evento.getTipoEvento());
         txtInfoEvento4.setText("Matéria: "+evento.getMateriaEvento());
         if(evento.getDescricao()!=null){
            txtInfoEvento5.setText("Descrição: "+evento.getDescricao());
         }
      }
   }

   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_info_evento);
      toolbar = (Toolbar)findViewById(R.id.toolbarInfoEvento);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      txtInfoEvento1 = (TextView)findViewById(R.id.txtInfoEvento1);
      txtInfoEvento2 = (TextView)findViewById(R.id.txtInfoEvento2);
      txtInfoEvento3 = (TextView)findViewById(R.id.txtInfoEvento3);
      txtInfoEvento4 = (TextView)findViewById(R.id.txtInfoEvento4);
      txtInfoEvento5 = (TextView)findViewById(R.id.txtInfoEvento5);
      btnEditarEvento = (FloatingActionButton)findViewById(R.id.btnEditarEvento);
      carregarInformacoes();
      btnEditarEvento.setOnClickListener(new OnClickListener() {
         public void onClick(View view) {
            int idEvento = getIntent().getIntExtra("idEvento", -1);
            int idalarme = getIntent().getIntExtra("idAlarme", 0);
            Intent intent = new Intent(InfoEvento.this, CadastrarEvento.class);
            intent.putExtra("editarEvento", idEvento);
            intent.putExtra("idAlarme", idalarme);
            startActivity(intent);
         }
      });
   }

   protected void onResume() {
      carregarInformacoes();
      super.onResume();
   }
}

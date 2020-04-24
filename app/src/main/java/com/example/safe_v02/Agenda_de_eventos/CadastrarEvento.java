package com.example.safe_v02.Agenda_de_eventos;

import android.app.AlarmManager;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.Toolbar;
import com.example.safe_v02.Materias_e_notas.Materia;
import com.example.safe_v02.Materias_e_notas.MateriaDAO;
import com.example.safe_v02.Notificacoes.AlertReceiver;
import com.example.safe_v02.Pickers.DatePickerFragment;
import com.example.safe_v02.Pickers.TimePickerFragment;
import com.example.safe_v02.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CadastrarEvento<Caldendar> extends AppCompatActivity implements OnDateSetListener, OnTimeSetListener {
   ImageButton botaoEscolherData;
   ImageButton botaoEscolherHora;
   FloatingActionButton btnSalvarEvento;
   private EventoDAO eventoDAO;
   Spinner spinnerMateriaevento;
   Spinner spinnerTipoEvento;
   Toolbar toolbar;
   TextView txtDataEvento;
   TextView txtHoraEvento;
   EditText txtTituloEvento,txtDescricaoEvento;
   Calendar custom_calendar = Calendar.getInstance();

   public void carregarEvento(int idEvento) {
      if (idEvento != -1) {
         Evento evento = MeusEventos.eventos.get(idEvento);
         txtTituloEvento.setText(evento.getTituloEvento());
         txtHoraEvento.setText(evento.getHorarioevento());
         txtDataEvento.setText(evento.getDataEvento());
         if(evento.getDescricao()!=null){
            txtDescricaoEvento.setText(evento.getDescricao());
         }
         String tipoEvento = evento.getTipoEvento();

         switch (tipoEvento){
            case "Prova":
               spinnerTipoEvento.setSelection(1);
               break;
            case "Trabalho":
               spinnerTipoEvento.setSelection(2);
               break;
            case "seminário":
               spinnerTipoEvento.setSelection(3);
               break;
         }

         ArrayList<String> array_materias = new ArrayList();
         array_materias.add("Matéria");
         MateriaDAO materiaDAO = new MateriaDAO(this);
         ArrayList<Materia> lista_materias = null;
         if (materiaDAO.obterTodas()!=null) {
            lista_materias = new ArrayList<Materia>(materiaDAO.obterTodas());
            array_materias.add("Todas");
            for(int i=0;i<lista_materias.size();i++){
               array_materias.add(lista_materias.get(i).getNome());
            }
         }

         for(int i=0; i < array_materias.size(); ++i) {
            if (array_materias.get(i).equalsIgnoreCase(evento.getMateriaEvento())) {
               spinnerMateriaevento.setSelection(i);
            }
         }
      }

   }

   public void onBackPressed() {
      String nome = txtTituloEvento.getText().toString();
      String data = txtDataEvento.getText().toString();
      String hora = txtHoraEvento.getText().toString();
      String tipo = spinnerTipoEvento.getSelectedItem().toString();
      String materia = spinnerMateriaevento.getSelectedItem().toString();
      if (data.length() <= 0 && hora.length() <= 0 && nome.length() <= 0 && materia == "Matéria" && tipo.equalsIgnoreCase("tipo")) {
         super.onBackPressed();
      } else {
         Builder builder = new Builder(this);
         builder.setTitle("Descartar alterações");
         builder.setMessage("Deseja descartar as alterações?");
         builder.setNegativeButton("CANCELAR", (OnClickListener)null);
         builder.setPositiveButton("DESCARTAR", new OnClickListener() {
            public void onClick(DialogInterface var1, int var2) {
               finish();
            }
         });
         builder.show();
      }
   }

   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_cadastrar_evento);
      toolbar = (Toolbar)findViewById(R.id.toolbarAg2);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle("Crie um evento");
      txtTituloEvento = (EditText)findViewById(R.id.txtTituloEvento);
      txtDataEvento = (TextView)findViewById(R.id.txtDataEvento);
      txtHoraEvento = (TextView)findViewById(R.id.txtHoraEvento);
      txtDescricaoEvento = (EditText)findViewById(R.id.txtDescricaoEvento);

      eventoDAO = new EventoDAO(this);
      spinnerTipoEvento = (Spinner)findViewById(R.id.spinnerTipoEvento);
      ArrayAdapter adapter_tipo_evento = ArrayAdapter.createFromResource(this, R.array.tipos_de_eventos_array, android.R.layout.simple_spinner_item);
      adapter_tipo_evento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinnerTipoEvento.setAdapter(adapter_tipo_evento);

      spinnerMateriaevento = (Spinner)findViewById(R.id.spinnerMateriaevento);
      popularSpinnerMaterias();
      final int idEvento = getIntent().getIntExtra("editarEvento", -1);
      if (idEvento != -1) {
         carregarEvento(idEvento);
      }

      btnSalvarEvento = (FloatingActionButton)findViewById(R.id.btnSalvarEvento);
      btnSalvarEvento.setOnClickListener(new android.view.View.OnClickListener() {
         public void onClick(View view) {
            String nome = txtTituloEvento.getText().toString();
            String data = txtDataEvento.getText().toString();
            String hora = txtHoraEvento.getText().toString();
            String tipo = spinnerTipoEvento.getSelectedItem().toString();
            String materia = spinnerMateriaevento.getSelectedItem().toString();
            String descricao=txtDescricaoEvento.getText().toString();

            if (data.length() > 0 && hora.length() > 0 && nome.length() > 0 && tipo.length() > 0 && materia != "Matéria" && tipo != "Tipo") {
               Evento evento = new Evento();
               evento.setTituloEvento(nome);
               evento.setDataEvento(data);
               evento.setHorarioevento(hora);
               evento.setTipoEvento(tipo);
               evento.setMateriaEvento(materia);
               evento.setDescricao(descricao);
               if (idEvento != -1) {
                  eventoDAO.atualizar(evento);
               } else {
                  eventoDAO.inserirEvento(evento);
                  salvarAlarme(custom_calendar,evento.getId());
               }

               MeusEventos.adapter.notifyDataSetChanged();
               finish();
            } else {
               Toast.makeText(getApplicationContext(), "Você deve preencher todos os campos para salvar o evento", 0).show();
            }
         }
      });
      botaoEscolherData = (ImageButton)findViewById(R.id.buttonEscolherData);
      botaoEscolherData.setOnClickListener(new android.view.View.OnClickListener() {
         public void onClick(View view) {
            (new DatePickerFragment()).show(getSupportFragmentManager(), "date picker");
         }
      });
      botaoEscolherHora = (ImageButton)findViewById(R.id.buttonEscolherHora);
      botaoEscolherHora.setOnClickListener(new android.view.View.OnClickListener() {
         public void onClick(View view) {
            (new TimePickerFragment()).show(getSupportFragmentManager(), "time picker");
         }
      });
   }

   public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
      custom_calendar.set(Calendar.YEAR, year);
      custom_calendar.set(Calendar.MONTH, month);
      custom_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      String data = DateFormat.getDateInstance(2).format(custom_calendar.getTime());
      txtDataEvento.setText(data);
   }


   public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
      custom_calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
      custom_calendar.set(Calendar.MINUTE, minute);
      custom_calendar.set(Calendar.SECOND, 0);
      txtHoraEvento.setText(hourOfDay+":"+minute);
   }

   // Faz com que o botão voltar da toolbar funcione igual ao do celular
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      if (item.getItemId() == android.R.id.home) {
         onBackPressed();
         return true;
      }
      return false;
   }


   public void popularSpinnerMaterias() {
      ArrayList<String> array_materias = new ArrayList();
      array_materias.add("Matéria");
      MateriaDAO materiaDAO = new MateriaDAO(this);
      ArrayList<Materia> lista_materias = null;
      if (materiaDAO.obterTodas() != null) {
         lista_materias = new ArrayList<Materia>(materiaDAO.obterTodas());
         array_materias.add("Todas");
         for(int i = 0; i < lista_materias.size(); ++i) {
            array_materias.add(lista_materias.get(i).getNome());
         }
      }

      ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_materias);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinnerMateriaevento.setAdapter(adapter);
   }

   private void salvarAlarme(Calendar c,int idEvento) {
      AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
      Intent intent = new Intent(this, AlertReceiver.class);
      intent.putExtra("Titulo",txtTituloEvento.getText().toString());
      intent.putExtra("DataEHora",txtDataEvento.getText().toString()+" "+txtHoraEvento.getText().toString());
      intent.putExtra("IdEvento",idEvento);
      PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), idEvento, intent, PendingIntent.FLAG_UPDATE_CURRENT);

      if (c.before(Calendar.getInstance())) {
         c.add(Calendar.DATE, 1);
      }

      alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
      String horario = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
      Toast.makeText(this, "Alarme salvo para às "+horario, Toast.LENGTH_SHORT).show();
   }

}
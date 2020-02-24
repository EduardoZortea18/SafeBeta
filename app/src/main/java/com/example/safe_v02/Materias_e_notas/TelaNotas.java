package com.example.safe_v02.Materias_e_notas;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.Toolbar;

import com.example.safe_v02.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TelaNotas extends AppCompatActivity implements CriarNotaDialog.DialogCriarNotaListener {
   static ArrayAdapter adapter_notas;
   ArrayList<Double> array_notas = new ArrayList();
   Button btnAddNota;
   long idMateria;
   ListView listaDeNotas;
   Materia materia = new Materia();
   MateriaDAO materiaDAO;
   Toolbar toolbar;
   TextView txtMediaDasNotas;

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      setContentView(R.layout.activity_tela_notas);
      toolbar = (Toolbar)findViewById(R.id.toolbarTelaNotas);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      carregarMateria();
      carregarNotas();
      txtMediaDasNotas = (TextView)findViewById(R.id.txtMediaDasNotas);
      DecimalFormat df = new DecimalFormat("0.00");
      txtMediaDasNotas.setText("Média atual: "+df.format(materia.getMedia()));
      listaDeNotas = (ListView)findViewById(R.id.listaDeNotas);
      adapter_notas = new ArrayAdapter<Double>(this, android.R.layout.simple_list_item_1, array_notas);
      listaDeNotas.setAdapter(adapter_notas);

      listaDeNotas.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView parent, View view, final int position, long id) {
            Builder builder = new Builder(TelaNotas.this);
            builder.setTitle("Excluir nota");
            builder.setMessage("Deseja excluir essa nota?");
            builder.setNegativeButton("NÃO", (OnClickListener)null);
            builder.setPositiveButton("SIM", new OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                  //Remove a nota da lista ,atualiza, re-calcula a média e atualiza o banco
                  array_notas.remove(position);
                  adapter_notas.notifyDataSetChanged();
                  String notas = (new Gson()).toJson(array_notas);
                  materia.setNotas(notas);
                  materiaDAO.atualizar(materia);
                  calcularMedia();
               }
            });
            builder.show();
         }
      });
      btnAddNota = (Button)findViewById(R.id.btnAddNota);
      btnAddNota.setOnClickListener(new android.view.View.OnClickListener() {
         public void onClick(View view) {
            //Verifica se a quantidade de notas da matéria ja foi lançada.
            //Se faltam notas a serem lançadas abre o dialog de criar nota
            //Se todas as notas foram lançadas, mostra uma mensagem informando ao usuário
            if (array_notas.size() < materia.getQntdNotas()) {
               (new CriarNotaDialog(materia.getTipoDeMedia())).show(getSupportFragmentManager(), "Criar nota");
            } else {
               Toast.makeText(getApplicationContext(), "Você já atingiu o limite das notas", 0).show();
            }
         }
      });
   }

   //Verifica o tipo de média que a matéria possui e em segudia,
   //realiza o cálculo da média e em segudia, salva no banco de dados e atualiza na tela
   public void calcularMedia() {
      double media;
      if (materia.getTipoDeMedia().equalsIgnoreCase("simples")) {
         int qntdNotas = materia.getQntdNotas();
         media = 0.0F;
         for(int i = 0;i<array_notas.size();i++){
            media += array_notas.get(i);
         }
         media /= qntdNotas;
      }
      else {
         media = 0;
         for(int i = 0;i<array_notas.size();i++){
            media += array_notas.get(i);
         }
      }
      materia.setMedia(media);
      materiaDAO.atualizar(materia);

      //Formata a media com apenas duas casas após a virgula
      DecimalFormat df = new DecimalFormat("0.00");
      txtMediaDasNotas.setText("Média atual: "+df.format(media));
   }

   //A partir do id enviando na intent, serão carregadas as informações e notas da matéria dona desse id
   public void carregarMateria() {
      idMateria = getIntent().getLongExtra("idMateria", -1);
      materiaDAO = new MateriaDAO(this);
      ArrayList<Materia> materias = new ArrayList<Materia>(materiaDAO.obterTodas());

      for(int i = 0; i < materias.size(); ++i) {
         if (materias.get(i).getId() == idMateria) {
            materia = materias.get(i);
            getSupportActionBar().setTitle(materia.getNome());
         }
      }
   }

   //Converte as notas salvas no banco de dados para um arrayListde float e em seguida
   //popula a lista de notas
   public void carregarNotas() {
      Gson gson = new Gson();
      String json = materia.getNotas();
      Type type = new TypeToken<ArrayList>() {}.getType();
      ArrayList<Double> arrayList = gson.fromJson(json, type);
      if (arrayList != null) {
         array_notas = new ArrayList<Double>(arrayList);
      }
   }

   //Esta interface verifica quando o usuário clica no botão salvar do Dialog de criação
   //de nota, pega a nota e salva no banco de dados e em segudia atualiza as informações na tela
   @Override
   public void salvarNota(Double nota) {
      array_notas.add(nota);
      calcularMedia();
      adapter_notas.notifyDataSetChanged();
      String notas = (new Gson()).toJson(array_notas);
      materia.setNotas(notas);
      materiaDAO.atualizar(materia);
   }
}

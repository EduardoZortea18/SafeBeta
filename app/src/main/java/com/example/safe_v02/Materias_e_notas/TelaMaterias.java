package com.example.safe_v02.Materias_e_notas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.widget.Toolbar;

import com.example.safe_v02.R;

import java.util.ArrayList;

public class TelaMaterias extends AppCompatActivity implements CriarMateriaDialog.DialogCriarMateriaListener {
   public static ArrayList<Materia> array_materias = new ArrayList();
   ArrayAdapter adapter_materias = null;
   Button btnAdicionarMateria;
   ListView lista_de_materias;
   MateriaDAO materiaDAO;
   Toolbar toolbar;

   public void abrirDialogCriarMateria(View view) {
      CriarMateriaDialog dialog = new CriarMateriaDialog(null);
      dialog.show(getSupportFragmentManager(),"Criar matéria");
   }

   protected void onCreate(Bundle bundle) {
      super.onCreate(bundle);
      setContentView(R.layout.activity_tela_materias);
      toolbar = (Toolbar)findViewById(R.id.toolbarControleNotas);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle("Suas matérias");
      btnAdicionarMateria = (Button)findViewById(R.id.btnAdicionarMateria);
      materiaDAO = new MateriaDAO(this);
      lista_de_materias = (ListView)findViewById(R.id.lista_de_materias);
      array_materias = materiaDAO.obterTodas();
      adapter_materias = new MateriaAdapter(this, array_materias);
      lista_de_materias.setAdapter(adapter_materias);

      lista_de_materias.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView adapterView, View view, int position, long id) {
            long idMateria = array_materias.get(position).getId();
            Intent intent = new Intent(TelaMaterias.this, TelaNotas.class);
            intent.putExtra("idMateria", idMateria);
            startActivity(intent);
         }
      });
      lista_de_materias.setOnItemLongClickListener(new OnItemLongClickListener() {
         public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            final Materia materia = array_materias.get(position);
            Builder builder = new Builder(TelaMaterias.this);
            builder.setTitle("O que deseja fazer?").setItems(R.array.opcoes_array, new OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                  if (which != 0) {
                     if (which == 1) {
                        (new CriarMateriaDialog(materia)).show(getSupportFragmentManager(), "Criar materia");
                     }
                  } else {
                     Builder builder1 = new Builder(TelaMaterias.this);
                     builder1.setTitle("Excluir matéria");
                     builder1.setMessage("Deseja excluir essa matéria?");
                     builder1.setNegativeButton("NÃO", (OnClickListener)null);
                     builder1.setPositiveButton("SIM", new OnClickListener() {
                        public void onClick(DialogInterface var1, int var2) {
                           materiaDAO.excluir(materia);
                           array_materias.remove(position);
                           adapter_materias.notifyDataSetInvalidated();
                           getApplicationContext().getSharedPreferences("com.example.safe_v02", 0).edit().remove(Integer.toString(materia.getId())).apply();
                        }
                     });
                     builder1.show();
                  }
               }
            });
            builder.show();
            return true;
         }
      });
   }

   public void salvarMateria(Materia materia) {
      if (materia != null) {
         materiaDAO.inserirMateria(materia);
         lista_de_materias = (ListView)findViewById(R.id.lista_de_materias);
         array_materias = materiaDAO.obterTodas();
         adapter_materias = new MateriaAdapter(this, array_materias);
         lista_de_materias.setAdapter(adapter_materias);
      }

   }
}

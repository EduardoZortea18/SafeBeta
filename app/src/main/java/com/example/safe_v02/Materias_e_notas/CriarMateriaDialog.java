package com.example.safe_v02.Materias_e_notas;

import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.safe_v02.R;

import java.util.ArrayList;

public class CriarMateriaDialog extends AppCompatDialogFragment {
   private CriarMateriaDialog.DialogCriarMateriaListener listener;
   private Materia materiaParaEditar;
   private Spinner spinnerTipoMedia;
   private String tipoMediaOld = null;
   private EditText txtNomeDaMateria;
   private EditText txtQntdNotas;

   public CriarMateriaDialog(Materia materiaParaEditar) {
      this.materiaParaEditar = materiaParaEditar;
   }

   public Dialog onCreateDialog(Bundle bundle) {
      Builder builder = new Builder(this.getActivity());
      View view = getActivity().getLayoutInflater().inflate(R.layout.layout_criar_materia, null);
      txtNomeDaMateria = (EditText)view.findViewById(R.id.txtNomeMateria);
      txtQntdNotas = (EditText)view.findViewById(R.id.txtQntdNotas);
      spinnerTipoMedia = (Spinner)view.findViewById(R.id.spinnerTipoDeMedia);
      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.tipos_de_media_array, android.R.layout.simple_spinner_item);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinnerTipoMedia.setAdapter(adapter);

      Materia materia = materiaParaEditar;
      if (materia != null) {
         tipoMediaOld = materia.getTipoDeMedia();
         txtNomeDaMateria.setText(this.materiaParaEditar.getNome());
         txtQntdNotas.setText(Integer.toString(materiaParaEditar.getQntdNotas()));
         ArrayList<String> arrayList = new ArrayList();
         arrayList.add("Tipo de média");
         arrayList.add("Simples");
         arrayList.add("Ponderada");

         for(int i = 0; i < arrayList.size(); ++i) {
            if (arrayList.get(i).equalsIgnoreCase(materiaParaEditar.getTipoDeMedia())) {
               spinnerTipoMedia.setSelection(i);
            }
         }
      }

      builder.setView(view).setTitle("Criar matéria").setNegativeButton("CANCELAR", new OnClickListener() {
         public void onClick(DialogInterface var1, int var2) {
         }
      }).setPositiveButton("CRIAR", new OnClickListener() {
         public void onClick(DialogInterface var1, int var2) {
            String nome = txtNomeDaMateria.getText().toString();
            String tipoDeMedia = spinnerTipoMedia.getSelectedItem().toString();
            if (mediaFoiTrocada(tipoDeMedia)) {
               if (materiaParaEditar != null) {
                  materiaParaEditar.setMedia(0);
                  materiaParaEditar.setNotas(null);
               }
            }

            if (materiaParaEditar != null) {
               if (nome.length() > 0 && tipoDeMedia != "Tipo de média" && txtQntdNotas.getText().toString().length() > 0) {
                  int qntdNotas = Integer.parseInt(txtQntdNotas.getText().toString());
                  if(qntdNotas>0){
                     materiaParaEditar.setNome(nome);
                     materiaParaEditar.setTipoDeMedia(tipoDeMedia);
                     materiaParaEditar.setQntdNotas(qntdNotas);
                     (new MateriaDAO(getActivity())).atualizar(materiaParaEditar);
                     listener.salvarMateria(null);
                  }
                  else{
                     Toast.makeText(getActivity(), "A quantidade de notas deve ser um valor maior que 0", 0).show();
                  }
               }
               else {
                  Toast.makeText(getActivity(), "Dados inválidos. Por favor preencha os campos corretamente.", 0).show();
               }
            } else {
               Materia materia1 = new Materia();
               if (nome.length() > 0 && tipoDeMedia != "Tipo de média" && txtQntdNotas.getText().toString().length() > 0) {
                  int qntdNotas = Integer.parseInt(txtQntdNotas.getText().toString());
                  if(qntdNotas>0){
                     materia1.setNome(nome);
                     materia1.setTipoDeMedia(tipoDeMedia);
                     materia1.setQntdNotas(qntdNotas);
                     listener.salvarMateria(materia1);
                  }
                  else{
                     Toast.makeText(getActivity(), "A quantidade de notas deve ser um valor maior que 0", 0).show();
                  }
               } else {
                  Toast.makeText(getActivity(), "Dados inválidos. Por favor preencha os campos corretamente.", 0).show();
               }
            }
         }
      });
      if (materiaParaEditar != null) {
         builder.setTitle("Editar matéria");
      }

      return builder.create();
   }

   public void onAttach(Context context) {
      super.onAttach(context);

      try {
         this.listener = (CriarMateriaDialog.DialogCriarMateriaListener)context;
      } catch (ClassCastException e) {
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append(context.toString());
         stringBuilder.append("Deve implementar DialogCriarMateria");
         throw new ClassCastException(stringBuilder.toString());
      }
   }

   public boolean mediaFoiTrocada(String tipoDeMedia) {
      return !tipoDeMedia.equalsIgnoreCase(this.tipoMediaOld);
   }

   public interface DialogCriarMateriaListener {
      void salvarMateria(Materia materia);
   }
}

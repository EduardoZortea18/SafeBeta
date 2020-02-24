package com.example.safe_v02.Materias_e_notas;

import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.safe_v02.R;

public class CriarNotaDialog extends AppCompatDialogFragment {
   private CriarNotaDialog.DialogCriarNotaListener listener;
   private String tipoDeMedia;
   private EditText txtNota;
   private EditText txtPesoNota;

   public CriarNotaDialog(String tipodDeMedia) {
      this.tipoDeMedia = tipodDeMedia;
   }

   public void onAttach(Context context) {
      super.onAttach(context);

      try {
         this.listener = (CriarNotaDialog.DialogCriarNotaListener)context;
      } catch (ClassCastException var3) {
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append(context.toString());
         stringBuilder.append("deve implementar DialogCriarNota");
         throw new ClassCastException(stringBuilder.toString());
      }
   }

   public Dialog onCreateDialog(Bundle var1) {
      Builder builder = new Builder(this.getActivity());
      View view = getActivity().getLayoutInflater().inflate(R.layout.layout_criar_nota, null);
      txtNota = (EditText)view.findViewById(R.id.txtNotaMateria);
      txtPesoNota = (EditText)view.findViewById(R.id.txtPesoNota);
      //Verifica se a matéria na qual o usuário quer salvar a nota possui o tipo de média simples ou ponderada
      //Caso for simples não será necessário digitar o peso da nota. Então a caixa de texto é desabilitada
      if (tipoDeMedia.equalsIgnoreCase("simples")) {
         txtPesoNota.setHint("Peso não necessário");
         txtPesoNota.setEnabled(false);
      }

      builder.setView(view).setTitle("Criar nota").setNegativeButton("CANCELAR", new OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
         }
      }).setPositiveButton("CRIAR", new OnClickListener() {
         public void onClick(DialogInterface dialog, int which) {
            //Verifica se o usuário preencheu todas as informações corretamente e em segudia,
            //retorna a nota para a classe TelaNotas através do listener(interface)
               if (tipoDeMedia.equalsIgnoreCase("simples")) {
                  if (txtNota.getText().toString().length() <= 0) {
                     Toast.makeText(getActivity().getApplicationContext(), "Campo de nota vazio", 0).show();
                  }
                  else{
                     double nota = Double.parseDouble(txtNota.getText().toString());
                     if (nota >= 0) {
                        listener.salvarNota(nota);
                     }
                     else{
                        Toast.makeText(getActivity().getApplicationContext(), "O valor da nota deve ser maior ou igual à 0", 0).show();
                     }
                  }
               }
               else if (tipoDeMedia.equalsIgnoreCase("ponderada")) {
                  if(txtNota.getText().toString().length()>0&&txtPesoNota.getText().toString().length()>0){
                     double nota = Float.parseFloat(txtNota.getText().toString());
                     float peso = Float.parseFloat(txtPesoNota.getText().toString());
                     if ((nota >= 0) && (peso >= 1) && (peso <= 100)) {
                        nota = (nota * peso )/ 100;
                        listener.salvarNota(nota);
                     }
                     else if((peso<1) || (peso>100)){
                        Toast.makeText(getActivity().getApplicationContext(), "Peso deve ser um valor entre 1 e 100", 0).show();
                     }
                  }
                  else{
                     Toast.makeText(getActivity().getApplicationContext(), "Você deve preencher todos os campos para salvar a nota", 0).show();
                  }
               }

         }
      });
      return builder.create();
   }

   //Interface que retorna a nota para a classe TelaNotas
   public interface DialogCriarNotaListener {
      void salvarNota(Double nota);
   }
}

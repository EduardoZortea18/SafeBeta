package com.example.safe_v02.Estatisticas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.safe_v02.Materias_e_notas.MateriaDAO;
import com.example.safe_v02.R;

import java.util.ArrayList;

public class Estatisticas extends Fragment {
   AdapterEstatistica adapterEstatistica = null;
   ArrayList array_materias = null;
   ListView listaEstatisticas;
   MateriaDAO materiaDAO;
   TextView txtAviso,txtStats1,txtStats2;

   public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
      View view = layoutInflater.inflate(R.layout.fragment_estatisticas, container, false);
      listaEstatisticas = (ListView)view.findViewById(R.id.listaEstatisticas);
      materiaDAO = new MateriaDAO(getActivity());
      array_materias = materiaDAO.obterTodas();
      adapterEstatistica = new AdapterEstatistica(getActivity(), array_materias);
      listaEstatisticas.setAdapter(adapterEstatistica);

      txtAviso = (TextView)view.findViewById(R.id.txtAvisoEstatisticas);
      txtStats1 = (TextView)view.findViewById(R.id.txtStats1);
      txtStats2 = (TextView)view.findViewById(R.id.txtStats2);
      verificarEstatisticas();

      return view;
   }

   public void onResume() {
      super.onResume();
      array_materias = this.materiaDAO.obterTodas();
      adapterEstatistica = new AdapterEstatistica(getActivity(), array_materias);
      listaEstatisticas.setAdapter(adapterEstatistica);
      verificarEstatisticas();
   }

   public void verificarEstatisticas(){
      if(array_materias.size()<=0){
         txtAviso.setText(R.string.MsgMaterias);
         txtStats1.setText(" ");
         txtStats2.setText(" ");
      }
      else{
         txtAviso.setText(" ");
         txtStats1.setText("Nome da matéria:");
         txtStats2.setText("Média atual: ");
      }
   }
}

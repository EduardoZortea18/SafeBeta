package com.example.safe_v02.Horarios;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safe_v02.Horarios.DialogCriarHorario.DialogCriarHorarioListener;
import com.example.safe_v02.R;
import java.util.ArrayList;
import java.util.Calendar;


public class Horarios extends Fragment implements AdapterView.OnItemSelectedListener, DialogCriarHorarioListener {
    Spinner spinnerDiasDaSemana;
    ListView listaHorarios;
    Button btnAdicionaraula;
    ArrayList<Horario> horario_segunda = new ArrayList<Horario>();
    ArrayList<Horario> horario_terca = new ArrayList<Horario>();
    ArrayList<Horario> horario_quarta = new ArrayList<Horario>();
    ArrayList<Horario> horario_quinta = new ArrayList<Horario>();
    ArrayList<Horario> horario_sexta = new ArrayList<Horario>();

    static ArrayAdapter<Horario> adapter_lista_horarios = null;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Criando view para o fragmento
        View view = inflater.inflate(R.layout.fragment_horarios, container, false);

        spinnerDiasDaSemana=(Spinner)view.findViewById(R.id.spinnerDiasDaSemana);
        listaHorarios=(ListView)view.findViewById(R.id.listaHorarios);
        ArrayAdapter<CharSequence> adapter_spinner = ArrayAdapter.createFromResource(this.getActivity(),R.array.horarios_array, android.R.layout.simple_spinner_item);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiasDaSemana.setAdapter(adapter_spinner);
        spinnerDiasDaSemana.setOnItemSelectedListener(this);

        carregarHorarios();

        txtAviso = (TextView)view.findViewById(R.id.txtAvisoHorarios);


        btnAdicionaraula = (Button)view.findViewById(R.id.btnAdicionaraula);
        btnAdicionaraula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dia=spinnerDiasDaSemana.getSelectedItem().toString();
                if(dia.equalsIgnoreCase("Selecione o dia")) {
                    Toast.makeText(getActivity().getApplicationContext(),"Você deve selecionar um dia para adicionar uma aula", Toast.LENGTH_SHORT).show();
                }
                else{
                    abrirDialogCriarHorario();
                }
            }
        });

        listaHorarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder opcoes = new AlertDialog.Builder(getActivity());
                opcoes.setTitle("O que deseja fazer?")
                        .setItems(R.array.opcoes_array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // O "which" contem a posição do indice do item selecionado
                                switch (which){
                                    case 0:
                                        AlertDialog.Builder adb= new AlertDialog.Builder(getActivity());
                                        adb.setTitle("Excluir");
                                        adb.setMessage("Deseja excluir esse horário?");
                                        final int positionToMove = position;
                                        adb.setNegativeButton("NÂO",null);
                                        adb.setPositiveButton("SIM",new AlertDialog.OnClickListener(){
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                excluirHorario(position);
                                            }
                                        });
                                        adb.show();
                                        break;
                                    case 1:
                                        modificarHorario(position);
                                        break;
                                }

                            }
                        });
                opcoes.show();
            }
        });

//Verifica em que dia da semana está e mostra os horários desse dia
        verificarODia();
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String dia=spinnerDiasDaSemana.getSelectedItem().toString();
        switch(dia){
            case "Selecione o dia":
                listaHorarios.setAdapter(null);
                break;
            case "Segunda-feira":
                adapter_lista_horarios = new HorarioAdapter(getActivity(),horario_segunda);
                listaHorarios.setAdapter(adapter_lista_horarios);
                break;
            case "Terça-feira":
                adapter_lista_horarios = new HorarioAdapter(getActivity(),horario_terca);
                listaHorarios.setAdapter(adapter_lista_horarios);
                break;
            case "Quarta-feira":
                adapter_lista_horarios = new HorarioAdapter(getActivity(),horario_quarta);
                listaHorarios.setAdapter(adapter_lista_horarios);
                break;
            case "Quinta-feira":
                adapter_lista_horarios = new HorarioAdapter(getActivity(),horario_quinta);
                listaHorarios.setAdapter(adapter_lista_horarios);
                break;
            case "Sexta-feira":
                adapter_lista_horarios = new HorarioAdapter(getActivity(),horario_sexta);
                listaHorarios.setAdapter(adapter_lista_horarios);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void excluirHorario(final int position){
        String dia=spinnerDiasDaSemana.getSelectedItem().toString();
        HorarioDAO horarioDAO = new HorarioDAO(getActivity());
        switch(dia){
            case "Segunda-feira":
                horarioDAO.excluir(horario_segunda.get(position));
                horario_segunda.remove(position);
                adapter_lista_horarios.notifyDataSetInvalidated();
                break;
            case "Terça-feira":
                horarioDAO.excluir(horario_terca.get(position));
                horario_terca.remove(position);
                adapter_lista_horarios.notifyDataSetInvalidated();
                break;
            case "Quarta-feira":
                horarioDAO.excluir(horario_quarta.get(position));
                horario_quarta.remove(position);
                adapter_lista_horarios.notifyDataSetInvalidated();
                break;
            case "Quinta-feira":
                horarioDAO.excluir(horario_quinta.get(position));
                horario_quinta.remove(position);
                adapter_lista_horarios.notifyDataSetInvalidated();
                break;
            case "Sexta-feira":
                horarioDAO.excluir(horario_sexta.get(position));
                horario_sexta.remove(position);
                adapter_lista_horarios.notifyDataSetInvalidated();
                break;
        }
    }

    public void modificarHorario(final int position){
        String dia=spinnerDiasDaSemana.getSelectedItem().toString();
        Horario horario = null;
        switch(dia){
            case "Segunda-feira":
                horario=horario_segunda.get(position);
                break;
            case "Terça-feira":
                horario=horario_terca.get(position);
                break;
            case "Quarta-feira":
                horario=horario_quarta.get(position);
                break;
            case "Quinta-feira":
                horario=horario_quinta.get(position);
            case "Sexta-feira":
                horario=horario_sexta.get(position);
                break;
        }
        DialogCriarHorario dialog = new DialogCriarHorario(horario);
        dialog.setTargetFragment(Horarios.this,1);
        dialog.show(getActivity().getSupportFragmentManager(),"Editar horário");
    }


    public void abrirDialogCriarHorario(){
        DialogCriarHorario dialog = new DialogCriarHorario(null);
        dialog.setTargetFragment(Horarios.this,1);
        dialog.show(getActivity().getSupportFragmentManager(),"Criar horário");
    }

    @Override
    public void salvarHorario(Horario horario) {
        String dia=spinnerDiasDaSemana.getSelectedItem().toString();
        if(horario!=null){
            switch(dia) {
                case "Segunda-feira":
                    horario.setDia(1);
                    horario_segunda.add(horario);
                    adapter_lista_horarios.notifyDataSetChanged();
                    break;
                case "Terça-feira":
                    horario.setDia(2);
                    horario_terca.add(horario);
                    adapter_lista_horarios.notifyDataSetChanged();
                    break;
                case "Quarta-feira":
                    horario.setDia(3);
                    horario_quarta.add(horario);
                    adapter_lista_horarios.notifyDataSetChanged();
                    break;
                case "Quinta-feira":
                    horario.setDia(4);
                    horario_quinta.add(horario);
                    adapter_lista_horarios.notifyDataSetChanged();
                    break;
                case "Sexta-feira":
                    horario.setDia(5);
                    horario_sexta.add(horario);
                    adapter_lista_horarios.notifyDataSetChanged();
                    break;
            }
            HorarioDAO horarioDAO = new HorarioDAO(getActivity());
            horarioDAO.inserirHorario(horario);
        }
    }

//Verifica em que dia da semana está e mostra os horários desse dia
    public void verificarODia(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                spinnerDiasDaSemana.setSelection(0);
                break;
            case Calendar.MONDAY:
                spinnerDiasDaSemana.setSelection(1);
                break;
            case Calendar.TUESDAY:
                spinnerDiasDaSemana.setSelection(2);
                break;
            case Calendar.WEDNESDAY:
                spinnerDiasDaSemana.setSelection(3);
                break;
            case Calendar.THURSDAY:
                spinnerDiasDaSemana.setSelection(4);
                break;
            case Calendar.FRIDAY:
                spinnerDiasDaSemana.setSelection(5);
                break;
            case Calendar.SATURDAY:
                spinnerDiasDaSemana.setSelection(0);
                break;
        }
    }

    public void carregarHorarios(){
        HorarioDAO horarioDAO = new HorarioDAO(getActivity());
            ArrayList<Horario> horarios = new ArrayList<Horario>(horarioDAO.obterTodos());
            for(int i=0;i<horarios.size();i++){
                switch(horarios.get(i).getDia()) {
                    case 1:
                        horario_segunda.add(horarios.get(i));
                        break;
                    case 2:
                        horario_terca.add(horarios.get(i));
                        break;
                    case 3:
                        horario_quarta.add(horarios.get(i));
                        break;
                    case 4:
                        horario_quinta.add(horarios.get(i));
                        break;
                    case 5:
                        horario_sexta.add(horarios.get(i));
                        break;
                }
            }
    }


}

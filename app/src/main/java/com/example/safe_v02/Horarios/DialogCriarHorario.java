package com.example.safe_v02.Horarios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.safe_v02.Materias_e_notas.Materia;
import com.example.safe_v02.Materias_e_notas.MateriaDAO;
import com.example.safe_v02.R;

import java.util.ArrayList;
import java.util.Calendar;

public class DialogCriarHorario extends AppCompatDialogFragment {

    private DialogCriarHorarioListener listener;
    private Spinner spinnerNomeAula;
    private TextView txtInicioDaAula,txtTerminoDaAula;
    private Horario horarioParaEditar;

    public DialogCriarHorario(Horario horarioParaEditar){
        this.horarioParaEditar=horarioParaEditar;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_criar_horario, null);


        txtInicioDaAula = (TextView)view.findViewById(R.id.txtInicioDaAula);
        txtTerminoDaAula = (TextView)view.findViewById(R.id.txtTerminoDaAula);
        spinnerNomeAula = (Spinner)view.findViewById(R.id.spinnerNomeAula);
        popularSpinnerNomeAula();

        txtInicioDaAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                final int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int horaSelecionada, int minutoSelecionado) {
                        txtInicioDaAula.setText(horaSelecionada+":"+minutoSelecionado);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Selecione o horário");
                mTimePicker.show();
            }
        });

        txtTerminoDaAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                final int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int horaSelecionada, int minutoSelecionado) {
                        txtTerminoDaAula.setText(horaSelecionada+":"+minutoSelecionado);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Selecione o horário");
                mTimePicker.show();
            }
        });


        builder.setView(view)
                .setTitle("Criar horário")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("CRIAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String materia,horaInicio,horaTermino;
                        if((spinnerNomeAula.getSelectedItem().toString()!="Matéria")&&(txtInicioDaAula.getText().toString().equalsIgnoreCase("horário de inicio")==false)&&(txtTerminoDaAula.getText().toString().equalsIgnoreCase("horário de término")==false)){
                            horaInicio=txtInicioDaAula.getText().toString();
                            horaTermino=txtTerminoDaAula.getText().toString();
                            materia = spinnerNomeAula.getSelectedItem().toString();
                            if(horarioParaEditar==null){
                                Horario horario = new Horario();
                                horario.setMateria(materia);
                                horario.setHoraInicio(horaInicio);
                                horario.setHoraTermino(horaTermino);
                                listener.salvarHorario(horario);
                            }
                            else{
                                HorarioDAO horarioDAO = new HorarioDAO(getActivity());
                                horarioParaEditar.setMateria(materia);
                                horarioParaEditar.setHoraInicio(horaInicio);
                                horarioParaEditar.setHoraTermino(horaTermino);
                                horarioDAO.atualizar(horarioParaEditar);
                                Horarios.adapter_lista_horarios.notifyDataSetChanged();
                                listener.salvarHorario(null);
                            }
                        }
                        else{
                            Toast.makeText(getActivity().getApplicationContext(),"Você deve preencher todos os campos para criar o horário", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                if(horarioParaEditar!=null){
                    builder.setTitle("Editar horário");
                }
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogCriarHorarioListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "deve implementar DialogCriarHorario");
        }
    }

    public interface DialogCriarHorarioListener {
        void salvarHorario(Horario horario);
    }

    public void popularSpinnerNomeAula() {
        ArrayList<String> array_materias = new ArrayList();
        array_materias.add("Matéria");
        MateriaDAO materiaDAO = new MateriaDAO(getActivity());
        ArrayList<Materia> lista_materias = null;
        if (materiaDAO.obterTodas() != null) {
            lista_materias = new ArrayList<Materia>(materiaDAO.obterTodas());
            for(int i = 0; i < lista_materias.size(); ++i) {
                array_materias.add(lista_materias.get(i).getNome());
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, array_materias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNomeAula.setAdapter(adapter);

        if(horarioParaEditar!=null){
            txtInicioDaAula.setText(horarioParaEditar.getHoraInicio());
            txtTerminoDaAula.setText(horarioParaEditar.getHoraTermino());
            for(int i=0; i < array_materias.size(); ++i) {
                if (array_materias.get(i).equalsIgnoreCase(horarioParaEditar.getMateria())) {
                    spinnerNomeAula.setSelection(i);
                }
            }
        }

    }

}
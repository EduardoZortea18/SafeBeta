package com.example.safe_v02.Horarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.safe_v02.Banco_de_dados.Conexao;
import java.util.ArrayList;

public class HorarioDAO {
   private SQLiteDatabase banco;
   private Conexao conexao;

   public HorarioDAO(Context context) {
      this.conexao = new Conexao(context);
      this.banco = this.conexao.getWritableDatabase();
   }

   public void atualizar(Horario horario) {
      ContentValues values = new ContentValues();
      values.put("id", horario.getId());
      values.put("horaInicio", horario.getHoraInicio());
      values.put("horaTermino", horario.getHoraTermino());
      values.put("materia", horario.getMateria());
      values.put("dia", horario.getDia());
      SQLiteDatabase banco = this.banco;
      String[] id = new String[]{Integer.toString(horario.getId())};
      banco.update("horarios", values, "id=?", id);
   }

   public void excluir(Horario horario) {
      SQLiteDatabase banco = this.banco;
      String[] id = new String[]{Integer.toString(horario.getId())};
      banco.delete("horarios", "id=?", id);
   }

   public long inserirHorario(Horario horario) {
      ContentValues values = new ContentValues();
      values.put("horaInicio", horario.getHoraInicio());
      values.put("horaTermino", horario.getHoraTermino());
      values.put("materia", horario.getMateria());
      values.put("dia", horario.getDia());
      return this.banco.insert("horarios", (String)null, values);
   }

   public ArrayList obterTodos() {
      ArrayList horarios = new ArrayList();
      Cursor cursor = this.banco.query("horarios", new String[]{"id", "horaInicio", "horaTermino", "materia", "dia"}, (String)null, (String[])null, (String)null, (String)null, (String)null);

      while(cursor.moveToNext()) {
         Horario horario = new Horario();
         horario.setId(cursor.getInt(0));
         horario.setHoraInicio(cursor.getString(1));
         horario.setHoraTermino(cursor.getString(2));
         horario.setMateria(cursor.getString(3));
         horario.setDia(cursor.getInt(4));
         horarios.add(horario);
      }
      return horarios;
   }
}

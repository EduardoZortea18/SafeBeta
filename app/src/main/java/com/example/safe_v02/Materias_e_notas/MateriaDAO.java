package com.example.safe_v02.Materias_e_notas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.safe_v02.Banco_de_dados.Conexao;
import java.util.ArrayList;

public class MateriaDAO {
   private SQLiteDatabase banco;
   private Conexao conexao;

   public MateriaDAO(Context context) {
      this.conexao = new Conexao(context);
      this.banco = this.conexao.getWritableDatabase();
   }

   public void atualizar(Materia materia) {
      ContentValues values = new ContentValues();
      values.put("id", materia.getId());
      values.put("nome", materia.getNome());
      values.put("tipoDemedia", materia.getTipoDeMedia());
      values.put("qntdNotas", materia.getQntdNotas());
      values.put("media", materia.getMedia());
      values.put("notas",materia.getNotas());
      SQLiteDatabase banco = this.banco;
      String[] id = new String[]{Integer.toString(materia.getId())};
      banco.update("materias", values, "id=?", id);
   }

   public void excluir(Materia materia) {
      SQLiteDatabase banco = this.banco;
      String[] id = new String[]{Integer.toString(materia.getId())};
      banco.delete("materias", "id=?", id);
   }

   public long inserirMateria(Materia materia) {
      ContentValues values = new ContentValues();
      values.put("nome", materia.getNome());
      values.put("tipoDeMedia", materia.getTipoDeMedia());
      values.put("qntdNotas", materia.getQntdNotas());
      values.put("media", materia.getMedia());
      values.put("notas",materia.getNotas());
      return this.banco.insert("materias", (String)null, values);
   }

   public ArrayList obterTodas() {
      ArrayList materias = new ArrayList();
      Cursor cursor = this.banco.query("materias", new String[]{"id", "nome", "tipoDeMedia", "qntdNotas", "media","notas"}, (String)null, (String[])null, (String)null, (String)null, (String)null);

      while(cursor.moveToNext()) {
         Materia materia = new Materia();
         materia.setId(cursor.getInt(0));
         materia.setNome(cursor.getString(1));
         materia.setTipoDeMedia(cursor.getString(2));
         materia.setQntdNotas(cursor.getInt(3));
         materia.setMedia(cursor.getFloat(4));
         materia.setNotas(cursor.getString(5));
         materias.add(materia);
      }
      return materias;
   }
}

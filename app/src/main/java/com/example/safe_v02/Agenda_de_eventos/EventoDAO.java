package com.example.safe_v02.Agenda_de_eventos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.safe_v02.Banco_de_dados.Conexao;
import java.util.ArrayList;

public class EventoDAO {
   private SQLiteDatabase banco;
   private Conexao conexao;

   public EventoDAO(Context context) {
      this.conexao = new Conexao(context);
      this.banco = this.conexao.getWritableDatabase();
   }

   public void atualizar(Evento evento) {
      ContentValues values = new ContentValues();
      values.put("id", evento.getId());
      values.put("nome", evento.getTituloEvento());
      values.put("tipo", evento.getTipoEvento());
      values.put("materia", evento.getMateriaEvento());
      values.put("data", evento.getDataEvento());
      values.put("hora", evento.getHorarioevento());
      values.put("descricao", evento.getDescricao());
      values.put("idCriacao", evento.getIdCriacao());
      SQLiteDatabase banco = this.banco;
      String[] id = new String[]{Integer.toString(evento.getId())};
      banco.update("eventos", values, "id=?", id);
   }

   public void excluir(Evento evento) {
      SQLiteDatabase banco = this.banco;
      String[] id = new String[]{Integer.toString(evento.getId())};
      banco.delete("eventos", "id=?", id);
   }

   public long inserirEvento(Evento evento) {
      ContentValues values = new ContentValues();
      values.put("nome", evento.getTituloEvento());
      values.put("tipo", evento.getTipoEvento());
      values.put("materia", evento.getMateriaEvento());
      values.put("data", evento.getDataEvento());
      values.put("hora", evento.getHorarioevento());
      values.put("descricao", evento.getDescricao());
      values.put("idCriacao", evento.getIdCriacao());
      return this.banco.insert("eventos", (String)null, values);
   }

   public ArrayList obterTodos() {
      ArrayList eventos = new ArrayList();
      Cursor cursor = this.banco.query("eventos", new String[]{"id", "nome", "data", "tipo", "materia", "hora","descricao","idCriacao"}, (String)null, (String[])null, (String)null, (String)null, (String)null);

      while(cursor.moveToNext()) {
         Evento evento = new Evento();
         evento.setId(cursor.getInt(0));
         evento.setTituloEvento(cursor.getString(1));
         evento.setDataEvento(cursor.getString(2));
         evento.setTipoEvento(cursor.getString(3));
         evento.setMateriaEvento(cursor.getString(4));
         evento.setHorarioevento(cursor.getString(5));
         evento.setDescricao(cursor.getString(6));
         evento.setIdCriacao(cursor.getInt(7));
         eventos.add(evento);
      }
      return eventos;
   }
}

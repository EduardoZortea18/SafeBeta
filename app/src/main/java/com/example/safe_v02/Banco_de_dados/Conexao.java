package com.example.safe_v02.Banco_de_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class Conexao extends SQLiteOpenHelper {
   private static final String nomeDoBanco = "BancoSafe";
   private static final int versaoBanco = 1;

   public Conexao(Context bancoSafe) {
      super(bancoSafe, "BancoSafe", (CursorFactory)null, 1);
   }

   public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table eventos(id integer primary key ,nome varchar(50),tipo varchar(20),materia varchar(50),data varchar(50),hora varchar(10),descricao varchar(200))");
      db.execSQL("create table horarios(id integer primary key  ,dia integer,horaInicio varchar(5),horaTermino varchar(5),materia varchar(50))");
      db.execSQL("create table materias(id integer primary key  ,nome varchar(50),tipoDeMedia varchar(20),qntdNotas integer,media double,notas varchar(200))");
   }

   public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
   }
}

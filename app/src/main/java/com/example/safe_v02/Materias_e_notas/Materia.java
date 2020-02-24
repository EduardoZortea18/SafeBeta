package com.example.safe_v02.Materias_e_notas;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Materia implements Serializable {
   private int id;
   private double media;
   private String nome;
   private int qntdNotas;

   public String getNotas() {
      return notas;
   }

   public void setNotas(String notas) {
      this.notas = notas;
   }

   private String tipoDeMedia,notas;

   public int getId() {
      return this.id;
   }

   public double getMedia() {
      return this.media;
   }

   public String getNome() {
      return this.nome;
   }

   public int getQntdNotas() {
      return this.qntdNotas;
   }

   public String getTipoDeMedia() {
      return this.tipoDeMedia;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setMedia(double var1) {
      this.media = var1;
   }

   public void setMedia(String var1, DecimalFormat var2) {
   }

   public void setNome(String var1) {
      this.nome = var1;
   }

   public void setQntdNotas(int var1) {
      this.qntdNotas = var1;
   }

   public void setTipoDeMedia(String var1) {
      this.tipoDeMedia = var1;
   }
}

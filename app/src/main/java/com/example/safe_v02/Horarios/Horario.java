package com.example.safe_v02.Horarios;

import java.io.Serializable;

public class Horario implements Serializable {
   private int dia;
   private String horaInicio;
   private String horaTermino;
   private int id;
   private String materia;

   public int getDia() {
      return this.dia;
   }

   public String getHoraInicio() {
      return this.horaInicio;
   }

   public String getHoraTermino() {
      return this.horaTermino;
   }

   public int getId() {
      return this.id;
   }

   public String getMateria() {
      return this.materia;
   }

   public void setDia(int var1) {
      this.dia = var1;
   }

   public void setHoraInicio(String var1) {
      this.horaInicio = var1;
   }

   public void setHoraTermino(String var1) {
      this.horaTermino = var1;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setMateria(String var1) {
      this.materia = var1;
   }
}

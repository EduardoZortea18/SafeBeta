package com.example.safe_v02.Agenda_de_eventos;

import java.io.Serializable;

public class Evento implements Serializable {
   private String dataEvento;
   private String horarioevento;
   private int id,idCriacao;
   private String materiaEvento;
   private String tipoEvento;

   public int getIdCriacao() {
      return idCriacao;
   }

   public void setIdCriacao(int idCriacao) {
      this.idCriacao = idCriacao;
   }

   private String tituloEvento;

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   private String descricao;

   public String getDataEvento() {
      return this.dataEvento;
   }

   public String getHorarioevento() {
      return this.horarioevento;
   }

   public int getId() {
      return this.id;
   }

   public String getMateriaEvento() {
      return this.materiaEvento;
   }

   public String getTipoEvento() {
      return this.tipoEvento;
   }

   public String getTituloEvento() {
      return this.tituloEvento;
   }

   public void setDataEvento(String var1) {
      this.dataEvento = var1;
   }

   public void setHorarioevento(String var1) {
      this.horarioevento = var1;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setMateriaEvento(String var1) {
      this.materiaEvento = var1;
   }

   public void setTipoEvento(String var1) {
      this.tipoEvento = var1;
   }

   public void setTituloEvento(String var1) {
      this.tituloEvento = var1;
   }
}

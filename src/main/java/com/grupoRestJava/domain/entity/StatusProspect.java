package com.grupoRestJava.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Status_Prospect")
public class StatusProspect extends BaseEntidade<Integer> {

   private static final long serialVersionUID = 492472753851383923L;

   @Column(name = "IsAcessoPorAlcada", nullable = false)
   private Boolean           acessoPorAlcada;

   @Column(name = "Descricao", length = 30, nullable = false)
   private String            descricao;

   @Id
   @Column(name = "PK_StatusProspect")
   private Integer           id;

   @Column(name = "IsStatusAtivo", nullable = false)
   private Boolean           statusAtivo;

   public Boolean getAcessoPorAlcada() {
      return this.acessoPorAlcada;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public Integer getId() {
      return this.id;
   }

   public Boolean getStatusAtivo() {
      return this.statusAtivo;
   }

   public void setAcessoPorAlcada(Boolean acessoPorAlcada) {
      this.acessoPorAlcada = acessoPorAlcada;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public void setStatusAtivo(Boolean statusAtivo) {
      this.statusAtivo = statusAtivo;
   }

}

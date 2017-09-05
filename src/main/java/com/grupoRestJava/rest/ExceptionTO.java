package com.grupoRestJava.rest;

public class ExceptionTO {

   private String  mensagem;

   private String  nomeClassException;

   private Boolean userException;

   public String getMensagem() {
      return this.mensagem;
   }

   public String getNomeClassException() {
      return this.nomeClassException;
   }

   public Boolean getUserException() {
      return this.userException;
   }

   public void setMensagem(final String mensagem) {
      this.mensagem = mensagem;
   }

   public void setNomeClassException(final String nomeClassException) {
      this.nomeClassException = nomeClassException;
   }

   public void setUserException(final Boolean userException) {
      this.userException = userException;
   }

}

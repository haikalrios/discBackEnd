package com.grupoRestJava.domain.entity;

import java.io.Serializable;


public abstract class BaseEntidade<ID> implements Serializable {

   private static final long             serialVersionUID = 1L;

   private ID                            idByReflection;

   /**
    * M�todo criado para corrigir um problebla na convers�o via dozer Esse
    * metodo era abstrato e quando chamada a convers�o do dozer o id, (m�todo
    * chamava getId) n�o era preenchido, tudo se deu por conta do generics no
    * retorno.
    * 
    */
   protected ID getIdByReflection() {
      if (this.idByReflection == null) {
         try {
            this.idByReflection = (ID) this.getClass().getDeclaredField("id");
         } catch (SecurityException exec) {

            throw new RuntimeException(
                  "Uma viola��o de seguran�a ocorreu na class"
                        + this.getClass().getCanonicalName()
                        + " m�todo getIdByReflection", exec);
         } catch (NoSuchFieldException exec) {
            throw new RuntimeException(
                  "N�o foi encontrado field ID na class"
                        + this.getClass().getCanonicalName(), exec);

         }
      }
      return this.idByReflection;

   }

   @Override
   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (this.getClass() != obj.getClass()) {
         return false;
      }
      BaseEntidade<?> other = (BaseEntidade<?>) obj;
      if (this.getIdByReflection() == null) {
         if (other.getIdByReflection() != null) {
            return false;
         }
      } else if (!this.getIdByReflection().equals(other.getIdByReflection())) {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result)
            + ((this.getIdByReflection() == null) ? 0 : this
                  .getIdByReflection().hashCode());
      return result;
   }



}

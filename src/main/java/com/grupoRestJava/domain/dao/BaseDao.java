package com.grupoRestJava.domain.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.grupoRestJava.domain.entity.BaseEntidade;

public abstract class BaseDao<T extends BaseEntidade<ID>, ID> implements BaseDaoIF<T, ID> {

   protected static final int MAXIMO_RESULTADO = 50;

   private Class<T>           clazz            = null;

   @PersistenceContext(type = PersistenceContextType.TRANSACTION)
   protected EntityManager    entityManager;

  

   private String criarAlias(final Criteria criteria, final String campoReferenciado, final Set<String> aliasCriteria) {
      String camoRef = campoReferenciado;
      if (campoReferenciado.contains(".")) {
         String[] divisao = campoReferenciado.split("\\.");
         for (int i = 0; i < divisao.length; i++) {
            camoRef = ((i == 0) ? this.getClazz().getSimpleName().toLowerCase() + "." + divisao[i] : divisao[i - 1] + "." + divisao[i]);
            if ((i < (divisao.length - 1)) && !aliasCriteria.contains(divisao[i])) {
               aliasCriteria.add(divisao[i]);
               criteria.createAlias(camoRef, divisao[i]);
            }
         }
      }
      return camoRef;
   }

   @SuppressWarnings("unchecked")
   protected Class<T> getClazz() {
      if (this.clazz == null) {
         this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      }
      return this.clazz;
   }

   protected Integer getRegistroInicialPagina(Integer pagina) {
      return (pagina * MAXIMO_RESULTADO) - MAXIMO_RESULTADO;
   }

   @Override
   public Criteria createCriteria() {
      Session session = (Session) this.entityManager.getDelegate();
      Criteria c = session.createCriteria(this.getClazz(), this.getClazz().getSimpleName().toLowerCase());
      c.setMaxResults(MAXIMO_RESULTADO);
      return c;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<T> findAll() throws Exception {
      List<T> resultado = this.entityManager.createQuery("from " + this.getClazz().getSimpleName()).setMaxResults(MAXIMO_RESULTADO)
            .getResultList();
      if (resultado.size() > MAXIMO_RESULTADO) {
         throw new Exception("Sua pesquisa retornou " + MAXIMO_RESULTADO + " ou mais registros. Refine sua pesquisa");
      }
      return resultado;
   }

   @Override
   public T findById(final ID obj) {
      return this.entityManager.find(this.getClazz(), obj);
   }


   @Override
   public Boolean isEmpty(final Object number) {
      return number == null;
   }

   @Override
   public void persist(final T obj) {
      if (this.entityManager.contains(obj)) {
         this.entityManager.merge(obj);
      } else {
         this.entityManager.persist(obj);
      }
      this.entityManager.flush();
   }

  

   @Override
   public void remove(final T obj) {
      this.entityManager.remove(obj);
      this.entityManager.flush();
   }

}

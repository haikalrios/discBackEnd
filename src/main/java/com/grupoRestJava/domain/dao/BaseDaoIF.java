package com.grupoRestJava.domain.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.grupoRestJava.domain.entity.BaseEntidade;

public interface BaseDaoIF<T extends BaseEntidade<ID>, ID> {


   public Criteria createCriteria();

   @SuppressWarnings("unchecked")
   public List<T> findAll() throws Exception;

   public T findById(final ID obj);

   @SuppressWarnings("unchecked")

   public Boolean isEmpty(final Object number);

   public void persist(final T obj);

  
   public void remove(final T obj);

}
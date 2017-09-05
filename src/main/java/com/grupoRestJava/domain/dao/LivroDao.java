package com.grupoRestJava.domain.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.grupoRestJava.domain.entity.Livro;

@Stateless
public class LivroDao extends BaseDao<Livro, Integer> {
	
	public List<Livro> buscarLivrosPorTitulo(String titulo){
		Query query =  entityManager.createQuery("select l from Livro l where l.titulo like '%"+titulo+"%'");
		return (( List<Livro>)query.getResultList()); 
		
	}

}

package com.grupoRestJava.AS;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.grupoRestJava.domain.dao.StatusProspectDAO;
import com.grupoRestJava.domain.entity.StatusProspect;

@Stateless
public class LivrosAS {
	
	@EJB
	private StatusProspectDAO statusDAO;
	
	public void salvar(StatusProspect statusProspect) {
		statusDAO.persist(statusProspect);

	}
	
	public List<StatusProspect> listAll() throws Exception {
		return statusDAO.findAll();

	}
	
	

}

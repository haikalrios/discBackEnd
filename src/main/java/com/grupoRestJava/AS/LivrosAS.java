package com.grupoRestJava.AS;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.grupoRestJava.domain.dao.LivroDao;
import com.grupoRestJava.domain.entity.Livro;

@Stateless
public class LivrosAS {

	@EJB
	private LivroDao livroDao;

	public Livro salvar(Livro statusProspect) throws Exception {
		if (statusProspect == null) {
			throw new Exception("Preciso passa o livro");
		}
		if (statusProspect.getDescricao() == null) {
			throw new Exception("Preciso passar a decsrição");
		}

		if (statusProspect.getIsbn() == null) {
			throw new Exception("Preciso passr o ISBN");
		}

		if (statusProspect.getSubTitulo() == null) {
			throw new Exception("Preciso passar o subtitulo");
		}
		if (statusProspect.getTitulo() == null) {
			throw new Exception("Preciso passar o titulo");
		}

		livroDao.persist(statusProspect);

		return statusProspect;

	}

	public List<Livro> listAll() throws Exception {
		return livroDao.findAll();

	}

	public Livro findById(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("Preciso passa ID");
		}
		Livro l = livroDao.findById(id);
		return l;

	}

}

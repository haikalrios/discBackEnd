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

	public Livro addLivro(Livro livro) throws Exception {
		if (livro == null) {
			throw new Exception("Preciso passa o livro");
		}
		if (livro.getDescricao() == null) {
			throw new Exception("Preciso passar a decsrição");
		}

		if (livro.getIsbn() == null) {
			throw new Exception("Preciso passr o ISBN");
		}

		if (livro.getSubTitulo() == null) {
			throw new Exception("Preciso passar o subtitulo");
		}
		if (livro.getTitulo() == null) {
			throw new Exception("Preciso passar o titulo");
		}

		livroDao.persist(livro);

		return livro;

	}

	public Livro updateLivro(Livro livro) throws Exception {
		
		if (livro == null) {
			throw new Exception("Preciso passa o livro");
		}
		if (livro.getDescricao() == null) {
			throw new Exception("Preciso passar a decsrição");
		}
		if (livro.getId()== null) {
			throw new Exception("Preciso passar o id");
		}

		if (livro.getIsbn() == null) {
			throw new Exception("Preciso passr o ISBN");
		}

		if (livro.getSubTitulo() == null) {
			throw new Exception("Preciso passar o subtitulo");
		}
		if (livro.getTitulo() == null) {
			throw new Exception("Preciso passar o titulo");
		}

		Livro livroAtualizado = livroDao.findById(livro.getId());
		
		if (livroAtualizado.getId()== null) {
			throw new Exception("Livro não existe");
		}
		livroAtualizado.setDescricao(livro.getDescricao());
		livroAtualizado.setId(livro.getId());
		livroAtualizado.setIsbn(livro.getIsbn());
		livroAtualizado.setSubTitulo(livro.getSubTitulo());
		livroAtualizado.setTitulo(livro.getTitulo());
		
		
		livroDao.persist(livroAtualizado);

		return livro;

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

	public Livro delete(Integer id) throws Exception {
		Livro l = livroDao.findById(id);
		if (l == null) {
			throw new Exception("Livro não existe");
		}
		livroDao.remove(l);
		return l ;
	}

}

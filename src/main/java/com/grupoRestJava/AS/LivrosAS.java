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
			throw new Exception("Preciso passar a decsri��o");
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
		if (livro.getAutor() == null) {
			throw new Exception("Preciso passar o autor");
		}
		if (livro.getStatus() == null) {
			throw new Exception("Preciso passar o status");
		}

		livroDao.persist(livro);

		return livro;

	}

	public Livro updateLivro(Livro livro) throws Exception {

		if (livro == null) {
			throw new Exception("Preciso passa o livro");
		}
		if (livro.getDescricao() == null) {
			throw new Exception("Preciso passar a decsri��o");
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
		if (livro.getAutor() == null) {
			throw new Exception("Preciso passar o autor");
		}
		if (livro.getStatus() == null) {
			throw new Exception("Preciso passar o status");
		}

		Livro livroAtualizado = livroDao.findById(livro.getId());

		if (livroAtualizado.getId()== null) {
			throw new Exception("Livro n�o existe");
		}
		livroAtualizado.setDescricao(livro.getDescricao());
		livroAtualizado.setId(livro.getId());
		livroAtualizado.setIsbn(livro.getIsbn());
		livroAtualizado.setSubTitulo(livro.getSubTitulo());
		livroAtualizado.setTitulo(livro.getTitulo());
		livroAtualizado.setAutor(livro.getAutor());
		livroAtualizado.setStatus(livro.getStatus());

		livroDao.persist(livroAtualizado);

		return livro;

	}


	public List<Livro> listAll() throws Exception {
		return livroDao.findAll();

	}
	
	public List<Livro> listarPorTitulo(String titulo) throws Exception {
		if (titulo == null){
			throw new Exception("Preciso passar o titulo a ser pesquisado");
		}
		return livroDao.buscarLivrosPorTitulo(titulo);

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
			throw new Exception("Livro n�o existe");
		}
		livroDao.remove(l);
		return l ;
	}

}

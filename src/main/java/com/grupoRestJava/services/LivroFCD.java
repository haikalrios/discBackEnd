package com.grupoRestJava.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.grupoRestJava.AS.LivrosAS;
import com.grupoRestJava.domain.entity.Livro;


@Path("/livrofcd")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class LivroFCD {

	@EJB
	private LivrosAS livroAS;

	@GET
	@Path("/listar")
	public List<Livro> listarTodos() throws Exception{
		return livroAS.listAll();
	}

	@GET
	@Path("/{id}")
	public Livro listarLivro(@PathParam("id") Integer id) throws Exception{
		return livroAS.findById(id) ;
	}
	
	@GET
	@Path("/listar/titulo/{descricao}")
	public List<Livro> listarLivroPorTitulo(@PathParam("descricao") String titulo) throws Exception{
		return livroAS.listarPorTitulo(titulo);
	}

	@POST
	@Path("/")
	public Livro addLivro(Livro livro) throws Exception{
		return livroAS.addLivro(livro);
	}

	@PUT
	@Path("/")
	public Livro updateLivro( Livro l) throws Exception{

		return livroAS.updateLivro(l) ;
	}

	@DELETE
	@Path("/{id}")
	public Livro deleteLivro(@PathParam("id") Integer id) throws Exception{
		return livroAS.delete(id) ;
	}

}

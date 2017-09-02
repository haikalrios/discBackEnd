package com.grupoRestJava.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@POST
	@Path("/")
	public Livro addLivro(Livro livro) throws Exception{
		return livroAS.salvar(livro);
	}
	
	/*@GET
	@Path("/pessoa/{id}")
	public PessoaTO listarPessoa(@PathParam("id") Integer id) throws Exception{
		PessoaTO s = new  PessoaTO();
		s.setId(1L);
		s.setNome("Haikal");
		return s;
	}
	a
	@POST
	@Path("/addPessoa")
	public void addStatus(PessoaTO p){
		System.out.println(p);
	}
	
	*/
/*	
	
	
	@GET
	@Path("/{id}")
	public PessoaTO olaGet(@PathParam("id") Long id ){
		PessoaTO p = new PessoaTO();
		p.setId(id);
		p.setNome("Joao "+ String.valueOf(id) );
		return p;
	}
	
	@GET
	@Path("/ola/{id}")
	public String olaGetString(@PathParam("id") String id ){
		return "Haikal";
	}
	
	@POST
	@Path("/add")
	public PessoaTO olaPost(PessoaTO p){
		p.setId(10L);
		p.setNome("Joao 10 " );
		return p;
	}
	
	@PUT
	@Path("/{id}")
	public PessoaTO olaPut(@PathParam("id") Long id, PessoaTO p){
		if (p == null){
			p = new PessoaTO();
		}
		p.setId(id);
		p.setNome(p.getNome() + " Metodo com id");
		return p ;
	}
	
	@PUT
	public PessoaTO olaPut( PessoaTO p){
		p.setNome(p.getNome() + " Metodo sem id");
		return p ;
	}


	
	@DELETE
	@Path("/{id}")
	public PessoaTO olaDelete(@PathParam("id") String nome ){
		PessoaTO p = new PessoaTO();
		p.setId(10L);
		p.setNome("Joao 10 " );
		return p;
	}
	*/
	

}

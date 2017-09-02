package com.grupoRestJava.services;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/myServices")
public class MyServices {

	@GET
	@Path("/ola/{nome}")
	public String olaGet(@PathParam("nome") String nome ){
		return "Ola "+ nome;
	}
	
	@POST
	@Path("/ola/{nome}")
	public String olaPost(@PathParam("nome") String nome ){
		return "Ola "+ nome;
	}
	
	@PUT
	@Path("/ola/{nome}")
	public String olaPut(@PathParam("nome") String nome ){
		return "Ola "+ nome;
	}
	
	@DELETE
	@Path("/ola/{nome}")
	public String olaDelete(@PathParam("nome") String nome ){
		return "Ola "+ nome;
	}
}

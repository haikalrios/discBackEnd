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
	@Path("/{id}")
	public String olaGet(@PathParam("id") String id ){
		return "Ola GET "+ id;
	}
	
	@POST
	@Path("/add")
	public String olaPost(String nome ){
		return "Ola ADD "+ nome;
	}
	
	@PUT
	@Path("/{id}")
	public String olaPut(@PathParam("id") String nome ){
		return "Ola PUT"+ nome;
	}

	
	@DELETE
	@Path("/{id}")
	public String olaDelete(@PathParam("id") String nome ){
		return "Ola DELETE "+ nome;
	}
	
	

}

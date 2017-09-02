package com.grupoRestJava.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("rest/myServices")
public class MyServices {

	@GET()
	@Path("/ola/{nome}")
	public String ola(@PathParam("nome") String nome ){
		return "Ola "+ nome;
	}
}

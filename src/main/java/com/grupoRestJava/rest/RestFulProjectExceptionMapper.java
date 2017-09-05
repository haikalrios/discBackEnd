package com.grupoRestJava.rest;

import java.lang.reflect.Method;

import javax.ejb.EJBAccessException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestFulProjectExceptionMapper implements ExceptionMapper<Exception> {

	/**
	 * Metodo criado para extrair ionformaões de acesso negado. Caso o parametro
	 * de entrada seja nulo o retorno será nulo. Caso não haja a mensagem seja
	 * nula retornará em branco
	 * 
	 * @param ex
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String getMessageToUserFromEJBAccessException(final Exception ex) {
		if (ex == null) {
			return null;
		}

		StringBuffer formatedMessage = new StringBuffer();
		String message = "";

		if (ex.getMessage() != null) {
			message = ex.getMessage();
		} else if (ex.getLocalizedMessage() != null) {
			message = ex.getLocalizedMessage();
		}

		try {
			int indexDoisPontos = message.lastIndexOf(":");

			int indexStringNaoEpermitida = message.lastIndexOf("não é permitida");

			if (indexDoisPontos < 0 || indexStringNaoEpermitida < 0) {
				formatedMessage.append(message);
			} else {

				String[] roles = null;
				// Pacote da classe que foi gerada a excecao
				// LEVANDO EM CONSIDERACAO INTERFACE ESTA NO MESMO
				// PACOTE DO
				// BEAN
				String packageOfClass = getPackagePathFromStringMessage(message);

				if (packageOfClass == null) {
					formatedMessage.append(message);
				} else {
					// Class Name que foi gerada a excecao
					String className = message.substring(indexDoisPontos + 1, indexStringNaoEpermitida).trim();
					className = packageOfClass + "." + className;

					// Method onde foi gerada a excecao
					String methodName = getMethodNameFromStringMessage(message);

					Class classInstance = Class.forName(className);
					Method[] methods = classInstance.getMethods();
					for (Method method : methods) {
						if (method.getName().equals(methodName)) {
							if (method.isAnnotationPresent(javax.annotation.security.RolesAllowed.class)) {
								roles = method.getAnnotation(javax.annotation.security.RolesAllowed.class).value();
							}
						}
					}
					// Formating message
					formatedMessage.append("Operação não permitida para este operador. Evento necessário - ");
					if (roles == null) {
						formatedMessage.append("Serviço não definida a permissão" + " ");
					} else {
						for (String r : roles) {
							formatedMessage.append(r + " ");
						}
					}

				}
			}
		} catch (Exception cnfe) {
			ex.printStackTrace();
			formatedMessage.append(message);
		}

		return formatedMessage.toString();
	}

	/**
	 * Retorna o nome do metodo extraído da mensagem de erro retorna null quando
	 * não localizar
	 * 
	 * @param message
	 * @return
	 */
	private String getMethodNameFromStringMessage(final String message) {
		String metodoNomeRetorno = null;
		try {
			int indexListaParametros = message.indexOf("(");
			if (indexListaParametros >= 0) {
				String stringAteParametros = message.substring(0, indexListaParametros);
				int indexUltimoPonto = stringAteParametros.lastIndexOf(".") + 1;
				metodoNomeRetorno = stringAteParametros.substring(indexUltimoPonto, stringAteParametros.length());
			}
		} catch (Exception ex) {
			// Pois qualquer exceção não pode parar o comportamento da
			// aplicação, apénas printa a pilha para ser ajustado.
			ex.printStackTrace();
		}
		return metodoNomeRetorno;
	}

	/**
	 * Retirar o nome ddo pacote. Caso não encontre no padrão definido retorna
	 * null Padrão:
	 * 
	 * @param message
	 * @return retorna o nome da classe contido na mensagem
	 */
	private String getPackagePathFromStringMessage(final String message) {
		String classNameRetorno = null;

		try {
			// Valida cada caracter que será referencia para parse da string
			// caso
			// contrario retorna nuull
			int indexAtePrimeiroParenteses = message.indexOf("(");
			if (indexAtePrimeiroParenteses >= 0) {
				String stringAteParametros = message.substring(0, indexAtePrimeiroParenteses);
				int indexUltimoPackageBrAteParametros = stringAteParametros.lastIndexOf(" br.");
				if (indexUltimoPackageBrAteParametros >= 0) {
					String stringPacoteAteMetodo = message.substring(indexUltimoPackageBrAteParametros, indexAtePrimeiroParenteses);
					int indexAteUltimoPontoDaStringPacoteAteMetodo = stringPacoteAteMetodo.lastIndexOf(".");
					if (indexAteUltimoPontoDaStringPacoteAteMetodo >= 0) {
						String stringPacoteAteInterface = stringPacoteAteMetodo.substring(0, indexAteUltimoPontoDaStringPacoteAteMetodo);
						int indexStringAtePacote = stringPacoteAteInterface.lastIndexOf(".");
						if (indexStringAtePacote >= 0) {
							classNameRetorno = stringPacoteAteInterface.substring(0, indexStringAtePacote).trim();
						}
					}
				}
			}
		} catch (Exception ex) {
			// Pois qualquer exceção não pode parar o comportamento da
			// aplicação, apénas printa a pilha para ser ajustado.
			ex.printStackTrace();

		}
		return classNameRetorno;
	}

	@Override
	public Response toResponse(final Exception ex) {

		ExceptionTO exceptionTO = new ExceptionTO();

		StatusType statusType = Response.Status.INTERNAL_SERVER_ERROR;

		if (ex.getCause() != null)
			exceptionTO.setMensagem(ex.getCause().getLocalizedMessage());
		else
			exceptionTO.setMensagem(ex.getMessage());

		Exception exCause = null;

		if (ex instanceof EJBAccessException) {
			exCause = ex;
		} else if (ex.getCause() != null && ex.getCause() instanceof EJBAccessException) {
			exCause = (Exception) ex.getCause();
		}

		if (exCause != null) {
			String retorno = getMessageToUserFromEJBAccessException(exCause);
			if (retorno != null && retorno.trim() != "") {
				exceptionTO.setMensagem(retorno);
				exceptionTO.setNomeClassException("br.coop.cooperforte.comuns.exceptions.checked.CooperforteAcessoNegadoException");
				statusType = Response.Status.UNAUTHORIZED;
			} else {
				exceptionTO.setNomeClassException(ex.getClass().getCanonicalName());
			}
		} else {
			exceptionTO.setNomeClassException(ex.getClass().getCanonicalName());
		}

		/*
		 * TODO Porque colocaram exception? As userException eram pra vir das
		 * checkeds Cooperforte. A ideia é que exeção de usuario da como alerta,
		 * exceções de sistema dá como erro na inrterface. cooperforte Haikal
		 * Rios
		 */
		exceptionTO.setUserException(ex instanceof Exception);

		return Response.status(statusType).entity(exceptionTO).type(MediaType.APPLICATION_JSON).header("ERROR", Boolean.TRUE).build();
	}
}

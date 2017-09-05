package com.grupoRestJava.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_LIVRO")
public class Livro extends BaseEntidade<Integer> {

	private static final long serialVersionUID = 492472753851383923L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PK_Livro")
	private Integer           id;

	@Column(name = "Isbn", nullable = false)
	private String           isbn;

	@Column(name = "Descricao", length = 200, nullable = false)
	private String            descricao;

	@Column(name = "titulo",length = 60, nullable = false)
	private String           titulo;

	@Column(name = "subtitulo",length = 60, nullable = false)
	private String           subTitulo;

	@Column(name = "autor",length = 40, nullable = false)
	private String           autor;
	
	@Column(name = "status", nullable = false)
	private Boolean          status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}

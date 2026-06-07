package br.com.gs.projeto_agro.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="USUARIO")
public class Usuario {

	@Id
	@Column(name="ID_USUARIO")
	private Integer id;
	@Column(name="NOME")
	@NotEmpty(message="Nome é um campo obrigatório")
	private String nome;
	@Column(name="EMAIL")
	@Email(message="Email inválido")
	private String email;
	@Column(name="SENHA")
	@NotEmpty(message="Senha é um campo obrigatório")
	private String senha;
	@Column(name="TELEFONE")
	@NotEmpty(message="Telefone é um campo obrigatório")
	@Size(min= 10, max=11, message="o tamanho da String"+ "deve estar entre 10 e 11")
	private String telefone;
	public Usuario() {
	
	}

	public Usuario(Integer id, String nome, String email, String senha, String telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}

	public void transferirUsuario(Usuario usuario) {
		
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.telefone = usuario.getTelefone();
		
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
	
}

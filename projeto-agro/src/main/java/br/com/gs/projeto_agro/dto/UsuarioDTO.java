package br.com.gs.projeto_agro.dto;

import br.com.gs.projeto_agro.model.Usuario;

public class UsuarioDTO {

	  	private Integer id;
	    private String nome;
	    private String email;
	    private String telefone;
	    
	    public UsuarioDTO() {
		
		}

		public UsuarioDTO(Integer id, String nome, String email, String telefone) {
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.telefone = telefone;
		}
		
		public UsuarioDTO(Usuario usuario) {
			this.id = usuario.getId();
			this.nome = usuario.getNome();
			this.email = usuario.getEmail();
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

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
	
	    
	    
}

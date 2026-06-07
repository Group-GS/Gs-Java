package br.com.gs.projeto_agro.dto;

import br.com.gs.projeto_agro.model.Local;

public class LocalDTO {

	private Integer id;
    private String nome;
    private String cidade;
    private String estado;
    
	
    
    
    
    public LocalDTO() {
	
	}

	public LocalDTO(Integer id, String nome, String cidade, String estado, Integer biomaId) {
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
		
	}
	
	public LocalDTO(Local local) {
		this.id = local.getId();
		this.nome = local.getNome();
		this.cidade = local.getCidade();
		this.estado = local.getEstado();
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	
	
    
	
    
}

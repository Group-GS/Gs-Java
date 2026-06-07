package br.com.gs.projeto_agro.dto;

import br.com.gs.projeto_agro.model.Bioma;

public class BiomaDTO {

	private Integer id;
    private String nome;
    private String descricao;
	
    
    public BiomaDTO() {
	
	}
	
	public BiomaDTO(Integer id, String nome, String descricao) {
		
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public BiomaDTO(Bioma bioma) {
		this.id = bioma.getIdBioma();
		this.nome = bioma.getNome();
		this.descricao = bioma.getDescricao();
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}

package br.com.gs.projeto_agro.dto;

import br.com.gs.projeto_agro.model.Cultura;

public class CulturaDTO {

	
	private Integer id;
    private String nome;
    private Double tempMinIdeal;
    private Double tempMaxIdeal;
    private Double umidadeMinIdeal;
    private Double umidadeMaxIdeal;
    private Double pressaoMinIdeal;
    private Double pressaoMaxIdeal;
	
    public CulturaDTO() {
	
	}

	public CulturaDTO(Integer id, String nome, Double tempMinIdeal, Double tempMaxIdeal, Double umidadeMinIdeal,
			Double umidadeMaxIdeal, Double pressaoMinIdeal, Double pressaoMaxIdeal) {
		this.id = id;
		this.nome = nome;
		this.tempMinIdeal = tempMinIdeal;
		this.tempMaxIdeal = tempMaxIdeal;
		this.umidadeMinIdeal = umidadeMinIdeal;
		this.umidadeMaxIdeal = umidadeMaxIdeal;
		this.pressaoMinIdeal = pressaoMinIdeal;
		this.pressaoMaxIdeal = pressaoMaxIdeal;
	}
	
	public CulturaDTO(Cultura cultura) {
		this.id = cultura.getId();
		this.nome = cultura.getNome();
		this.tempMinIdeal = cultura.getTempMinIdeal();
		this.tempMaxIdeal = cultura.getTempMaxIdeal();
		this.umidadeMinIdeal = cultura.getUmidadeMinIdeal();
		this.umidadeMaxIdeal = cultura.getUmidadeMaxIdeal();
		this.pressaoMinIdeal= cultura.getPressaoMinIdeal();
		this.pressaoMaxIdeal = cultura.getPressaoMaxIdeal();
		
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

	public Double getTempMinIdeal() {
		return tempMinIdeal;
	}

	public void setTempMinIdeal(Double tempMinIdeal) {
		this.tempMinIdeal = tempMinIdeal;
	}

	public Double getTempMaxIdeal() {
		return tempMaxIdeal;
	}

	public void setTempMaxIdeal(Double tempMaxIdeal) {
		this.tempMaxIdeal = tempMaxIdeal;
	}

	public Double getUmidadeMinIdeal() {
		return umidadeMinIdeal;
	}

	public void setUmidadeMinIdeal(Double umidadeMinIdeal) {
		this.umidadeMinIdeal = umidadeMinIdeal;
	}

	public Double getUmidadeMaxIdeal() {
		return umidadeMaxIdeal;
	}

	public void setUmidadeMaxIdeal(Double umidadeMaxIdeal) {
		this.umidadeMaxIdeal = umidadeMaxIdeal;
	}

	public Double getPressaoMinIdeal() {
		return pressaoMinIdeal;
	}

	public void setPressaoMinIdeal(Double pressaoMinIdeal) {
		this.pressaoMinIdeal = pressaoMinIdeal;
	}

	public Double getPressaoMaxIdeal() {
		return pressaoMaxIdeal;
	}

	public void setPressaoMaxIdeal(Double pressaoMaxIdeal) {
		this.pressaoMaxIdeal = pressaoMaxIdeal;
	}
	
	
    

}

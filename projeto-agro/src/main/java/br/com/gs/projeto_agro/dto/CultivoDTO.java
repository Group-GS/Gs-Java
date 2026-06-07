package br.com.gs.projeto_agro.dto;

import java.time.LocalDate;

import br.com.gs.projeto_agro.model.Cultivo;

public class CultivoDTO {

	private Integer id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
 
	
    
    public CultivoDTO() {
		
	}
	
	
	public CultivoDTO(Integer id, LocalDate dataInicio, LocalDate dataFim, String status
			) {

		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.status = status;

	}
	
	public CultivoDTO(Cultivo cultivo) {
		this.id = cultivo.getId();
		this.dataInicio = cultivo.getDataInicio();
		this.dataFim = cultivo.getDataFim();
		this.status = cultivo.getStatus();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
    
	
}

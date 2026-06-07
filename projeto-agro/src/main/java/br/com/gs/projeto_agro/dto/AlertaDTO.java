package br.com.gs.projeto_agro.dto;

import java.time.LocalDate;

import br.com.gs.projeto_agro.model.Alerta;

public class AlertaDTO {

	private Integer id;
    private String tipo;
    private String descricao;
    private LocalDate dataAlerta;
    private String status;
    private Integer localId;
	
    public AlertaDTO() {
		
	}

	public AlertaDTO(Integer id, String tipo, String descricao, LocalDate dataAlerta, String status, Integer localId) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.dataAlerta = dataAlerta;
		this.status = status;
		this.localId = localId;
	}
	
	public AlertaDTO(Alerta alerta) {
		this.id = alerta.getId();
		this.tipo = alerta.getTipo();
		this.descricao = alerta.getDescricao();
		this.dataAlerta = alerta.getDataAlerta();
		this.status =alerta.getStatus();
		this.localId = alerta.getLocal().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataAlerta() {
		return dataAlerta;
	}

	public void setDataAlerta(LocalDate dataAlerta) {
		this.dataAlerta = dataAlerta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

    
    
    
}

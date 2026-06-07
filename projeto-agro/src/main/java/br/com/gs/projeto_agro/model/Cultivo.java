package br.com.gs.projeto_agro.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="CULTIVO")
public class Cultivo {

	@Id
	@Column(name="ID_CULTIVO")
	private Integer id;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="DATA_INICIO")
	private LocalDate dataInicio;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="DATA_FIM")
	private LocalDate dataFim;
	@NotEmpty(message="Esse campo é obrigatório")
	@Column(name="STATUS")
    private String status;
	@ManyToOne
	@JoinColumn(name = "ID_LOCAL")
	private Local local;
	@ManyToOne
	@JoinColumn(name = "ID_CULTURA")
	private Cultura cultura;

	
    public Cultivo() {
		
	}


	 Cultivo(Integer id, LocalDate dataInicio, LocalDate dataFim, String status) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.status = status;
	}


	public void transferirCultivo(Cultivo cultivo) {
		this.dataFim= cultivo.getDataFim();
		this.dataInicio= cultivo.getDataInicio();
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

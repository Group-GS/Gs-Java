package br.com.gs.projeto_agro.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="LEITURA")
public class Leitura  {

	@Id
	@Column(name="ID_LEITURA")
	private Integer id;
	@NotNull(message="Esta campo é obrigatório")
	@Column(name="VALOR")
	private Double valor;
	@NotNull(message="Esta campo é obrigatório")
	@Column(name = "DATA_HORA")
	private LocalDateTime dataHora;
	@ManyToOne
	@JoinColumn(name = "ID_SENSOR")
	private Sensor sensor;
	
	public Leitura() {
		
	}

	public Leitura(Integer id, Double valor, LocalDateTime dataHora, Sensor sensor) {
		super();
		this.id = id;
		this.valor = valor;
		this.dataHora = dataHora;
		this.sensor = sensor;
	}
	
	public void transferirLeitura(Leitura leitura) {
		this.valor = leitura.getValor();
		this.dataHora = leitura.getDataHora();
		this.sensor =leitura.getSensor();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	
	
	
	
}

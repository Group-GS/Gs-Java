package br.com.gs.projeto_agro.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.CascadeType;

@Entity
@Table(name="SENSOR")
public class Sensor {
	
	
	@Id
	@Column(name="ID_SENSOR")
	private Integer id;
	@NotEmpty(message="Esta campo é obrigatório")
	@Column(name = "TIPO_SENSOR")
    private String tipoSensor;
	@NotEmpty(message="Esta campo é obrigatório")
	@Column(name="STATUS")
    private String status;
    @ManyToOne
    @JoinColumn(name = "ID_LOCAL")
    private Local local;
	@OneToMany(
    mappedBy = "sensor",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
private List<Leitura> leituras;
	
    public Sensor() {
		
	}

	public Sensor(Integer id, String status, Local local) {
		this.id = id;
		this.status = status;
		this.local = local;
		
	}

	public Sensor(Integer id, String tipoSensor, String status) {
		this.id = id;
		this.tipoSensor = tipoSensor;
		this.status = status;
	}
	
	public void transferirSensor(Sensor sensor){
    	this.tipoSensor = sensor.getTipoSensor();
    	this.status = sensor.getStatus();
    	this.local = sensor.getLocal();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoSensor() {
		return tipoSensor;
	}

	public void setTipoSensor(String tipoSensor) {
		this.tipoSensor = tipoSensor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Local getLocal() {
    	return local;
	}

	public void setLocal(Local local) {
    	this.local = local;
	}
    
    
    
}

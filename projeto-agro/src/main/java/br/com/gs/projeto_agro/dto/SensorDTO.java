package br.com.gs.projeto_agro.dto;

import br.com.gs.projeto_agro.model.Sensor;

public class SensorDTO {

	private Integer id;
    private String tipoSensor;
    private String status;
	
    public SensorDTO() {
	
	}

	public SensorDTO(Integer id, String tipoSensor, String status) {
	
		this.id = id;
		this.tipoSensor = tipoSensor;
		this.status = status;
	}
	
	public SensorDTO(Sensor sensor) {
		this.id = sensor.getId();
		this.tipoSensor = sensor.getTipoSensor();
		this.status= sensor.getStatus();
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



    


    
}

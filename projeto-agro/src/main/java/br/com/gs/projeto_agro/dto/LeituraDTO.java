package br.com.gs.projeto_agro.dto;

import java.time.LocalDateTime;

import br.com.gs.projeto_agro.model.Leitura;

public class LeituraDTO {

	 	private Integer id;
	    private Double valor;
	    private LocalDateTime dataHora;
	    private Integer sensorId;
		
	    public LeituraDTO() {
		
		}
		
		
		public LeituraDTO(Integer id, Double valor, LocalDateTime dataHora, Integer sensorId) {
			
			this.id = id;
			this.valor = valor;
			this.dataHora = dataHora;
			this.sensorId = sensorId;
		}
		
		public LeituraDTO(Leitura leitura) {
			this.id = leitura.getId();
			this.valor = leitura.getValor();
			this.dataHora = leitura.getDataHora();
			this.sensorId = leitura.getSensor().getId();
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
		public Integer getSensorId() {
			return sensorId;
		}
		public void setSensorId(Integer sensorId) {
			this.sensorId = sensorId;
		}
	
	
	    
	
}

package br.com.gs.projeto_agro.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ALERTA")
@Schema(description = "Entidade responsável pelas aplicações dos Alertas")
public class Alerta {

	 	@Id
	    @Column(name = "ID_ALERTA")
	    private Integer id;
	 	@Column(name="TIPO")
	    private String tipo;
	 	@Column(name="DESCRICAO")
	    private String descricao;
	    @Column(name = "DATA_ALERTA")
	    private LocalDate dataAlerta;
	    @Column(name="STATUS")
	    private String status;
	    @ManyToOne
	    @JoinColumn(name = "ID_LOCAL")
	    private Local local;
		
	    public Alerta() {
			
		}

		public Alerta(Integer id, String tipo, String descricao, LocalDate dataAlerta, String status, Local local) {
			super();
			this.id = id;
			this.tipo = tipo;
			this.descricao = descricao;
			this.dataAlerta = dataAlerta;
			this.status = status;
			this.local = local;
		}

		public void transferirAlerta(Alerta alerta) {
			this.tipo = alerta.getTipo();
			this.descricao = alerta.getDescricao();
			this.dataAlerta = alerta.getDataAlerta();
			this.status = alerta.getStatus();
			this.local = alerta.getLocal();
			
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

		public Local getLocal() {
			return local;
		}

		public void setLocal(Local local) {
			this.local = local;
		}
	    
	    
	    

}

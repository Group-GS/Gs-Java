package br.com.gs.projeto_agro.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="CULTURA")
public class Cultura  {

	@Id
	@Column(name="ID_CULTURA")
	private Integer id;
	@Column(name="NOME")
	private String nome;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="TEMP_MIN_IDEAL")
	private Double tempMinIdeal;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="TEMP_MAX_IDEAL")
	private Double tempMaxIdeal;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="UMIDADE_MIN_IDEAL")
	private Double umidadeMinIdeal;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="UMIDADE_MAX_IDEAL")
	private Double umidadeMaxIdeal;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="PRESSAO_MIN_IDEAL")
    private Double pressaoMinIdeal;
	@NotNull(message="Esse campo é obrigatório")
	@Column(name="PRESSAO_MAX_IDEAL")
	private Double pressaoMaxIdeal;
    @OneToMany(mappedBy = "cultura")
    private List<Cultivo> cultivos;
	
    public Cultura() {
		
	}

	public Cultura(Integer id, String nome, Double tempMinIdeal, Double tempMaxIdeal, Double umidadeMinIdeal,
			Double umidadeMaxIdeal, Double pressaoMinIdeal, Double pressaoMaxIdeal) {
		super();
		this.id = id;
		this.nome = nome;
		this.tempMinIdeal = tempMinIdeal;
		this.tempMaxIdeal = tempMaxIdeal;
		this.umidadeMinIdeal = umidadeMinIdeal;
		this.umidadeMaxIdeal = umidadeMaxIdeal;
		this.pressaoMinIdeal = pressaoMinIdeal;
		this.pressaoMaxIdeal = pressaoMaxIdeal;
	}
	
	public void transferirCultura(Cultura cultura) {
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

	public List<Cultivo> getCultivos() {
		return cultivos;
	}

	public void setCultivos(List<Cultivo> cultivos) {
		this.cultivos = cultivos;
	}
	
    
    
	
}

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

@Entity
@Table(name="LOCAIS")
public class Local {

	@Id
	@Column(name="ID_LOCAL")
	private Integer id;
	@NotEmpty(message="Esta campo é obrigatório")
	@Column(name="NOME")
	private String nome;
	@NotEmpty(message="Esta campo é obrigatório")
	@Column(name="CIDADE")
	private String cidade;
	@NotEmpty(message="Esta campo é obrigatório")
	@Column(name="ESTADO")
    private String estado;
    @ManyToOne
    @JoinColumn(name = "ID_BIOMA")
    private Bioma bioma;
    @OneToMany(mappedBy = "local")
    private List<Sensor> sensores;
    @OneToMany(mappedBy = "local")
    private List<Cultivo> cultivos;
    @OneToMany(mappedBy = "local")
    private List<Alerta> alertas;
   
	
    public Local() {
	
	}

	public Local(Integer id, String nome, String cidade, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public void transferirLocal(Local local) {
		this.nome = local.getNome();
		this.cidade = local.getCidade();
		this.estado = local.getEstado();
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
    
    
}

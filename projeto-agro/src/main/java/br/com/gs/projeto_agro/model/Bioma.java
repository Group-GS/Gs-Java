package br.com.gs.projeto_agro.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "BIOMA")
public class Bioma {

    @Id
    @Column(name = "ID_BIOMA")
    private Integer idBioma;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;
    
    @OneToMany(mappedBy = "bioma")
    private List<Local> locais;

    public Bioma() {}

    public Bioma(Integer idBioma, String nome, String descricao) {
        this.idBioma = idBioma;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public void transferirBioma(Bioma bioma) {
    	this.nome = bioma.getNome();
    	this.descricao = bioma.getDescricao();
    }

    public Integer getIdBioma() {
        return idBioma;
    }

    public void setIdBioma(Integer idBioma) {
        this.idBioma = idBioma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
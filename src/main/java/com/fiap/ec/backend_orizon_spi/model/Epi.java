package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "epi")
public class Epi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_epi;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = true)
    private String descricao;

    public Epi() {
    }

    public Epi(String name, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId_epi() {
        return id_epi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

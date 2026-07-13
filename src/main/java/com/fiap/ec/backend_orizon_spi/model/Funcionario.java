package com.fiap.ec.backend_orizon_spi.model;
import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_funcionario;
    @Column(nullable = false)
    private String nome;
    private String matricula;
    private String setor;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(String nome, String matricula, String setor, String cargo) {
        this.nome = nome;
        this.matricula = matricula;
        this.setor = setor;
        this.cargo = cargo;
    }

    public Long getId_funcionario() {
        return id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}

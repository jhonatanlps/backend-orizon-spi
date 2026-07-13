package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ocorrencia;
    @Column(nullable = false)
    private LocalDateTime data_hora;
    private String status;
    @ManyToOne
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Ocorrencia() {
    }

    public Ocorrencia(LocalDateTime data_hora, String status, Maquina maquina, Funcionario funcionario) {
        this.data_hora = data_hora;
        this.status = status;
        this.maquina = maquina;
        this.funcionario = funcionario;
    }

    public Long getId_ocorrencia() {
        return id_ocorrencia;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}

package com.fiap.ec.backend_orizon_spi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alerta;
    @Column(nullable = false)
    private String mensagem;
    private String status;
    private LocalDateTime data_hora;
    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;

    public Alerta() {

    }

    public Alerta(String mensagem, String status, LocalDateTime data_hora, Ocorrencia ocorrencia) {
        this.mensagem = mensagem;
        this.status = status;
        this.data_hora = data_hora;
        this.ocorrencia = ocorrencia;
    }

    public Long getId_alerta() {
        return id_alerta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
}

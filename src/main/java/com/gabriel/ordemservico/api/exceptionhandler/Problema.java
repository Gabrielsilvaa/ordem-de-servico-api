package com.gabriel.ordemservico.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.type.StringNVarcharType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<Campo> campos;


    public static class Campo{
        private String nome;
        private String msg;

        public Campo(String nome, String msg) {
            this.nome = nome;
            this.msg = msg;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

}

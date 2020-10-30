package com.gabriel.ordemservico.domain.model;

import com.gabriel.ordemservico.api.model.Comentarios;
import com.gabriel.ordemservico.domain.exception.NegocioException;
import com.sun.istack.NotNull;
import jdk.dynalink.linker.LinkerServices;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private OffsetDateTime dataAbertura;

    private OffsetDateTime dataFinalizada;



    @OneToMany(mappedBy = "ordemServico")
    private List<Comentarios> comentarios = new ArrayList<>();


    public List<Comentarios> getComentarios() {
        return comentarios;
    }
    public void setComentarios(List<Comentarios> comentarios) {
        this.comentarios = comentarios;
    }
    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public OffsetDateTime getDataFinalizada() {
        return dataFinalizada;
    }
    public void setDataFinalizada(OffsetDateTime dataFinalizada) {
        this.dataFinalizada = dataFinalizada;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public StatusOrdemServico getStatus() {
        return status;
    }
    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrdemServico other = (OrdemServico) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public boolean podeSerFinalizado(){
        return StatusOrdemServico.ABERTA.equals(getStatus());
    }

    public boolean naoPodeSerFinalizado(){
        return podeSerFinalizado();
    }

    public void finalizar(){
        if (naoPodeSerFinalizado()){
            throw new NegocioException("Ordem de servico finalizada");
        }

        setStatus(StatusOrdemServico.FINALIZADA);
        setDataFinalizada(OffsetDateTime.now());
    }

}

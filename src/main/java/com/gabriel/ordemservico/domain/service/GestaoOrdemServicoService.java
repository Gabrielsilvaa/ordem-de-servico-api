package com.gabriel.ordemservico.domain.service;

import com.gabriel.ordemservico.api.model.Comentarios;
import com.gabriel.ordemservico.domain.exception.EntidadeNaoEncontradaException;
import com.gabriel.ordemservico.domain.exception.NegocioException;
import com.gabriel.ordemservico.domain.model.Cliente;
import com.gabriel.ordemservico.domain.model.OrdemServico;
import com.gabriel.ordemservico.domain.model.StatusOrdemServico;
import com.gabriel.ordemservico.domain.repository.ClienteRepository;
import com.gabriel.ordemservico.domain.repository.ComentariosRepository;
import com.gabriel.ordemservico.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentariosRepository comentariosRepository;

    public OrdemServico criar( OrdemServico ordemServico){

        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente nao encteado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }

    public void finalizar(Long ordemServicoId){
        OrdemServico ordemServico = buscar(ordemServicoId);
        ordemServico.finalizar();
        ordemServicoRepository.save(ordemServico);
    }

    public Comentarios addComentario(Long ordemServicoId, String descricao){

        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem nao encontrada"));

        Comentarios comentarios = new Comentarios();

        comentarios.setDataEnvio(OffsetDateTime.now());
        comentarios.setDescricao(descricao);
        comentarios.setOrdemServico(ordemServico);

        return comentariosRepository.save(comentarios);
    }

    private OrdemServico buscar (Long ordeServicoId){
        return ordemServicoRepository.findById(ordeServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("ordem de serviço nao encontrada"));
    }

}

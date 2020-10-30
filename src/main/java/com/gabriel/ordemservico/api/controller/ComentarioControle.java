package com.gabriel.ordemservico.api.controller;

import com.gabriel.ordemservico.api.model.ComentarioInput;
import com.gabriel.ordemservico.api.model.ComentarioModel;
import com.gabriel.ordemservico.api.model.Comentarios;
import com.gabriel.ordemservico.domain.exception.EntidadeNaoEncontradaException;
import com.gabriel.ordemservico.domain.model.Cliente;
import com.gabriel.ordemservico.domain.model.OrdemServico;
import com.gabriel.ordemservico.domain.repository.OrdemServicoRepository;
import com.gabriel.ordemservico.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoID}/comentarios")
public class ComentarioControle {

    @Autowired
    private  GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel add(@PathVariable Long ordemServicoID, @RequestBody @Valid ComentarioInput comentarioInput){

        Comentarios comentario = gestaoOrdemServicoService
                .addComentario(ordemServicoID, comentarioInput.getDescricao());
        return toModel(comentario);
    }

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoID){
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoID)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("nao tem comentario"));
        return toCollectionModel(ordemServico.getComentarios());

    }


    private ComentarioModel toModel(Comentarios comentarios){
        return modelMapper.map(comentarios, ComentarioModel.class);
    }
    private List<ComentarioModel> toCollectionModel(List<Comentarios> comentarios){

        return comentarios.stream()
                .map(comentarios1 -> toModel(comentarios1))
                .collect(Collectors.toList());
    }
}

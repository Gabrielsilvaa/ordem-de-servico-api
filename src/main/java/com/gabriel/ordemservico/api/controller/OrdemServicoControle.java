package com.gabriel.ordemservico.api.controller;

import com.gabriel.ordemservico.api.model.OrdemServicoInput;
import com.gabriel.ordemservico.api.model.OrdemServicoModel;
import com.gabriel.ordemservico.domain.model.OrdemServico;
import com.gabriel.ordemservico.domain.repository.OrdemServicoRepository;
import com.gabriel.ordemservico.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoControle {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput){
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        return toModel(gestaoOrdemServicoService.criar(ordemServico));
    }

    @GetMapping
    public List<OrdemServicoModel> listar(){
        return toCollectionModel(ordemServicoRepository.findAll());
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId){
        Optional<OrdemServico> ordemServico =  ordemServicoRepository.findById(ordemServicoId);

        if (ordemServico.isPresent()){
            OrdemServicoModel ordemServicoModelmodel = toModel(ordemServico.get());
            return ResponseEntity.ok(ordemServicoModelmodel);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{ordemServicoId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long ordemServicoId){

        gestaoOrdemServicoService.finalizar(ordemServicoId);

    }

    private OrdemServicoModel toModel(OrdemServico ordemServico){
        return modelMapper.map(ordemServico, OrdemServicoModel.class );
    }

    private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordemServico){
        return ordemServico.stream()
                .map(ordemServico1 -> toModel(ordemServico1))
                .collect(Collectors.toList());
    }

    private OrdemServico toEntity(OrdemServicoInput ordemServicoInput){
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }

}

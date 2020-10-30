package com.gabriel.ordemservico.api.controller;

import com.gabriel.ordemservico.domain.model.Cliente;
import com.gabriel.ordemservico.domain.repository.ClienteRepository;
import com.gabriel.ordemservico.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    //metodo para ligar ao repositorio
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    //metodo para retornar dados
    @GetMapping
    public List<Cliente> lista(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){

        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        //if para retornar se tem alguma coisa no cliente
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        //caso esteja vazio retorna erro 404 no http
        return ResponseEntity.notFound().build();
    }

    //metodo para adicionar dados
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
       return cadastroClienteService.salvar(cliente);
    }

    //Metodo para alterar um cliente
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,
                                             @RequestBody Cliente cliente){
        //se o id for vazio ele retorna esse erro
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    //esse metodo usamos pra apagar um cliente
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        //se o id for vazio ele retorna esse erro
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cadastroClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }


}

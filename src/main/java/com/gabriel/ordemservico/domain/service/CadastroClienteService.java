package com.gabriel.ordemservico.domain.service;

import com.gabriel.ordemservico.domain.exception.NegocioException;
import com.gabriel.ordemservico.domain.model.Cliente;
import com.gabriel.ordemservico.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistente != null && !clienteExistente.equals(cliente)){
            throw  new NegocioException("ja tem gente com esse email");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }

}

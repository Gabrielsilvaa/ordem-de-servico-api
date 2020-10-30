package com.gabriel.ordemservico.domain.repository;

import com.gabriel.ordemservico.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Interface pra criação de repositorio, nela fazemos as interfaces para consulta
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //para criar um metodo a onde busca somente um nome
    List<Cliente> findByNome(String nome);

    //Para criar uma select com like
    List<Cliente> findByNomeContaining(String nome);

    Cliente findByEmail(String email);

}

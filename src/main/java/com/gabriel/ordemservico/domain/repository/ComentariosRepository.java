package com.gabriel.ordemservico.domain.repository;

import com.gabriel.ordemservico.api.model.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
}

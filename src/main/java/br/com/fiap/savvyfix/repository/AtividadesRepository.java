package br.com.fiap.savvyfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.savvyfix.model.Atividades;

import java.util.List;

@Repository
public interface AtividadesRepository extends JpaRepository<Atividades, Long> {
    List<Atividades> findByClienteId(Long clienteId);
    Atividades findFirstByClienteIdOrderByIdDesc(Long clierteId);
}

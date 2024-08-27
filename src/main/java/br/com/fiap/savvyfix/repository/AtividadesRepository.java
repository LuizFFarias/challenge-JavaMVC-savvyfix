package br.com.fiap.savvyfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.savvyfix.model.Atividades;

@Repository
public interface AtividadesRepository extends JpaRepository<Atividades, Long> {
    Atividades findByClienteId(Long clienteId);
}

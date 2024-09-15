package br.com.fiap.savvyfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.savvyfix.model.Compra;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
    List<Compra> findByClienteId(Long clienteId);
}

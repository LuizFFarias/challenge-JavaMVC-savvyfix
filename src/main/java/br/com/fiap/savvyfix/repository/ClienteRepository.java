package br.com.fiap.savvyfix.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.savvyfix.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
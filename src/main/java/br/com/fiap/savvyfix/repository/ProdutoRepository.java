package br.com.fiap.savvyfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.savvyfix.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
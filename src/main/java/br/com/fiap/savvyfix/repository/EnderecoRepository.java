package br.com.fiap.savvyfix.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.savvyfix.model.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
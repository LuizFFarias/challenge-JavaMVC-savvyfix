package br.com.fiap.savvyfix.service;


import br.com.fiap.savvyfix.model.Atividades;
import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.repository.AtividadesRepository;
import jakarta.ws.rs.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AtividadesService implements  ServiceDTO<Atividades>{
    @Autowired
    private AtividadesRepository repo;

    @Autowired
    private ClienteService clienteService;


    @Override
    public Collection<Atividades> findAll() {
        return repo.findAll();
    }

    @Override
    public Atividades save(Atividades atividades) {
        return repo.save( atividades );
    }

    public Atividades findById(Long id) {return repo.findById(id).orElse(null);}

    public Atividades findByClienteId(Long clienteId) {return repo.findByClienteId(clienteId);}


}

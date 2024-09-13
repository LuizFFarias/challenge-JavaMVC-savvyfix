package br.com.fiap.savvyfix.service;


import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.model.Endereco;
import br.com.fiap.savvyfix.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClienteService implements  ServiceDTO<Cliente>{

    @Autowired
    private ClienteRepository repo;

    @Override
    public Collection<Cliente> findAll() {
        return repo.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repo.save( cliente );
    }

    public Cliente findById(Long id) {
        return  repo.findById(id).orElse(null);
    }

    public Cliente findByCpf(String cpf) {return repo.findByCpf(cpf);}

    public void deleteById(Long id){repo.deleteById(id);}
}
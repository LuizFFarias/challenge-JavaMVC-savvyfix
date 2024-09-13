package br.com.fiap.savvyfix.service;


import br.com.fiap.savvyfix.model.Compra;
import br.com.fiap.savvyfix.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CompraService implements ServiceDTO<Compra> {

    @Autowired
    private CompraRepository repo;

    @Override
    public Collection<Compra> findAll() {
        return repo.findAll();
    }

    @Override
    public Compra save(Compra compra) {
        return repo.save(compra);
    }

    public Compra findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteById(Long id){repo.deleteById(id);}

    public List<Compra> findByClienteId(Long clienteId) {
        return repo.findByClienteId(clienteId);
    }

}

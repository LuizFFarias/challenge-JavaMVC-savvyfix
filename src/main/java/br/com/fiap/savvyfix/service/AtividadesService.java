package br.com.fiap.savvyfix.service;


import br.com.fiap.savvyfix.model.Atividades;
import br.com.fiap.savvyfix.repository.AtividadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AtividadesService implements  ServiceDTO<Atividades>{
    @Autowired
    private AtividadesRepository repo;

    @Override
    public Collection<Atividades> findAll() {
        return repo.findAll();
    }

    @Override
    public Atividades save(Atividades atividades) {
        return repo.save( atividades );
    }

    public Atividades findById(Long id) {return repo.findById(id).orElse(null);}

    public List<Atividades> findByClienteId(Long clienteId) {return repo.findByClienteId(clienteId);}

    public List<Atividades> findByProdutoId(Long produtoId) {return repo.findByProdutoId(produtoId);}
    public void deleteById(Long id){repo.deleteById(id);}


}

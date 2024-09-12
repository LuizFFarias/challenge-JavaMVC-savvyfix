package br.com.fiap.savvyfix.service;



import br.com.fiap.savvyfix.model.Endereco;
import br.com.fiap.savvyfix.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnderecoService implements ServiceDTO<Endereco>{

    @Autowired
    private EnderecoRepository repo;

    @Override
    public Collection<Endereco> findAll() {
        return repo.findAll();
    }

    public Endereco findById(Long id){return repo.findById(id).orElse(null);}

    @Override
    public Endereco save(Endereco endereco) {
        return repo.save( endereco );
    }


}

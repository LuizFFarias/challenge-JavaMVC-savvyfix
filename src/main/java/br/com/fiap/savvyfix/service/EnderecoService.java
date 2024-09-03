package br.com.fiap.savvyfix.service;


import br.com.fiap.savvyfix.dto.request.EnderecoRequest;
import br.com.fiap.savvyfix.dto.response.EnderecoResponse;
import br.com.fiap.savvyfix.model.Endereco;
import br.com.fiap.savvyfix.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnderecoService implements ServiceDTO<Endereco, EnderecoRequest, EnderecoResponse>{

    @Autowired
    private EnderecoRepository repo;

    @Override
    public Endereco toEntity( EnderecoRequest enderecoRequest) {

        return Endereco.builder()
                .cep( enderecoRequest.cep() )
                .rua( enderecoRequest.rua() )
                .numero( enderecoRequest.numero() )
                .bairro( enderecoRequest.bairro())
                .cidade( enderecoRequest.cidade())
                .estado( enderecoRequest.estado())
                .pais( enderecoRequest.pais())
                .build();
    }

    @Override
    public EnderecoResponse toResponse(Endereco endereco) {
        return EnderecoResponse.builder()
                .id(endereco.getId())
                .cep( endereco.getCep() )
                .rua( endereco.getRua() )
                .numero( endereco.getNumero() )
                .bairro( endereco.getBairro())
                .cidade( endereco.getCidade())
                .estado( endereco.getEstado())
                .pais( endereco.getPais())
                .build();
    }

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

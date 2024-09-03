package br.com.fiap.savvyfix.service;

import br.com.fiap.savvyfix.dto.request.ClienteRequest;
import br.com.fiap.savvyfix.dto.response.ClienteResponse;
import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.model.Endereco;
import br.com.fiap.savvyfix.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClienteService implements  ServiceDTO<Cliente, ClienteRequest, ClienteResponse>{

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoService enderecoService;
    @Override
    public Cliente toEntity(ClienteRequest clienteRequest) {

        Endereco endereco = enderecoService.findById(clienteRequest.endereco().id());

        return Cliente.builder()
                .nome( clienteRequest.nome() )
                .cpf( clienteRequest.cpf() )
                .senha( clienteRequest.senha() )
                .endereco( endereco )
                .build();
    }

    @Override
    public ClienteResponse toResponse(Cliente cliente) {

        var endereco = enderecoService.toResponse(cliente.getEndereco());

        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome( cliente.getNome() )
                .cpf( cliente.getCpf() )
                .endereco(endereco)
                .build();
    }

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
}
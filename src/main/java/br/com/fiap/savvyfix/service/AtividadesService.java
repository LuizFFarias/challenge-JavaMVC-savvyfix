package br.com.fiap.savvyfix.service;

import br.com.fiap.savvyfix.dto.request.AtividadesRequest;
import br.com.fiap.savvyfix.dto.response.AtividadesResponse;
import br.com.fiap.savvyfix.model.Atividades;
import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.repository.AtividadesRepository;
import jakarta.ws.rs.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AtividadesService implements  ServiceDTO<Atividades, AtividadesRequest, AtividadesResponse>{
    @Autowired
    private AtividadesRepository repo;

    @Autowired
    private ClienteService clienteService;


    @Override
    public Atividades toEntity(AtividadesRequest atividadesRequest) {

        Cliente cliente = clienteService.findById( atividadesRequest.cliente().id() );


        return Atividades.builder()
                .precoVariado(atividadesRequest.precoVariado() )
                .horarioAtual( atividadesRequest.horarioAtual() )
                .localizacaoAtual( atividadesRequest.localizacaoAtual() )
                .climaAtual( atividadesRequest.climaAtual())
                .qntdProcura( atividadesRequest.qntdProcura())
                .demanda( atividadesRequest.demanda())
                .cliente( cliente )
                .build();
    }

    @Override
    public AtividadesResponse toResponse(Atividades atividades) {
        var cliente = clienteService.toResponse(atividades.getCliente());

        return AtividadesResponse.builder()
                .id(atividades.getId())
                .precoVariado( atividades.getPrecoVariado() )
                .horarioAtual( atividades.getHorarioAtual() )
                .localizacaoAtual( atividades.getLocalizacaoAtual() )
                .climaAtual( atividades.getClimaAtual())
                .qntdProcura( atividades.getQntdProcura())
                .demanda( atividades.getDemanda())
                .cliente( cliente )
                .build();
    }

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

package br.com.fiap.savvyfix.service;

import java.util.Collection;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.savvyfix.dto.request.ProdutoRequest;
import br.com.fiap.savvyfix.dto.response.ProdutoResponse;
import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService implements ServiceDTO<Produto, ProdutoRequest, ProdutoResponse>{

	 @Autowired
	    private ProdutoRepository repo;

	    @Override
	    public Produto toEntity(ProdutoRequest produtoRequest) {

	        return Produto.builder()
	                .nome( produtoRequest.nome() )
	                .descricao( produtoRequest.descricao() )
	                .marca( produtoRequest.marca() )
	                .precoFixo( produtoRequest.precoFixo())
					.imgProduto(produtoRequest.imgProduto())
	                .build();
	    }

	    @Override
	    public ProdutoResponse toResponse(Produto produto) {
	        return ProdutoResponse.builder()
	                .id(produto.getId())
	                .nome( produto.getNome() )
	                .descricao( produto.getDescricao() )
	                .marca( produto.getMarca() )
	                .precoFixo( produto.getPrecoFixo())
	                .build();
	    }

		@Override
		public Collection<Produto> findAll() {
			return repo.findAll();
		}

		@Override
		public Produto findById(Long id) {return repo.findById(id).orElse(null);}

		@Override
		public Produto save(Produto produto) {return repo.save(produto);}

		public void deleteById(Long id) {repo.deleteById(id);}

}

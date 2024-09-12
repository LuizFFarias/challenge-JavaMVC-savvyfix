package br.com.fiap.savvyfix.service;

import java.util.Collection;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService implements ServiceDTO<Produto>{

	 @Autowired
	    private ProdutoRepository repo;

	 public Produto produtoBuilder(Produto produto){
		 return  Produto.builder()
				 .id(produto.getId())
				.nome(produto.getNome())
				.marca(produto.getMarca())
				.descricao(produto.getDescricao())
				.precoFixo(produto.getPrecoFixo())
				.imgProduto(produto.getImgProduto())
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

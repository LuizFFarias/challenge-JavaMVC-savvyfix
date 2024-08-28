package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;

import br.com.fiap.savvyfix.repository.ProdutoRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping(value = "/produtos", produces = "application/json")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping()
	private ModelAndView findAll() {
		Collection<Produto> produtos = service.findAll();
		ModelAndView mv = new ModelAndView("produtos");
		mv.addObject("produtos", produtos);
        return mv;
    }

	@GetMapping("/{id}")
	private ModelAndView findById(@PathVariable Long id) {
		Produto produto = service.findById(id);
		if (produto == null) {
			return new ModelAndView("redirect:/produtos");
		} else {
			ModelAndView mv = new ModelAndView("produto");
			mv.addObject("produto", produto);
			return mv;
		}
	}
	@GetMapping("/adiciona_produto")
	private ModelAndView save(){
		ModelAndView mv = new ModelAndView("adiciona_produto");
		mv.addObject("produto", new Produto());
		return mv;
	}



	@PostMapping("/insere_produto")
	private ModelAndView save(@Valid Produto produto, BindingResult bd) {
		if (bd.hasErrors()){
			ModelAndView mv = new ModelAndView("adiciona_produto");
			return mv;

		} else {
			Produto prod = new Produto();
			prod.setNome(produto.getNome());
			prod.setDescricao(produto.getDescricao());
			prod.setMarca(produto.getMarca());
			prod.setPrecoFixo(produto.getPrecoFixo());

			service.save(prod);

			return new ModelAndView("redirect:/produtos");

		}
	}


}
package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.fiap.savvyfix.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Optional;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping("/produtos")
	private ModelAndView findAllProdutos() {
		Collection<Produto> produtos = service.findAll();
		ModelAndView mv = new ModelAndView("produtos");
		mv.addObject("produtos", produtos);
        return mv;
    }

	@GetMapping("produtos/{id}")
	private ModelAndView findProdutoById(@PathVariable Long id) {
		Produto produto = service.findById(id);
		if (produto == null) {
			return new ModelAndView("redirect:/produtos");
		} else {
			ModelAndView mv = new ModelAndView("produto");
			mv.addObject("produto", produto);
			return mv;
		}
	}


}

package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.model.Atividades;
import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.service.AtividadesService;
import br.com.fiap.savvyfix.service.ClienteService;
import br.com.fiap.savvyfix.service.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/produtos", produces = "application/json")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@Autowired
	private AtividadesService serviceAtv;

	@Autowired
	private ClienteService serviceCliente;

	@GetMapping()
	private ModelAndView findAll(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String cpf = auth.getName();

		Cliente clienteLogado = serviceCliente.findByCpf(cpf);

		// Busca os produtos e atividades associados ao cliente logado
		Collection<Produto> produtos = service.findAll();
		List<Atividades> atividades = serviceAtv.findByClienteId(clienteLogado.getId());

		// Prepara o ModelAndView com os produtos e atividades
		ModelAndView mv = new ModelAndView("produtos");
		mv.addObject("produtos", produtos);
		mv.addObject("atividades", atividades);
		mv.addObject("cliente", clienteLogado);

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
	private ModelAndView save(@Valid Produto prod, BindingResult bd) {
		if (bd.hasErrors()){
			ModelAndView mv = new ModelAndView("adiciona_produto");
			return mv;

		} else {
			var produto = service.produtoBuilder(prod);
			service.save(produto);
			return new ModelAndView("redirect:/produtos");

		}
	}

	@GetMapping("/editar_produto/{id}")
	public ModelAndView returnEditar(@PathVariable Long id) {

		Produto produto = service.findById(id);

		if (produto == null) {
			return new ModelAndView("redirect:/produtos");
		} else {
			ModelAndView mv = new ModelAndView("edita_produto");
			mv.addObject("produto", produto);
			return mv;
		}
	}

	@PostMapping("/edita_produto/{id}")
	public ModelAndView editarProd(@PathVariable Long id, @Valid Produto produto, BindingResult bd) {

		if (bd.hasErrors()){
			ModelAndView mv = new ModelAndView("edita_produto");
			mv.addObject("produto", produto);
			return mv;
		} else {

			Produto prod = service.findById(id);
			if (prod == null) {
				ModelAndView mv = new ModelAndView("edita_produto");
				mv.addObject("produto", produto);
				return mv;
			} else {
				prod.setNome(produto.getNome());
				prod.setDescricao(produto.getDescricao());
				prod.setMarca(produto.getMarca());
				prod.setPrecoFixo(produto.getPrecoFixo());
				prod.setImgProduto(produto.getImgProduto());
				service.save(prod);

				return new ModelAndView("redirect:/produtos");
			}
		}

	}

	@GetMapping("/deletar_produto/{id}")
	public ModelAndView deletarProduto(@PathVariable Long id) {
		Produto produto = service.findById(id);
		if (produto == null) {
			return new ModelAndView("redirect:/produtos");
		} else {
			service.deleteById(id);
			return new ModelAndView("redirect:/produtos");
		}
	}


}

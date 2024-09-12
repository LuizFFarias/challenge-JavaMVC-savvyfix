package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.model.Endereco;
import br.com.fiap.savvyfix.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping(value = "/endereco", produces = "application/json")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping()
    private ModelAndView findAll() {
        Collection<Endereco> enderecos = service.findAll();
        ModelAndView mv = new ModelAndView("enderecos");
        mv.addObject("enderecos", enderecos);
        return mv;
    }
}

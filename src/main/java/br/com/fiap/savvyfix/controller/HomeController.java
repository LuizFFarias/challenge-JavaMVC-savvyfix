package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.repository.ClienteRepository;
import br.com.fiap.savvyfix.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ClienteService serviceClie;

    @GetMapping("/")
    public ModelAndView home() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String cpf = auth.getName();

        Cliente clienteLogado = serviceClie.findByCpf(cpf);
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("cliente", clienteLogado);
        return mv;
    }
}

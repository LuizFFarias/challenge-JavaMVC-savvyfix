package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.model.Endereco;
import br.com.fiap.savvyfix.service.ClienteService;
import br.com.fiap.savvyfix.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/clientes", produces = "application/json")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private EnderecoService serviceEndereco;

    @GetMapping("/cadastro_cliente")
    private ModelAndView save(){
        ModelAndView mv = new ModelAndView("cadastro_cliente");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/insere_cliente")
    private ModelAndView save(@Valid Cliente cliente, BindingResult bd){
        if(bd.hasErrors()){
            ModelAndView mv = new ModelAndView("cadastro_cliente");
            return mv;
        } else{
            Cliente clie = new Cliente();
            Endereco endereco = new Endereco();

            endereco.setRua( cliente.getEndereco().getRua());
            endereco.setNumero( cliente.getEndereco().getNumero());
            endereco.setCep( cliente.getEndereco().getCep());
            endereco.setBairro( cliente.getEndereco().getBairro());
            endereco.setCidade( cliente.getEndereco().getCidade());
            endereco.setEstado( cliente.getEndereco().getEstado());
            endereco.setPais( cliente.getEndereco().getPais());

            serviceEndereco.save(endereco);

            clie.setNome(cliente.getNome());
            clie.setCpf(cliente.getCpf());
            clie.setSenha(cliente.getSenha());
            clie.setEndereco(endereco);

            service.save(clie);

            return new ModelAndView("redirect:/");
        }
    }

    @GetMapping("/login_cliente")
    private ModelAndView login(){
        ModelAndView mv = new ModelAndView("login_cliente");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/logar_cliente")
    private ModelAndView logar(@Valid Cliente cliente, BindingResult bd){
        if(bd.hasErrors()){
            ModelAndView mv = new ModelAndView("login_cliente");
            return mv;
        } else {
            Cliente clieLogin = service.findByCpf(cliente.getCpf());

            if(clieLogin == null || !clieLogin.getSenha().equals(cliente.getSenha())){
                ModelAndView mv = new ModelAndView("login_cliente");
                mv.addObject("erro", "CPF ou senha inválidos");
                return mv;
            } else {
                return new ModelAndView("redirect:/");
            }
        }
    }






}

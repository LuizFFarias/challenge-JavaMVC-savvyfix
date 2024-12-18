package br.com.fiap.savvyfix.controller;


import br.com.fiap.savvyfix.model.*;
import br.com.fiap.savvyfix.repository.RoleRepository;
import br.com.fiap.savvyfix.service.AtividadesService;
import br.com.fiap.savvyfix.service.ClienteService;
import br.com.fiap.savvyfix.service.CompraService;
import br.com.fiap.savvyfix.service.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping(value = "/clientes", produces = "application/json")
@SessionAttributes("clienteLogado")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private EnderecoService serviceEnd;

    @Autowired
    private AtividadesService serviceAtv;

    @Autowired
    private CompraService serviceCpr;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastro_cliente")
    private ModelAndView save(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String cpf = auth.getName();

        if(cpf != "anonymousUser"){
            Cliente clienteLogado = service.findByCpf(cpf);
            boolean isAdmin = clienteLogado.getRoles().stream()
                    .anyMatch(role -> role.getNome().equals("ROLE_ADMIN"));
            ModelAndView mv = new ModelAndView("cadastro_cliente");
            mv.addObject("cliente", new Cliente());
            mv.addObject("isadmin", isAdmin );
            mv.addObject("roles", roleRepo.findAll());
            return mv;

        }
        ModelAndView mv = new ModelAndView("cadastro_cliente");
        mv.addObject("cliente", new Cliente());
        mv.addObject("isadmin", false);
        return mv;
    }

    @PostMapping("/insere_cliente")
    private ModelAndView save(@Valid Cliente cliente, BindingResult bd, Long id_role){
        if(bd.hasErrors()){
            ModelAndView mv = new ModelAndView("cadastro_cliente");
            return mv;
        }

        Cliente clienteExistente = service.findByCpf(cliente.getCpf());
        if(clienteExistente != null){
            ModelAndView mv = new ModelAndView("cadastro_cliente");
            mv.addObject("cliente", cliente);
            mv.addObject("erro", "CPF já cadastrado");
            return mv;
        }

        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));


        Endereco endereco = cliente.getEndereco();
        if(endereco == null){
            ModelAndView mv = new ModelAndView("cadastro_cliente");
            mv.addObject("cliente", cliente);
            return mv;
        }

        serviceEnd.save(endereco);
        cliente.setEndereco(endereco);
        service.save(cliente);

            return new ModelAndView("redirect:/clientes/login_cliente");

    }

    @GetMapping("/login_cliente")
    public String login(){

        return "login_cliente";
    }

    @GetMapping("/editar_cliente/{id}")
    public ModelAndView returnEditar(@PathVariable Long id) {
        Cliente cliente = service.findById(id);

        if (cliente == null) {
            return new ModelAndView("redirect:/compras/historico_compras/" + cliente.getId());
        } else {
            ModelAndView mv = new ModelAndView("edita_cliente");
            mv.addObject("cliente", cliente);
            return mv;
        }
    }

    @PostMapping("/edita_conta/{id}")
    public ModelAndView editarConta(@PathVariable Long id, @Valid Cliente cliente, BindingResult bd) {

        if (bd.hasErrors()){
            ModelAndView mv = new ModelAndView("edita_cliente");
            mv.addObject("cliente", cliente);
            return mv;
        } else {

            Cliente clie = service.findById(id);
            if (clie == null) {
                ModelAndView mv = new ModelAndView("edita_cliente");
                mv.addObject("cliente", cliente);
                return mv;
            } else {
                clie.setCpf(cliente.getCpf());
                clie.setNome(cliente.getNome());
                clie.setSenha(cliente.getSenha());
                clie.getEndereco().setCep(cliente.getEndereco().getCep());
                clie.getEndereco().setRua(cliente.getEndereco().getRua());
                clie.getEndereco().setNumero(cliente.getEndereco().getNumero());
                clie.getEndereco().setBairro(cliente.getEndereco().getBairro());
                clie.getEndereco().setCidade(cliente.getEndereco().getCidade());
                clie.getEndereco().setEstado(cliente.getEndereco().getEstado());
                clie.getEndereco().setPais(cliente.getEndereco().getPais());

                service.save(clie);

                return new ModelAndView("redirect:/compras/historico_compras/" + clie.getId());
            }
        }

    }

    @GetMapping("/deletar_conta/{id}")
    public ModelAndView deletarConta(@PathVariable Long id) {
        Cliente cliente = service.findById(id);
        if (cliente == null) {
            return new ModelAndView("redirect:/compras/historico_compras/" + cliente.getId());
        }

        List<Compra> compras = serviceCpr.findByClienteId(cliente.getId());
        if (compras != null && !compras.isEmpty()) {
            for (Compra compra : compras) {
                serviceCpr.deleteById(compra.getId());
            }
        }

        List<Atividades> atividades = serviceAtv.findByClienteId(cliente.getId());
        if (atividades != null && !atividades.isEmpty()) {
            for (Atividades atv : atividades) {
                serviceAtv.deleteById(atv.getId());
            }
        }

        service.deleteById(id);

        Endereco endereco = serviceEnd.findById(cliente.getEndereco().getId());
        if (endereco != null){
            serviceEnd.deleteById(id);
        }
        return new ModelAndView("redirect:/");

    }

    @GetMapping("/acesso_negado")
    public String retornaAcessoNegado(){
        return("acesso_negado");
    }

}


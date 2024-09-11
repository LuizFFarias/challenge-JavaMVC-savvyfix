package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.dto.request.CompraRequest;
import br.com.fiap.savvyfix.model.Compra;
import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.service.AtividadesService;
import br.com.fiap.savvyfix.service.CompraService;
import br.com.fiap.savvyfix.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/compras", produces = "application/json")
public class CompraController {

    @Autowired
    private CompraService service;

    @Autowired
    private ProdutoService serviceProd;

    @Autowired
    private AtividadesService serviceAtv;

    @GetMapping("/confirmar_compra")
    private ModelAndView save(){
        ModelAndView mv = new ModelAndView("compra");
        mv.addObject("compra", new Compra());
        return mv;
    }

    @PostMapping("/finalizar_compra")
    private ModelAndView save(@Valid CompraRequest compraRequest, BindingResult bd){
        var compra = service.toEntity(compraRequest);
        if (bd.hasErrors()){
            bd.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            ModelAndView mv = new ModelAndView("compra");
            mv.addObject("compra", compra);
            return mv;
        }
        else{
            var cliente = compra.getCliente();
            var produto = compra.getProduto();

            var atividades = serviceAtv.findByClienteId(cliente.getId());
            if (atividades != null){
                Float valorFinal = atividades.getPrecoVariado() * compra.getQntdProd();
                compra.setValorCompra(valorFinal);
                service.save(compra);
                return new ModelAndView("redirect:/");
            }
            service.save(compra);
            return new ModelAndView("redirect:/");

        }
    }



}

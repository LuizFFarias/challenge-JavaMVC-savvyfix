package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.model.Atividades;
import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.model.Compra;
import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.service.AtividadesService;
import br.com.fiap.savvyfix.service.ClienteService;
import br.com.fiap.savvyfix.service.CompraService;
import br.com.fiap.savvyfix.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping(value = "/compras", produces = "application/json")
@SessionAttributes("clienteLogado")
public class CompraController {

    @Autowired
    private CompraService service;

    @Autowired
    private ProdutoService serviceProd;

    @Autowired
    private AtividadesService serviceAtv;

    @Autowired
    private ClienteService serviceClie;

    @GetMapping("/confirmar_compra/{id}")
    private ModelAndView save(@PathVariable Long id, @ModelAttribute("clienteLogado") Cliente clienteLogado){
        Produto produto = serviceProd.findById(id);
        if (produto == null) {
            return new ModelAndView("redirect:/produtos");
        }
        ModelAndView mv = new ModelAndView("compra");
        mv.addObject("produto", produto);
        mv.addObject("compra", new Compra());
        mv.addObject("clienteLogado", clienteLogado);
        return mv;
    }

    @PostMapping("/finalizar_compra")
    private ModelAndView save(@Valid Compra compra, BindingResult bd, @ModelAttribute("clienteLogado") Cliente clienteLogado, @RequestParam("produto.id") Long idProd){
        if (bd.hasErrors()){
            bd.getAllErrors().forEach(error -> {
                System.out.println("Error: " + error.getDefaultMessage());
            });
            ModelAndView mv = new ModelAndView("compra");
            mv.addObject("compra", compra);
            return mv;
        }
        else{
            Cliente clie = serviceClie.findByCpf(clienteLogado.getCpf());
            if(clie == null) {
                ModelAndView mv = new ModelAndView("compra");
                mv.addObject("compra", compra);
                return mv;
            }

            Produto produto = serviceProd.findById(idProd);
            if (produto == null){
                ModelAndView mv = new ModelAndView("compra");
                mv.addObject("compra", compra);
                return mv;
            }

            compra.setProduto(produto);
            compra.setEspecificacoes(produto.getDescricao());
            compra.setNomeProd(produto.getNome());
            compra.setCliente(clie);

            Float valorFinal;
            Atividades atividades = serviceAtv.findByClienteId(clie.getId());
            if (atividades != null){
                compra.setValorCompra(atividades.getPrecoVariado());
                atividades.setPrecoVariado(produto.getPrecoFixo() - (produto.getPrecoFixo() * 0.1f));
                atividades.setHorarioAtual(LocalTime.now());
                atividades.setDemanda("Al");
                atividades.setQntdProcura(atividades.getQntdProcura() + 1);
                serviceAtv.save(atividades);
            } else {
                valorFinal = produto.getPrecoFixo() * compra.getQntdProd();
                compra.setValorCompra(valorFinal);

                // Simulação de análise dos dados das atividades e adicionando 10% de desconto na próxima compra
                var precoDesconto = produto.getPrecoFixo() - (produto.getPrecoFixo() * 0.1);

                Atividades atv = Atividades.builder()
                        .cliente(clie)
                        .precoVariado((float)precoDesconto)
                        .demanda("Al")
                        .qntdProcura(120)
                        .climaAtual("Calor")
                        .localizacaoAtual(clie.getEndereco().getBairro())
                        .horarioAtual(LocalTime.now())
                        .build();

                serviceAtv.save(atv);
            }
            service.save(compra);
            return new ModelAndView("redirect:/compras/historico_compras/" + clie.getId());
        }
    }

    @GetMapping("/historico_compras/{id}")
    private ModelAndView historico(@PathVariable Long id, @ModelAttribute("clienteLogado") Cliente cliente){

        List<Compra> compras = service.findByClienteId(id);

        if (compras.isEmpty()) {
            ModelAndView mv = new ModelAndView("historico_compras");
            mv.addObject("mensagem", "Nenhuma compra encontrada.");
            return mv;
        }

        ModelAndView mv = new ModelAndView("historico_compras");
        mv.addObject("compras", compras);
        return mv;

    }

}

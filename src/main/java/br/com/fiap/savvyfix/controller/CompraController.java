package br.com.fiap.savvyfix.controller;

import br.com.fiap.savvyfix.mensageria.ProdutorRabbitMQ;
import br.com.fiap.savvyfix.model.Atividades;
import br.com.fiap.savvyfix.model.Cliente;
import br.com.fiap.savvyfix.model.Compra;
import br.com.fiap.savvyfix.model.Produto;
import br.com.fiap.savvyfix.service.AtividadesService;
import br.com.fiap.savvyfix.service.ClienteService;
import br.com.fiap.savvyfix.service.CompraService;
import br.com.fiap.savvyfix.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
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

    @Autowired
    private ProdutorRabbitMQ produtorRabbitMQ;

    @GetMapping("/confirmar_compra/{id}")
    private ModelAndView save(@PathVariable Long id){
        Produto produto = serviceProd.findById(id);
        if (produto == null) {
            return new ModelAndView("redirect:/produtos");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String cpf = auth.getName();

        Cliente clienteLogado = serviceClie.findByCpf(cpf);

        ModelAndView mv = new ModelAndView("compra");
        mv.addObject("produto", produto);
        mv.addObject("compra", new Compra());
        mv.addObject("clienteLogado", clienteLogado);
        return mv;
    }

    @PostMapping("/finalizar_compra")
    private ModelAndView save(Compra compra, BindingResult bd, @RequestParam("produto.id") Long idProd) {

        if (compra.getQntdProd() == null){
            ModelAndView mv = new ModelAndView("compra");
            mv.addObject("compra", compra);
            return mv;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String cpf = auth.getName();

        Cliente clie = serviceClie.findByCpf(cpf);
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

        Long atividadeId = 0L;
        
        BigDecimal valorFinal;
        List<Atividades> atividades = serviceAtv.findByClienteId(clie.getId());
        if (!atividades.isEmpty()){
            for (Atividades atv : atividades){
                if (atv.getProduto().getId() == produto.getId()){
                    BigDecimal precoVariado = BigDecimal.valueOf(atv.getPrecoVariado());
                    BigDecimal qntdProd = BigDecimal.valueOf(compra.getQntdProd());
                    valorFinal = precoVariado.multiply(qntdProd).setScale(2, RoundingMode.HALF_EVEN);
                    compra.setValorCompra(valorFinal.floatValue());
                    compra.setAtividades(atv);

                    atv.setPrecoVariado(produto.getPrecoFixo() - (produto.getPrecoFixo() * 0.1f));
                    atv.setHorarioAtual(LocalTime.now());
                    atv.setDemanda("Al");
                    atv.setQntdProcura(atv.getQntdProcura() + 1);
                    serviceAtv.save(atv);
                    atividadeId = atv.getId();
                } else {
                    valorFinal = BigDecimal.valueOf(produto.getPrecoFixo())
                            .multiply(BigDecimal.valueOf(compra.getQntdProd()))
                            .setScale(2, RoundingMode.HALF_EVEN);
                    compra.setValorCompra(valorFinal.floatValue());

                    // Simulação de análise dos dados das atividades e adicionando 10% de desconto na próxima compra
                    BigDecimal precoDesconto = BigDecimal.valueOf(produto.getPrecoFixo())
                            .multiply(BigDecimal.valueOf(0.9))
                            .setScale(2, RoundingMode.HALF_EVEN);

                    Atividades novaAtividade = Atividades.builder()
                            .cliente(clie)
                            .precoVariado(precoDesconto.floatValue())
                            .demanda("Al")
                            .qntdProcura(120)
                            .climaAtual("Calor")
                            .localizacaoAtual(clie.getEndereco().getBairro())
                            .horarioAtual(LocalTime.now())
                            .produto(produto)
                            .build();

                    serviceAtv.save(novaAtividade);
                    atividadeId = novaAtividade.getId();
                }
            }
        } else {
            valorFinal = BigDecimal.valueOf(produto.getPrecoFixo())
                    .multiply(BigDecimal.valueOf(compra.getQntdProd()))
                    .setScale(2, RoundingMode.HALF_EVEN);
            compra.setValorCompra(valorFinal.floatValue());

            // Simulação de análise dos dados das atividades e adicionando 10% de desconto na próxima compra
            BigDecimal precoDesconto = BigDecimal.valueOf(produto.getPrecoFixo())
                    .multiply(BigDecimal.valueOf(0.9))
                    .setScale(2, RoundingMode.HALF_EVEN);

            Atividades novaAtividade = Atividades.builder()
                    .cliente(clie)
                    .precoVariado(precoDesconto.floatValue())
                    .demanda("Al")
                    .qntdProcura(120)
                    .climaAtual("Calor")
                    .localizacaoAtual(clie.getEndereco().getBairro())
                    .horarioAtual(LocalTime.now())
                    .produto(produto)
                    .build();

            serviceAtv.save(novaAtividade);
            atividadeId = novaAtividade.getId();
        }

        String msg = "Compra realizada com sucesso! Informações da compra, Produto: " + produto.getNome() + "; Quantidade: " + compra.getQntdProd() +
                "; Especificações: " + compra.getEspecificacoes() + "; CPF do cliente: " + clie.getCpf() + "; Id da Atividade: " + atividadeId + "; Horário da compra: " + LocalDate.now() + "; Enviado para Cep:" + clie.getEndereco().getCep() + "; Rua: " + clie.getEndereco().getRua() +
                "; Número: " + clie.getEndereco().getNumero() + "; Valor da compra: " + compra.getValorCompra();
        produtorRabbitMQ.enviarMensagem(msg);


        service.save(compra);
        return new ModelAndView("redirect:/compras/historico_compras/" + clie.getId());
    }

    @GetMapping("/historico_compras/{id}")
    private ModelAndView historico(@PathVariable Long id, @ModelAttribute("clienteLogado") Cliente cliente){

        List<Compra> compras = service.findByClienteId(id);
        Cliente cliente1 = serviceClie.findById(id);

        if (compras.isEmpty() || cliente1 == null) {
            ModelAndView mv = new ModelAndView("historico_compras");
            mv.addObject("mensagem", "Nenhuma compra encontrada.");
            return mv;
        }

        ModelAndView mv = new ModelAndView("historico_compras");
        mv.addObject("compras", compras);
        mv.addObject("cliente", cliente1);
        return mv;

    }

}

package br.com.fiap.savvyfix.mensageria;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorRabbitMQ {

    @RabbitListener(queues = ConfiguracaoRabbitMQ.fila)
    public void lerMensagem(String msg) {
        System.out.println("Mensagem recebida: " + msg);
    }

}

package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PedidoRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Test
    public void deveSalvarUmPedidoNovo() {

        var pedido = new Pedido();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("500.00"));

        var cliente = clienteRepositorio.findById(1).orElseThrow();
        pedido.setCliente(cliente);

        pedidoRepositorio.save(pedido);

        assertNotNull(pedido.getId());
    }
}
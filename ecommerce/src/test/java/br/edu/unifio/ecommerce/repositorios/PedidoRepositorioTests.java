package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PedidoRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Test
    public void deveInserirPedido() {

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        Pedido salvo = pedidoRepositorio.save(pedido);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("ABERTO", salvo.getStatus());
        assertEquals(new BigDecimal("500.00"), salvo.getValorTotal());
        assertEquals(cliente.getId(), salvo.getCliente().getId());
    }

    @Test
    public void deveBuscarPedidoPorId() {

        List<Pedido> pedidos = pedidoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(pedidos.isEmpty());

        Pedido pedido = pedidos.get(0);

        Pedido encontrado = pedidoRepositorio.findById(
                pedido.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(pedido.getStatus(), encontrado.getStatus());
        assertEquals(pedido.getValorTotal(), encontrado.getValorTotal());
        assertEquals(pedido.getCliente().getId(), encontrado.getCliente().getId());
    }

    @Test
    public void deveListarPedidos() {

        List<Pedido> pedidos = pedidoRepositorio.findAll();

        assertNotNull(pedidos);
        assertFalse(pedidos.isEmpty());
        assertTrue(pedidos.size() >= 1);
    }

    @Test
    public void deveAlterarPedido() {

        Pedido pedido = pedidoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = pedido.getId();

        pedido.setStatus("FECHADO");
        pedido.setValorTotal(new BigDecimal("750.00"));

        pedidoRepositorio.save(pedido);

        Pedido alterado = pedidoRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals("FECHADO", alterado.getStatus());
        assertEquals(new BigDecimal("750.00"), alterado.getValorTotal());
    }

    @Test
    public void deveExcluirPedido() {

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("100.00"));
        pedido.setCliente(cliente);

        Pedido salvo = pedidoRepositorio.save(pedido);

        assertTrue(pedidoRepositorio.existsById(salvo.getId()));

        pedidoRepositorio.deleteById(salvo.getId());

        assertFalse(pedidoRepositorio.existsById(salvo.getId()));
    }
}
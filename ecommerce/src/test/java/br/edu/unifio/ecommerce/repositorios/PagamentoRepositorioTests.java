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
import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PagamentoRepositorioTests {

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    private Pedido criarPedido() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        return pedidoRepositorio.save(pedido);
    }

    @Test
    public void deveInserirPagamento() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("500.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        Pagamento salvo = pagamentoRepositorio.save(pagamento);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals(new BigDecimal("500.00"), salvo.getValor());
        assertEquals("APROVADO", salvo.getStatus());
        assertEquals("PIX", salvo.getTipo());
        assertEquals(pedido.getId(), salvo.getPedido().getId());
    }

    @Test
    public void deveBuscarPagamentoPorId() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("300.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        Pagamento salvo = pagamentoRepositorio.save(pagamento);

        Pagamento encontrado = pagamentoRepositorio.findById(
                salvo.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(salvo.getId(), encontrado.getId());
        assertEquals(new BigDecimal("300.00"), encontrado.getValor());
        assertEquals("APROVADO", encontrado.getStatus());
        assertEquals("PIX", encontrado.getTipo());
    }

    @Test
    public void deveListarPagamentos() {
        Pedido pedido1 = criarPedido();
        Pedido pedido2 = criarPedido();

        Pagamento pagamento1 = new Pagamento();
        pagamento1.setValor(new BigDecimal("100.00"));
        pagamento1.setData(LocalDateTime.now());
        pagamento1.setStatus("APROVADO");
        pagamento1.setTipo("PIX");
        pagamento1.setPedido(pedido1);

        Pagamento pagamento2 = new Pagamento();
        pagamento2.setValor(new BigDecimal("200.00"));
        pagamento2.setData(LocalDateTime.now());
        pagamento2.setStatus("PENDENTE");
        pagamento2.setTipo("CARTAO");
        pagamento2.setPedido(pedido2);

        pagamentoRepositorio.save(pagamento1);
        pagamentoRepositorio.save(pagamento2);

        List<Pagamento> pagamentos = pagamentoRepositorio.findAll();

        assertNotNull(pagamentos);
        assertTrue(pagamentos.size() >= 2);
    }

    @Test
    public void deveAlterarPagamento() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("400.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("PENDENTE");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        Pagamento salvo = pagamentoRepositorio.save(pagamento);

        Integer id = salvo.getId();

        salvo.setStatus("APROVADO");
        salvo.setTipo("CARTAO");

        pagamentoRepositorio.save(salvo);

        Pagamento alterado = pagamentoRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals(new BigDecimal("400.00"), alterado.getValor());
        assertEquals("APROVADO", alterado.getStatus());
        assertEquals("CARTAO", alterado.getTipo());
    }

    @Test
    public void deveExcluirPagamento() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        Pagamento salvo = pagamentoRepositorio.save(pagamento);

        assertTrue(pagamentoRepositorio.existsById(salvo.getId()));

        pagamentoRepositorio.deleteById(salvo.getId());

        assertFalse(pagamentoRepositorio.existsById(salvo.getId()));
    }
}
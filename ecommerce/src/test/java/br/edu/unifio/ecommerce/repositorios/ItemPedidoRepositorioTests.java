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

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.ItemPedido;
import br.edu.unifio.ecommerce.entidades.Pedido;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ItemPedidoRepositorioTests {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveInserirItemPedido() {

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("200.00"));
        pedido.setCliente(cliente);

        pedido = pedidoRepositorio.save(pedido);

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        Produto produto = new Produto();
        produto.setNome("Produto Item Teste");
        produto.setDescricao("Produto para teste do item pedido");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("100.00"));
        produto.setCategoria(categoria);

        produto = produtoRepositorio.save(produto);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("100.00"));
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        ItemPedido salvo = itemPedidoRepositorio.save(itemPedido);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals(2, salvo.getQuantidade());
        assertEquals(new BigDecimal("100.00"), salvo.getValorUnitario());
        assertEquals(pedido.getId(), salvo.getPedido().getId());
        assertEquals(produto.getId(), salvo.getProduto().getId());
    }

    @Test
    public void deveBuscarItemPedidoPorId() {

        List<ItemPedido> itens = itemPedidoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(itens.isEmpty());

        ItemPedido item = itens.get(0);

        ItemPedido encontrado = itemPedidoRepositorio.findById(
                item.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(item.getQuantidade(), encontrado.getQuantidade());
        assertEquals(item.getValorUnitario(), encontrado.getValorUnitario());
    }

    @Test
    public void deveListarItensPedido() {

        List<ItemPedido> itens = itemPedidoRepositorio.findAll();

        assertNotNull(itens);
        assertFalse(itens.isEmpty());
        assertTrue(itens.size() >= 1);
    }

    @Test
    public void deveAlterarItemPedido() {

        ItemPedido item = itemPedidoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = item.getId();

        item.setQuantidade(5);
        item.setValorUnitario(new BigDecimal("150.00"));

        itemPedidoRepositorio.save(item);

        ItemPedido alterado = itemPedidoRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals(5, alterado.getQuantidade());
        assertEquals(new BigDecimal("150.00"), alterado.getValorUnitario());
    }

    @Test
    public void deveExcluirItemPedido() {

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("50.00"));
        pedido.setCliente(cliente);

        pedido = pedidoRepositorio.save(pedido);

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        Produto produto = new Produto();
        produto.setNome("Produto Exclusao");
        produto.setDescricao("Produto para teste de exclusao");
        produto.setEstoque((short) 5);
        produto.setPreco(new BigDecimal("50.00"));
        produto.setCategoria(categoria);

        produto = produtoRepositorio.save(produto);

        ItemPedido item = new ItemPedido();
        item.setQuantidade(1);
        item.setValorUnitario(new BigDecimal("50.00"));
        item.setPedido(pedido);
        item.setProduto(produto);

        ItemPedido salvo = itemPedidoRepositorio.save(item);

        assertTrue(itemPedidoRepositorio.existsById(salvo.getId()));

        itemPedidoRepositorio.deleteById(salvo.getId());

        assertFalse(itemPedidoRepositorio.existsById(salvo.getId()));
    }
}
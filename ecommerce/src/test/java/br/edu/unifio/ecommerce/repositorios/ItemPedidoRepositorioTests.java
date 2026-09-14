package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;
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
    public void deveSalvarUmItemPedidoNovo() {

        var itemPedido = new ItemPedido();

        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("100.00"));

        var cliente = clienteRepositorio.findById(1).orElseThrow();

        var pedido = new Pedido();
        pedido.setData(java.time.LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("200.00"));
        pedido.setCliente(cliente);

        pedidoRepositorio.save(pedido);

        itemPedido.setPedido(pedido);

        var categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        var produto = new Produto();
        produto.setNome("Notebook");
        produto.setDescricao("Notebook para teste");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("100.00"));
        produto.setCategoria(categoria);

        produtoRepositorio.save(produto);

        itemPedido.setProduto(produto);

        itemPedidoRepositorio.save(itemPedido);

        assertNotNull(itemPedido.getId());
    }
}
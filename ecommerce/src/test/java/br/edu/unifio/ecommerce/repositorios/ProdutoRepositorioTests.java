package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ProdutoRepositorioTests {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveInserirProduto() {

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        Produto produto = new Produto();
        produto.setNome("Notebook Lenovo");
        produto.setDescricao("Notebook para desenvolvimento");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("3500.00"));
        produto.setCategoria(categoria);

        Produto salvo = produtoRepositorio.save(produto);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("Notebook Lenovo", salvo.getNome());
        assertEquals((short) 10, salvo.getEstoque());
        assertEquals(new BigDecimal("3500.00"), salvo.getPreco());
        assertEquals(categoria.getId(), salvo.getCategoria().getId());
    }

    @Test
    public void deveBuscarProdutoPorId() {

        List<Produto> produtos = produtoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(produtos.isEmpty());

        Produto produto = produtos.get(2);

        Produto encontrado = produtoRepositorio.findById(produto.getId()).orElseThrow();

        assertEquals(produto.getNome(), encontrado.getNome());
        assertEquals(produto.getDescricao(), encontrado.getDescricao());
        assertEquals(produto.getEstoque(), encontrado.getEstoque());
    }

    @Test
    public void deveListarProdutos() {

        List<Produto> produtos = produtoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "nome")
        );

        assertNotNull(produtos);
        assertEquals(5, produtos.size());
        assertEquals("Curso de Java", produtos.get(0).getNome());
    }

    @Test
    public void deveAlterarProduto() {

        Produto produto = produtoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = produto.getId();

        produto.setNome("Notebook Alterado");
        produto.setEstoque((short) 20);

        produtoRepositorio.save(produto);

        Produto alterado = produtoRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals("Notebook Alterado", alterado.getNome());
        assertEquals((short) 20, alterado.getEstoque());
    }

    @Test
    public void deveExcluirProduto() {

        Produto produto = new Produto();
        produto.setNome("Produto Para Excluir");
        produto.setDescricao("Produto criado para teste de exclusao");
        produto.setEstoque((short) 5);
        produto.setPreco(new BigDecimal("50.00"));

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();
        produto.setCategoria(categoria);

        Produto salvo = produtoRepositorio.save(produto);

        assertTrue(produtoRepositorio.existsById(salvo.getId()));

        produtoRepositorio.deleteById(salvo.getId());

        assertFalse(produtoRepositorio.existsById(salvo.getId()));
    }
}
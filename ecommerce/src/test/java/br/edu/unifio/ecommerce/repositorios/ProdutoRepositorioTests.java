package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ProdutoRepositorioTests {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    public void deveBuscarUmProdutoPorId() {

        List<Produto> produtos = produtoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(produtos.isEmpty());
        assertEquals(5, produtos.size());

        Produto produto = produtos.get(2);

        assertNotNull(produto);
        assertEquals("Mouse Sem Fio", produto.getNome());
    }

    @Test
    public void deveBuscarTodosOsProdutos() {

        List<Produto> produtos = produtoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "nome")
        );

        assertNotNull(produtos);
        assertEquals(5, produtos.size());
    }
}

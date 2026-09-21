package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveInserirCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Informática");
        categoria.setDescricao("Produtos de informática");

        Categoria salva = categoriaRepositorio.save(categoria);

        assertNotNull(salva);
        assertNotNull(salva.getId());
        assertEquals("Informática", salva.getNome());
        assertEquals("Produtos de informática", salva.getDescricao());
    }

    @Test
    public void deveBuscarCategoriaPorId() {

        Categoria categoria = categoriaRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Categoria encontrada = categoriaRepositorio.findById(
                categoria.getId()
        ).orElseThrow();

        assertNotNull(encontrada);
        assertEquals(categoria.getNome(), encontrada.getNome());
        assertEquals(categoria.getDescricao(), encontrada.getDescricao());
    }

    @Test
    public void deveListarCategorias() {

        List<Categoria> categorias = categoriaRepositorio.findAll();

        assertNotNull(categorias);
        assertFalse(categorias.isEmpty());
        assertTrue(categorias.size() >= 5);
    }

    @Test
    public void deveAlterarCategoria() {

        Categoria categoria = categoriaRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Short id = categoria.getId();

        categoria.setNome("Tecnologia Alterada");
        categoria.setDescricao("Descrição alterada");

        categoriaRepositorio.save(categoria);

        Categoria alterada = categoriaRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterada.getId());
        assertEquals("Tecnologia Alterada", alterada.getNome());
        assertEquals("Descrição alterada", alterada.getDescricao());
    }

    @Test
    public void deveExcluirCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Para Excluir");
        categoria.setDescricao("Categoria criada para teste de exclusão");

        Categoria salva = categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(salva.getId()));

        categoriaRepositorio.deleteById(salva.getId());

        assertFalse(categoriaRepositorio.existsById(salva.getId()));
    }
}
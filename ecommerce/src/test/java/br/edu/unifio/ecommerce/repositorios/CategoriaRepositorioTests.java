package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveSalvarUmaCategoriaNova() {

        var categoria = new Categoria();

        categoria.setNome("Informática");
        categoria.setDescricao("Produtos de informática");

        categoriaRepositorio.save(categoria);

        assertNotNull(categoria.getId());
    }
}
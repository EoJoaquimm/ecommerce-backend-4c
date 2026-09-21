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

import br.edu.unifio.ecommerce.entidades.Palestrante;

@SpringBootTest
public class PalestranteRepositorioTests {

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    @Test
    public void deveInserirPalestrante() {

        Palestrante palestrante = new Palestrante();

        palestrante.setNome("Palestrante de Teste");
        palestrante.setMiniBio("Especialista em tecnologia");
        palestrante.setEmail("palestrante@teste.com");

        Palestrante salvo = palestranteRepositorio.save(palestrante);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("Palestrante de Teste", salvo.getNome());
        assertEquals("Especialista em tecnologia", salvo.getMiniBio());
        assertEquals("palestrante@teste.com", salvo.getEmail());
    }

    @Test
    public void deveBuscarPalestrantePorId() {

        List<Palestrante> palestrantes = palestranteRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(palestrantes.isEmpty());

        Palestrante palestrante = palestrantes.get(0);

        Palestrante encontrado = palestranteRepositorio.findById(
                palestrante.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(palestrante.getNome(), encontrado.getNome());
        assertEquals(palestrante.getEmail(), encontrado.getEmail());
        assertEquals(palestrante.getMiniBio(), encontrado.getMiniBio());
    }

    @Test
    public void deveListarPalestrantes() {

        List<Palestrante> palestrantes = palestranteRepositorio.findAll();

        assertNotNull(palestrantes);
        assertFalse(palestrantes.isEmpty());
        assertTrue(palestrantes.size() >= 5);
    }

    @Test
    public void deveAlterarPalestrante() {

        Palestrante palestrante = palestranteRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = palestrante.getId();

        palestrante.setNome("Palestrante Alterado");
        palestrante.setEmail("alterado@teste.com");

        palestranteRepositorio.save(palestrante);

        Palestrante alterado = palestranteRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals("Palestrante Alterado", alterado.getNome());
        assertEquals("alterado@teste.com", alterado.getEmail());
    }

    @Test
    public void deveExcluirPalestrante() {

        Palestrante palestrante = new Palestrante();

        palestrante.setNome("Palestrante Para Excluir");
        palestrante.setMiniBio("Teste de exclusao");
        palestrante.setEmail("excluir@teste.com");

        Palestrante salvo = palestranteRepositorio.save(palestrante);

        assertTrue(palestranteRepositorio.existsById(salvo.getId()));

        palestranteRepositorio.deleteById(salvo.getId());

        assertFalse(palestranteRepositorio.existsById(salvo.getId()));
    }
}
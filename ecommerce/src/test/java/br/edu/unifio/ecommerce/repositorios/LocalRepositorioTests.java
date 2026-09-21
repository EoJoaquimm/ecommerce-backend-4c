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

import br.edu.unifio.ecommerce.entidades.Local;

@SpringBootTest
public class LocalRepositorioTests {

    @Autowired
    private LocalRepositorio localRepositorio;

    @Test
    public void deveInserirLocal() {

        Local local = new Local();

        local.setNome("Local de Teste");
        local.setEndereco("Rua de Teste, 100");
        local.setCapacidade(200);

        Local salvo = localRepositorio.save(local);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("Local de Teste", salvo.getNome());
        assertEquals("Rua de Teste, 100", salvo.getEndereco());
        assertEquals(200, salvo.getCapacidade());
    }

    @Test
    public void deveBuscarLocalPorId() {

        List<Local> locais = localRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(locais.isEmpty());

        Local local = locais.get(0);

        Local encontrado = localRepositorio.findById(
                local.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(local.getNome(), encontrado.getNome());
        assertEquals(local.getEndereco(), encontrado.getEndereco());
        assertEquals(local.getCapacidade(), encontrado.getCapacidade());
    }

    @Test
    public void deveListarLocais() {

        List<Local> locais = localRepositorio.findAll();

        assertNotNull(locais);
        assertFalse(locais.isEmpty());
        assertTrue(locais.size() >= 5);
    }

    @Test
    public void deveAlterarLocal() {

        Local local = localRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = local.getId();

        local.setNome("Local Alterado");
        local.setCapacidade(500);

        localRepositorio.save(local);

        Local alterado = localRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals("Local Alterado", alterado.getNome());
        assertEquals(500, alterado.getCapacidade());
    }

    @Test
    public void deveExcluirLocal() {

        Local local = new Local();

        local.setNome("Local Para Excluir");
        local.setEndereco("Endereco para teste");
        local.setCapacidade(50);

        Local salvo = localRepositorio.save(local);

        assertTrue(localRepositorio.existsById(salvo.getId()));

        localRepositorio.deleteById(salvo.getId());

        assertFalse(localRepositorio.existsById(salvo.getId()));
    }
}
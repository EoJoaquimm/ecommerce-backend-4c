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

import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest
public class ClienteRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void deveInserirCliente() {

        Cliente cliente = new Cliente();

        cliente.setNome("Joaquim Costa");
        cliente.setEmail("joaquim@email.com");
        cliente.setTelefone("14999999999");

        Cliente salvo = clienteRepositorio.save(cliente);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("Joaquim Costa", salvo.getNome());
        assertEquals("joaquim@email.com", salvo.getEmail());
        assertEquals("14999999999", salvo.getTelefone());
    }

    @Test
    public void deveBuscarClientePorId() {

        Cliente cliente = clienteRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Cliente encontrado = clienteRepositorio.findById(
                cliente.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(cliente.getNome(), encontrado.getNome());
        assertEquals(cliente.getEmail(), encontrado.getEmail());
        assertEquals(cliente.getTelefone(), encontrado.getTelefone());
    }

    @Test
    public void deveListarClientes() {

        List<Cliente> clientes = clienteRepositorio.findAll();

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertTrue(clientes.size() >= 1);
    }

    @Test
    public void deveAlterarCliente() {

        Cliente cliente = clienteRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = cliente.getId();

        cliente.setNome("Joaquim Alterado");
        cliente.setEmail("alterado@email.com");

        clienteRepositorio.save(cliente);

        Cliente alterado = clienteRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals("Joaquim Alterado", alterado.getNome());
        assertEquals("alterado@email.com", alterado.getEmail());
    }

    @Test
    public void deveExcluirCliente() {

        Cliente cliente = new Cliente();

        cliente.setNome("Cliente Para Excluir");
        cliente.setEmail("excluir@email.com");
        cliente.setTelefone("14988888888");

        Cliente salvo = clienteRepositorio.save(cliente);

        assertTrue(clienteRepositorio.existsById(salvo.getId()));

        clienteRepositorio.deleteById(salvo.getId());

        assertFalse(clienteRepositorio.existsById(salvo.getId()));
    }
}
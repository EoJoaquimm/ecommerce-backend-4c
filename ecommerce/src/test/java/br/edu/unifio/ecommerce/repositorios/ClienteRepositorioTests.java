package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest
public class ClienteRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void deveSalvarUmClienteNovo() {

        var cliente = new Cliente();

        cliente.setNome("Joaquim Costa");
        cliente.setEmail("joaquim@email.com");
        cliente.setTelefone("14999999999");

        clienteRepositorio.save(cliente);

        assertNotNull(cliente.getId());
    }
}
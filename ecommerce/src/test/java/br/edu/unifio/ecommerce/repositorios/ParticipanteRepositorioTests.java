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

import br.edu.unifio.ecommerce.entidades.Participante;

@SpringBootTest
public class ParticipanteRepositorioTests {

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Test
    public void deveInserirParticipante() {
        Participante participante = new Participante();

        participante.setNome("Participante Teste");
        participante.setEmail("participante@teste.com");
        participante.setTelefone("14999999999");

        Participante salvo = participanteRepositorio.save(participante);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("Participante Teste", salvo.getNome());
        assertEquals("participante@teste.com", salvo.getEmail());
        assertEquals("14999999999", salvo.getTelefone());
    }

    @Test
    public void deveBuscarParticipantePorId() {
        List<Participante> participantes = participanteRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(participantes.isEmpty());

        Participante participante = participantes.get(0);

        Participante encontrado = participanteRepositorio.findById(
                participante.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(participante.getNome(), encontrado.getNome());
        assertEquals(participante.getEmail(), encontrado.getEmail());
        assertEquals(participante.getTelefone(), encontrado.getTelefone());
    }

    @Test
    public void deveListarParticipantes() {
        List<Participante> participantes = participanteRepositorio.findAll();

        assertNotNull(participantes);
        assertFalse(participantes.isEmpty());
        assertTrue(participantes.size() >= 5);
    }

    @Test
    public void deveAlterarParticipante() {
        Participante participante = participanteRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = participante.getId();

        participante.setNome("Participante Alterado");
        participante.setEmail("alterado@teste.com");

        participanteRepositorio.save(participante);

        Participante alterado = participanteRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals("Participante Alterado", alterado.getNome());
        assertEquals("alterado@teste.com", alterado.getEmail());
    }

    @Test
    public void deveExcluirParticipante() {
        Participante participante = new Participante();

        participante.setNome("Participante Para Excluir");
        participante.setEmail("excluir@teste.com");
        participante.setTelefone("14988888888");

        Participante salvo = participanteRepositorio.save(participante);

        assertTrue(participanteRepositorio.existsById(salvo.getId()));

        participanteRepositorio.deleteById(salvo.getId());

        assertFalse(participanteRepositorio.existsById(salvo.getId()));
    }
}
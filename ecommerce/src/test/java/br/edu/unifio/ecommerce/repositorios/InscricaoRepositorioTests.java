package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Evento;
import br.edu.unifio.ecommerce.entidades.Inscricao;
import br.edu.unifio.ecommerce.entidades.Participante;

@SpringBootTest
public class InscricaoRepositorioTests {

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Test
    public void deveInserirInscricao() {
        Evento evento = eventoRepositorio.findById(1).orElseThrow();
        Participante participante = participanteRepositorio.findById(1).orElseThrow();

        Inscricao inscricao = new Inscricao();
        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("CONFIRMADA");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        Inscricao salva = inscricaoRepositorio.save(inscricao);

        assertNotNull(salva);
        assertNotNull(salva.getId());
        assertEquals("CONFIRMADA", salva.getStatus());
        assertEquals(evento.getId(), salva.getEvento().getId());
        assertEquals(participante.getId(), salva.getParticipante().getId());
    }

    @Test
    public void deveBuscarInscricaoPorId() {
        List<Inscricao> inscricoes = inscricaoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(inscricoes.isEmpty());

        Inscricao inscricao = inscricoes.get(0);

        Inscricao encontrada = inscricaoRepositorio.findById(
                inscricao.getId()
        ).orElseThrow();

        assertNotNull(encontrada);
        assertEquals(inscricao.getStatus(), encontrada.getStatus());
        assertEquals(
                inscricao.getEvento().getId(),
                encontrada.getEvento().getId()
        );
        assertEquals(
                inscricao.getParticipante().getId(),
                encontrada.getParticipante().getId()
        );
    }

    @Test
    public void deveListarInscricoes() {
        List<Inscricao> inscricoes = inscricaoRepositorio.findAll();

        assertNotNull(inscricoes);
        assertFalse(inscricoes.isEmpty());
        assertTrue(inscricoes.size() >= 5);
    }

    @Test
    public void deveAlterarInscricao() {
        Inscricao inscricao = inscricaoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = inscricao.getId();

        inscricao.setStatus("CANCELADA");

        inscricaoRepositorio.save(inscricao);

        Inscricao alterada = inscricaoRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterada.getId());
        assertEquals("CANCELADA", alterada.getStatus());
        assertEquals(
                inscricao.getEvento().getId(),
                alterada.getEvento().getId()
        );
        assertEquals(
                inscricao.getParticipante().getId(),
                alterada.getParticipante().getId()
        );
    }

    @Test
    public void deveExcluirInscricao() {
        Evento evento = eventoRepositorio.findById(1).orElseThrow();
        Participante participante = participanteRepositorio.findById(1).orElseThrow();

        Inscricao inscricao = new Inscricao();
        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("CONFIRMADA");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        Inscricao salva = inscricaoRepositorio.save(inscricao);

        assertTrue(inscricaoRepositorio.existsById(salva.getId()));

        inscricaoRepositorio.deleteById(salva.getId());

        assertFalse(inscricaoRepositorio.existsById(salva.getId()));
    }
}
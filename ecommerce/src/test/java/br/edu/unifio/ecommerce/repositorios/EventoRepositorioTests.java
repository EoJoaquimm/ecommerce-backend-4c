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

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Evento;
import br.edu.unifio.ecommerce.entidades.Local;
import br.edu.unifio.ecommerce.entidades.Palestrante;

@SpringBootTest
public class EventoRepositorioTests {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    @Autowired
    private LocalRepositorio localRepositorio;

    @Test
    public void deveInserirEvento() {

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();
        Local local = localRepositorio.findById(1).orElseThrow();
        Palestrante palestrante = palestranteRepositorio.findById(1).orElseThrow();

        Evento evento = new Evento();

        evento.setNome("Evento de Teste");
        evento.setDescricao("Evento criado para teste");
        evento.setDataInicio(LocalDateTime.of(2026, 10, 1, 19, 0));
        evento.setDataFim(LocalDateTime.of(2026, 10, 1, 22, 0));
        evento.setCapacidade(100);
        evento.setStatus("ABERTO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        Evento salvo = eventoRepositorio.save(evento);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("Evento de Teste", salvo.getNome());
        assertEquals(100, salvo.getCapacidade());
        assertEquals(categoria.getId(), salvo.getCategoria().getId());
        assertEquals(local.getId(), salvo.getLocal().getId());
        assertEquals(palestrante.getId(), salvo.getPalestrante().getId());
    }

    @Test
    public void deveBuscarEventoPorId() {

        List<Evento> eventos = eventoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );

        assertFalse(eventos.isEmpty());

        Evento evento = eventos.get(0);

        Evento encontrado = eventoRepositorio.findById(
                evento.getId()
        ).orElseThrow();

        assertNotNull(encontrado);
        assertEquals(evento.getNome(), encontrado.getNome());
        assertEquals(evento.getDescricao(), encontrado.getDescricao());
        assertEquals(evento.getCapacidade(), encontrado.getCapacidade());
    }

    @Test
    public void deveListarEventos() {

        List<Evento> eventos = eventoRepositorio.findAll();

        assertNotNull(eventos);
        assertFalse(eventos.isEmpty());
        assertTrue(eventos.size() >= 5);
    }

    @Test
    public void deveAlterarEvento() {

        Evento evento = eventoRepositorio.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        ).get(0);

        Integer id = evento.getId();

        evento.setNome("Evento Alterado");
        evento.setCapacidade(200);
        evento.setStatus("FECHADO");

        eventoRepositorio.save(evento);

        Evento alterado = eventoRepositorio.findById(id).orElseThrow();

        assertEquals(id, alterado.getId());
        assertEquals("Evento Alterado", alterado.getNome());
        assertEquals(200, alterado.getCapacidade());
        assertEquals("FECHADO", alterado.getStatus());
    }

    @Test
    public void deveExcluirEvento() {

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();
        Local local = localRepositorio.findById(1).orElseThrow();
        Palestrante palestrante = palestranteRepositorio.findById(1).orElseThrow();

        Evento evento = new Evento();

        evento.setNome("Evento Para Excluir");
        evento.setDescricao("Evento criado para teste de exclusao");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(2));
        evento.setCapacidade(50);
        evento.setStatus("ABERTO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        Evento salvo = eventoRepositorio.save(evento);

        assertTrue(eventoRepositorio.existsById(salvo.getId()));

        eventoRepositorio.deleteById(salvo.getId());

        assertFalse(eventoRepositorio.existsById(salvo.getId()));
    }
}
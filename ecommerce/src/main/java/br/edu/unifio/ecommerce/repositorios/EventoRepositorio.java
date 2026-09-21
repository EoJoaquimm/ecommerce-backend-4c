package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifio.ecommerce.entidades.Evento;

public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
}
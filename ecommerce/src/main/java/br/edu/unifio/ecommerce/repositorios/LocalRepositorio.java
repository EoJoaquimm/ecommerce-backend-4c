package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifio.ecommerce.entidades.Local;

public interface LocalRepositorio extends JpaRepository<Local, Integer> {
}
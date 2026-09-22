package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifio.ecommerce.entidades.Inscricao;

public interface InscricaoRepositorio extends JpaRepository<Inscricao, Integer> {
}
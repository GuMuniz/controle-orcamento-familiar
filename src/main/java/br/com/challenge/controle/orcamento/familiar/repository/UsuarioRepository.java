package br.com.challenge.controle.orcamento.familiar.repository;


import br.com.challenge.controle.orcamento.familiar.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}

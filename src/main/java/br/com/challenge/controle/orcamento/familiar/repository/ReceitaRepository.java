package br.com.challenge.controle.orcamento.familiar.repository;


import br.com.challenge.controle.orcamento.familiar.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query("SELECT r FROM Receita r WHERE r.descricao = :descricao")
    List<Receita> findByDescricao(String descricao);

    @Query("SELECT r FROM Receita r WHERE MONTH(r.data) = :mes AND YEAR(r.data) = :ano")
    List<Receita> buscarPorMes(Integer ano, Integer mes);

    @Query("SELECT r FROM Receita r WHERE r.descricao = :descricao AND MONTH(r.data) = :mes AND YEAR(r.data) = :ano")
    Optional<Receita> buscarPorDescricaoEData(String descricao, Integer mes, Integer ano);

}

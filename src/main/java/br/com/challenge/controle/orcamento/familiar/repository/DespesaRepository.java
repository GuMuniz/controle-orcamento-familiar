package br.com.challenge.controle.orcamento.familiar.repository;

import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query("SELECT d FROM Despesa d WHERE d.descricao = :descricao")
    List<Despesa> findByDescricao(String descricao);

    @Query("SELECT d FROM Despesa d WHERE MONTH(d.data) = :mes AND YEAR(d.data) = :ano")
    List<Despesa> buscarPorMes(Integer ano, Integer mes);

    @Query("SELECT d FROM Despesa d WHERE d.descricao = :descricao AND MONTH(d.data) = :mes AND YEAR(d.data) = :ano")
    Optional<Despesa> buscarPorDescricaoEData(String descricao, Integer mes, Integer ano);

}

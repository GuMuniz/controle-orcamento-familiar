package br.com.challenge.controle.orcamento.familiar.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="receitas")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;


    public Receita() {
    }

    public Receita(String descricao, BigDecimal valor, LocalDateTime data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

}
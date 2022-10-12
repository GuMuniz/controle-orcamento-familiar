package br.com.challenge.controle.orcamento.familiar.model;

import br.com.challenge.controle.orcamento.familiar.enums.CategoriaEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="despesas")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    public Despesa() {

    }

    public Despesa(String descricao, BigDecimal valor, LocalDateTime data, CategoriaEnum categoira) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoira;
    }
}

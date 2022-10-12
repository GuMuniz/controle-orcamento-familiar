package br.com.challenge.controle.orcamento.familiar.dto;

import br.com.challenge.controle.orcamento.familiar.model.Receita;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReceitaDto {
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDateTime data;

    public ReceitaDto(Receita receita) {
        this.id = receita.getId();
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
        this.data = receita.getData();
    }

}

package br.com.challenge.controle.orcamento.familiar.dto;


import br.com.challenge.controle.orcamento.familiar.enums.CategoriaEnum;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DespesaDto {
    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDateTime data;

    private CategoriaEnum categoria;

    public DespesaDto(Despesa despesa) {
        this.id = despesa.getId();
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
        this.data = despesa.getData();
        this.categoria = despesa.getCategoria();
    }

}

package br.com.challenge.controle.orcamento.familiar.dto;

import br.com.challenge.controle.orcamento.familiar.enums.CategoriaEnum;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AtualizaDespesaDto {

    @NotBlank
    private String descricao;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotBlank
    private String valor;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @NotNull
    private LocalDateTime data;

    @NotBlank
    private String categoria;

    public String getDescricao() {
        return descricao;
    }

    public String getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setDespesa(Despesa despesa) {
        despesa.setValor(new BigDecimal(valor));
        despesa.setDescricao(descricao);
        despesa.setData(data);
        despesa.setCategoria(CategoriaEnum.valueOf(categoria));

    }
}

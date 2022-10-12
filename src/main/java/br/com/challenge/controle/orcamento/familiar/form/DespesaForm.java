package br.com.challenge.controle.orcamento.familiar.form;

import br.com.challenge.controle.orcamento.familiar.enums.CategoriaEnum;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class DespesaForm {

    @NotBlank
    private String descricao;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotBlank
    private String valor;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @NotNull
    private LocalDateTime data;

    private String categoria;

    public Despesa toDespesa() {
        CategoriaEnum cat = (categoria == null || categoria.trim().isEmpty()) ? CategoriaEnum.OUTROS
                : CategoriaEnum.valueOf(categoria.toUpperCase());

        return new Despesa(descricao, new BigDecimal(valor), data, cat);
    }

}

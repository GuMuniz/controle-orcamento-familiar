package br.com.challenge.controle.orcamento.familiar.form;

import br.com.challenge.controle.orcamento.familiar.model.Receita;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReceitaForm {

    @NotBlank
    private String descricao;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotBlank
    private String valor;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @NotNull
    private LocalDateTime data;


    public Receita toReceita() {
        return new Receita(descricao, new BigDecimal(valor), data);
    }
}

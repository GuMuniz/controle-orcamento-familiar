package br.com.challenge.controle.orcamento.familiar.dto;

import br.com.challenge.controle.orcamento.familiar.enums.CategoriaEnum;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import br.com.challenge.controle.orcamento.familiar.model.Receita;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ResumoDto {
    private BigDecimal valorTotalReceitas;
    private BigDecimal valorTotalDespesas;
    private BigDecimal saldoFinalMes;
    private Map<CategoriaEnum, BigDecimal> gastosCategorias;

    public ResumoDto(List<Receita> listReceita, List<Despesa> listDespesa) {
        this.valorTotalReceitas = new BigDecimal(listReceita.stream()
                .mapToDouble(receita -> Double.parseDouble(String.valueOf(receita.getValor()))).sum());
        this.valorTotalDespesas = new BigDecimal(listDespesa.stream()
                .mapToDouble(despesa -> Double.parseDouble(String.valueOf(despesa.getValor()))).sum());
        this.saldoFinalMes = valorTotalReceitas.subtract(valorTotalDespesas);
        this.gastosCategorias = new HashMap<>();

        listDespesa.forEach(despesa -> {
            if (gastosCategorias.containsKey(despesa.getCategoria())) {
                gastosCategorias.replace(despesa.getCategoria(), gastosCategorias.get(despesa.getCategoria()).add(despesa.getValor()));
            } else {
                gastosCategorias.put(despesa.getCategoria(), despesa.getValor());
            }
        });
    }
}

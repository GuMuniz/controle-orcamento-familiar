package br.com.challenge.controle.orcamento.familiar.service;

import br.com.challenge.controle.orcamento.familiar.dto.ResumoDto;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import br.com.challenge.controle.orcamento.familiar.model.Receita;
import br.com.challenge.controle.orcamento.familiar.repository.DespesaRepository;
import br.com.challenge.controle.orcamento.familiar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumoService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    public ResumoDto resumoDoMes(Integer ano, Integer mes) {
        List<Receita> listReceita =receitaRepository.buscarPorMes(ano, mes);
        List<Despesa> listDespesa = despesaRepository.buscarPorMes(ano, mes);
        return new ResumoDto(listReceita, listDespesa);
    }
}
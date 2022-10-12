package br.com.challenge.controle.orcamento.familiar.service;

import br.com.challenge.controle.orcamento.familiar.dto.AtualizaReceitaDto;
import br.com.challenge.controle.orcamento.familiar.dto.ReceitaDto;
import br.com.challenge.controle.orcamento.familiar.form.ReceitaForm;
import br.com.challenge.controle.orcamento.familiar.model.Receita;
import br.com.challenge.controle.orcamento.familiar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;


    public List<ReceitaDto> listar() {
        return receitaRepository.findAll().stream().map(ReceitaDto::new).collect(Collectors.toList());
    }


    public ReceitaDto buscarPorId(Long id) {
        Optional<Receita> receitaOpt = receitaRepository.findById(id);
        return (receitaOpt.isPresent()) ? new ReceitaDto(receitaOpt.get()) : null;
    }

    public List<ReceitaDto> buscarPorDescricao(String descricao) {
        return receitaRepository.findByDescricao(descricao).stream().map(ReceitaDto::new).collect(Collectors.toList());
    }

    public List<ReceitaDto> listarPorMes(Integer ano, Integer mes) {
        return receitaRepository.buscarPorMes(ano, mes).stream().map(ReceitaDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ReceitaDto cadastrar(ReceitaForm receitaForm) {
        if (validarExiste(receitaForm.getDescricao(), receitaForm.getData().getMonthValue(),
                receitaForm.getData().getYear())) {
            return null;
        }
        Receita receita = receitaForm.toReceita();
        receitaRepository.save(receita);
        return new ReceitaDto(receita);

    }

    @Transactional
    public ReceitaDto atualizar(Long id, AtualizaReceitaDto receitaDto) {
        Optional<Receita> receitaOpt = receitaRepository.findById(id);
        if (receitaOpt.isPresent() && !validarExiste(receitaDto.getDescricao(),
                receitaDto.getData().getMonthValue(), receitaDto.getData().getYear())) {
            Receita receita = receitaOpt.get();
            receitaDto.setReceita(receita);
            return new ReceitaDto(receita);
        }
        return null;
    }

    public Boolean deletar(Long id) {
        Optional<Receita> receitaOpt = receitaRepository.findById(id);
        if (receitaOpt.isPresent()) {
            receitaRepository.delete(receitaOpt.get());
            return true;
        }
        return false;
    }


    public Boolean validarExiste(String descricao, Integer mes, Integer ano) {
        Optional<Receita> receitaOpt = receitaRepository.buscarPorDescricaoEData(descricao, mes, ano);
        return receitaOpt.isPresent();
    }
}

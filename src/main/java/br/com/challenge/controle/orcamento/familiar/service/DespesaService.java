package br.com.challenge.controle.orcamento.familiar.service;

import br.com.challenge.controle.orcamento.familiar.dto.AtualizaDespesaDto;
import br.com.challenge.controle.orcamento.familiar.dto.DespesaDto;
import br.com.challenge.controle.orcamento.familiar.form.DespesaForm;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import br.com.challenge.controle.orcamento.familiar.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<DespesaDto> listar() {
        return despesaRepository.findAll().stream().map(DespesaDto::new).collect(Collectors.toList());
    }

    public List<DespesaDto> buscarPorDescricao(String descricao){
        return despesaRepository.findByDescricao(descricao).stream().map(DespesaDto::new).collect(Collectors.toList());
    }

    public List<DespesaDto> listarPorMes(Integer ano, Integer mes){
        return despesaRepository.buscarPorMes(ano, mes).stream().map(DespesaDto::new).collect(Collectors.toList());
    }

    public DespesaDto buscarPorId(Long id) {
        Optional<Despesa> despesaDto = despesaRepository.findById(id);
        return (despesaDto.isPresent()) ? new DespesaDto(despesaDto.get()) : null;
    }

    @Transactional
    public DespesaDto cadastrar(DespesaForm despesaForm) {
        if (validarExiste(despesaForm.getDescricao(), despesaForm.getData().getMonthValue(),
                despesaForm.getData().getYear())) {
            return null;
        }
        Despesa despesa = despesaForm.toDespesa();
        despesaRepository.save(despesa);
        return new DespesaDto(despesa);

    }

    @Transactional
    public DespesaDto atualizar(Long id, AtualizaDespesaDto despesaDto) {
        Optional<Despesa> despesaOpt = despesaRepository.findById(id);
        if (despesaOpt.isPresent() && !validarExiste(despesaDto.getDescricao(),
                despesaDto.getData().getMonthValue(), despesaDto.getData().getYear())) {
            Despesa despesa = despesaOpt.get();
            despesaDto.setDespesa(despesa);
            return new DespesaDto(despesa);
        }
        return null;
    }

    public Boolean deletar(Long id) {
        Optional<Despesa> despesDto = despesaRepository.findById(id);
        if (despesDto.isPresent()) {
            despesaRepository.delete(despesDto.get());
            return true;
        }
        return false;
    }

    public Boolean validarExiste(String descricao, Integer mes, Integer ano) {
        Optional<Despesa> receitaOpt = despesaRepository.buscarPorDescricaoEData(descricao, mes, ano);
        return receitaOpt.isPresent();
    }
}

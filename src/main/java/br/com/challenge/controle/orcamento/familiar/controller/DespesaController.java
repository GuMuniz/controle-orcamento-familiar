package br.com.challenge.controle.orcamento.familiar.controller;

import br.com.challenge.controle.orcamento.familiar.dto.AtualizaDespesaDto;
import br.com.challenge.controle.orcamento.familiar.dto.DespesaDto;
import br.com.challenge.controle.orcamento.familiar.form.DespesaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.challenge.controle.orcamento.familiar.service.DespesaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;


    @GetMapping("/")
    public ResponseEntity<List<DespesaDto>> listarDespesas() {
        List<DespesaDto> listaDespesaDto = despesaService.listar();
        return (listaDespesaDto.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(listaDespesaDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDto> buscarDespesa(@PathVariable Long id) {
        DespesaDto despesaDto = despesaService.buscarPorId(id);
        return (despesaDto != null) ? ResponseEntity.ok(despesaDto) : ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<DespesaDto>> buscaPorDescricao(@RequestParam("descricao") String descricao){
        List<DespesaDto> listaDespesaDto = despesaService.buscarPorDescricao(descricao);
        return (listaDespesaDto.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(listaDespesaDto);
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<List<DespesaDto>> listarPorMes(@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes) {
        List<DespesaDto> listaDespesaDto = despesaService.listarPorMes(ano, mes);
        return (listaDespesaDto.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(listaDespesaDto);
    }

    @PostMapping
    public ResponseEntity<DespesaDto> cadastrarDespesa(@Valid @RequestBody DespesaForm despesaForm,
                                                       UriComponentsBuilder uriBuilder) {
        DespesaDto despesaDto = despesaService.cadastrar(despesaForm);
        return (despesaDto != null) ? ResponseEntity.created(uriBuilder.path("/{id}").buildAndExpand(despesaDto.getId())
                .toUri()).body(despesaDto) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaDto> atualizarDespesa(@PathVariable Long id,
                                                       @Valid @RequestBody AtualizaDespesaDto atualizaDespesa) {
        DespesaDto despesaDto = despesaService.atualizar(id, atualizaDespesa);
        return (despesaDto != null) ? ResponseEntity.ok().body(despesaDto) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDespesa(@PathVariable Long id) {
        return (despesaService.deletar(id)) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

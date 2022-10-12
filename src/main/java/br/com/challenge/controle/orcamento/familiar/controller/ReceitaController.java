package br.com.challenge.controle.orcamento.familiar.controller;

import br.com.challenge.controle.orcamento.familiar.dto.AtualizaReceitaDto;
import br.com.challenge.controle.orcamento.familiar.dto.ReceitaDto;
import br.com.challenge.controle.orcamento.familiar.form.ReceitaForm;
import br.com.challenge.controle.orcamento.familiar.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/")
    public ResponseEntity<List<ReceitaDto>> listarReceitas() {
        List<ReceitaDto> listaReceitaDto = receitaService.listar();
        return (listaReceitaDto.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(listaReceitaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDto> buscarReceita(@PathVariable Long id) {
        ReceitaDto receitaDto = receitaService.buscarPorId(id);
        return (receitaDto != null) ? ResponseEntity.ok(receitaDto) : ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<ReceitaDto>> buscaPorDescricao(@RequestParam("descricao") String descricao) {
        List<ReceitaDto> listaReceitaDto = receitaService.buscarPorDescricao(descricao);
        return (listaReceitaDto.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(listaReceitaDto);
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<List<ReceitaDto>> listarPorMes(@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes) {
        List<ReceitaDto> listaReceitaDto = receitaService.listarPorMes(ano, mes);
        return (listaReceitaDto.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(listaReceitaDto);
    }

    @PostMapping
    public ResponseEntity<ReceitaDto> cadastrarReceita(@Valid @RequestBody ReceitaForm receitaForm,
                                                       UriComponentsBuilder uriBuilder) {
        ReceitaDto receitaDto = receitaService.cadastrar(receitaForm);
        return (receitaDto != null) ? ResponseEntity.created(uriBuilder.path("/{id}").buildAndExpand(receitaDto.getId()).toUri())
                .body(receitaDto) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDto> atualizarReceita(@PathVariable Long id,
                                                       @Valid @RequestBody AtualizaReceitaDto atualizaReceita) {
        ReceitaDto receitDto = receitaService.atualizar(id, atualizaReceita);
        return (receitDto != null) ? ResponseEntity.ok(receitDto) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarReceita(@PathVariable Long id) {
        return (receitaService.deletar(id)) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}

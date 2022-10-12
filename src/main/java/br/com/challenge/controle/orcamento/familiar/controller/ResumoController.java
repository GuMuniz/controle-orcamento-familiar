package br.com.challenge.controle.orcamento.familiar.controller;


import br.com.challenge.controle.orcamento.familiar.dto.ResumoDto;
import br.com.challenge.controle.orcamento.familiar.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResumoDto resumoDoMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        return resumoService.resumoDoMes(ano, mes);
    }
}
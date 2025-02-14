package com.mercadoapp.mercado_app.controllers;

import com.mercadoapp.mercado_app.models.Atendimento;
import com.mercadoapp.mercado_app.services.AtendimentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/atendimentos")
public class AtendimentoController {
    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping
    public List<Atendimento> listarTodos() {
        return atendimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Atendimento> buscarPorId(@PathVariable Long id) {
        return atendimentoService.buscarPorId(id);
    }

    @PostMapping
    public Atendimento salvar(@RequestBody Atendimento atendimento) {
        return atendimentoService.salvar(atendimento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        atendimentoService.deletar(id);
    }
}

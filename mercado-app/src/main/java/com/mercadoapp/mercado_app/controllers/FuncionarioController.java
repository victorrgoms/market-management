package com.mercadoapp.mercado_app.controllers;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Funcionario;
import com.mercadoapp.mercado_app.services.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id);
    }

    @PostMapping
    public Funcionario salvar(@RequestBody Funcionario funcionario) {
        return funcionarioService.salvar(funcionario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Funcionario> atualizar(@RequestBody Funcionario funcionario, @PathVariable Long id) {
        try {
            Funcionario funcionarioAtualizado = funcionarioService.atualizar(funcionario, id);
            return ResponseEntity.ok(funcionarioAtualizado);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

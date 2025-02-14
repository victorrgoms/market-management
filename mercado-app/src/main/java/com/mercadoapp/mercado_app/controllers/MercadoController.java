package com.mercadoapp.mercado_app.controllers;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Mercado;
import com.mercadoapp.mercado_app.services.MercadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/mercados")
public class MercadoController {
    private final MercadoService mercadoService;

    public MercadoController(MercadoService mercadoService) {
        this.mercadoService = mercadoService;
    }

    @GetMapping
    public List<Mercado> listarTodos() {
        return mercadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Mercado> buscarPorId(@PathVariable Long id) {
        return mercadoService.buscarPorId(id);
    }

    @PostMapping
    public Mercado salvar(@RequestBody Mercado mercado) {
        return mercadoService.salvar(mercado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        mercadoService.deletar(id);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Mercado> atualizar(@RequestBody Mercado mercado, @PathVariable Long id) {
        try {
            Mercado mercadoAtualizado = mercadoService.atualizar(mercado, id);
            return ResponseEntity.ok(mercadoAtualizado);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

package com.mercadoapp.mercado_app.controllers;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Compra;
import com.mercadoapp.mercado_app.services.CompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/compras")
public class CompraController {
    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public List<Compra> listarTodas() {
        return compraService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<Compra> buscarPorId(@PathVariable Long id) {
        return compraService.buscarPorId(id);
    }

    @PostMapping
    public Compra salvar(@RequestBody Compra compra) {
        return compraService.salvar(compra);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        compraService.deletar(id);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Compra> atualizar(@RequestBody Compra compra, @PathVariable Long id) {
        try {
            Compra compraAtualizada = compraService.atualizar(compra, id);
            return ResponseEntity.ok(compraAtualizada);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

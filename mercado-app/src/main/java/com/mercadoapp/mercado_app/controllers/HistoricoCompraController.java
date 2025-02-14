package com.mercadoapp.mercado_app.controllers;

import com.mercadoapp.mercado_app.models.HistoricoCompra;
import com.mercadoapp.mercado_app.services.HistoricoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/historico-compra")
public class HistoricoCompraController {

    @Autowired
    private HistoricoCompraService historicoCompraService;

    @PostMapping
    public ResponseEntity<HistoricoCompra> salvar(@RequestBody HistoricoCompra historicoCompra) {
        return new ResponseEntity<>(historicoCompraService.salvar(historicoCompra), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoCompra> buscarPorId(@PathVariable Long id) {
        Optional<HistoricoCompra> historicoCompra = historicoCompraService.buscarPorId(id);
        return historicoCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<HistoricoCompra> listarTodos() {
        return historicoCompraService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        historicoCompraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

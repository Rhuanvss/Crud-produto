package com.example.projeto_springboot.controller;

import com.example.projeto_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.projeto_springboot.dto.ProdutoDTO;
import com.example.projeto_springboot.model.Produto;
import com.example.projeto_springboot.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoDTO>> listarProduto() {
        List<ProdutoDTO> produtos = produtoService.listarTodos().stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        Produto salvo = produtoService.salvarProduto(produto);
        return ResponseEntity.ok(toDTO(salvo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto == null) {
            throw new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado");
        }
        return ResponseEntity.ok(toDTO(produto));
    }

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    private Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.id());
        produto.setNome(produtoDTO.nome());
        produto.setPreco(produtoDTO.preco());
        return produto;
    }
}

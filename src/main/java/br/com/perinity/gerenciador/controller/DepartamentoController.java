package br.com.perinity.gerenciador.controller;

import br.com.perinity.gerenciador.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarDepartamentosComQuantidade() {
        List<Map<String, Object>> resultado = pessoaService.listarDepartamentosComQuantidade();
        return ResponseEntity.ok(resultado);
    }
}


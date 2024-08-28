package br.com.perinity.gerenciador.controller;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Adicionar uma pessoa
    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.adicionarPessoa(pessoa);
        return ResponseEntity.ok(novaPessoa);
    }

    // Alterar uma pessoa
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> pessoaAtualizada = pessoaService.alterarPessoa(id, pessoa);
        return pessoaAtualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Remover uma pessoa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPessoa(@PathVariable Long id) {
        pessoaService.removerPessoa(id);
        return ResponseEntity.noContent().build();
    }

    // Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
    @GetMapping
    public ResponseEntity<List<Object[]>> listarPessoasComTotalHoras() {
        List<Object[]> pessoas = pessoaService.listarPessoasComTotalHoras();
        return ResponseEntity.ok(pessoas);
    }

    // Buscar pessoas por nome e período, retorna média de horas gastas por tarefa
    @GetMapping("/indice")
    public ResponseEntity<List<Map<String, Object>>> buscarMediaHorasPorPessoa(
            @RequestParam String nome,
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        List<Map<String, Object>> resultado = pessoaService.buscarMediaHorasPorPessoa(nome, inicio, fim);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Map<String, Object>>> listarTodasPessoas() {
        List<Map<String, Object>> pessoas = pessoaService.listarTodasPessoas();
        return ResponseEntity.ok(pessoas);
    }

//    @GetMapping("/departamentos")
//    public ResponseEntity<List<Map<String, Object>>> listarDepartamentosComQuantidade() {
//        List<Map<String, Object>> resultado = pessoaService.listarDepartamentosComQuantidade();
//        return ResponseEntity.ok(resultado);
//    }

}

package br.com.perinity.gerenciador.controller;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.model.Tarefa;
import br.com.perinity.gerenciador.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    // Adicionar uma tarefa
    @PostMapping
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.adicionarTarefa(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    // Alocar uma pessoa na tarefa que tenha o mesmo departamento
    @PutMapping("/alocar/{id}")
    public ResponseEntity<Tarefa> alocarPessoaNaTarefa(
            @PathVariable Long id,
            @RequestBody Pessoa pessoa) {
        Tarefa tarefa = tarefaService.alocarPessoaNaTarefa(id, pessoa);
        return ResponseEntity.ok(tarefa);
    }

    // Finalizar a tarefa
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.finalizarTarefa(id);
        return ResponseEntity.ok(tarefa);
    }

    // Listar 3 tarefas que estejam sem pessoa alocada e com os prazos mais antigos
    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> listarTarefasPendentes() {
        List<Tarefa> tarefas = tarefaService.listarTarefasPendentes();
        return ResponseEntity.ok(tarefas);
    }

    // Listar tarefas
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTodasTarefas();
        return ResponseEntity.ok(tarefas);
    }

}

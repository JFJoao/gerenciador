package br.com.perinity.gerenciador.service;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.model.Tarefa;
import br.com.perinity.gerenciador.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;


    // Alocar uma pessoa na tarefa
    public Tarefa alocarPessoaNaTarefa(Long tarefaId, Pessoa pessoa) {
        // Busca a tarefa pelo ID
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        // Verifica se o departamento da pessoa é o mesmo da tarefa
        if (!tarefa.getDepartamento().equals(pessoa.getDepartamento())) {
            throw new IllegalArgumentException("A pessoa e a tarefa devem pertencer ao mesmo departamento");
        }

        tarefa.setPessoaAlocada(pessoa);
        return tarefaRepository.save(tarefa);
    }

    // Finalizar a tarefa
    public Tarefa finalizarTarefa(Long tarefaId) {
        // Busca a tarefa pelo ID
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        tarefa.setFinalizado(true);
        return tarefaRepository.save(tarefa);
    }

    // Listar 3 tarefas que estejam sem pessoa alocada e com os prazos mais antigos
    public List<Tarefa> listarTarefasPendentes() {
        return tarefaRepository.findTop3ByPessoaAlocadaIsNullOrderByPrazoAsc();
    }

    // Adicionar uma tarefa
    public Tarefa adicionarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }
}

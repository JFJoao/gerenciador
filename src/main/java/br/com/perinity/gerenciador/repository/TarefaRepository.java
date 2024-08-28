package br.com.perinity.gerenciador.repository;

import br.com.perinity.gerenciador.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos.
    List<Tarefa> findTop3ByPessoaAlocadaIsNullOrderByPrazoAsc();

    // Listar departamento e quantidade de pessoas e tarefas
    List<Tarefa> findAllByOrderByDepartamentoAsc();

}

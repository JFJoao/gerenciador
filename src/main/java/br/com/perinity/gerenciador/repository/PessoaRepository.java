package br.com.perinity.gerenciador.repository;

import br.com.perinity.gerenciador.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // Lista info pessoas, tarefas alocadas e total de horas em tarefas.
    @Query("SELECT p.nome AS nome, p.departamento AS departamento, SUM(t.duracao) AS totalHoras " +
            "FROM Pessoa p JOIN p.tarefas t " +
            "GROUP BY p.id, p.nome, p.departamento " +
            "ORDER BY p.id ASC")
    List<Object[]> findTotalHorasGastasPorPessoa();

    @Query("SELECT p.nome AS NOME, p.departamento AS departamento, AVG(t.duracao) AS mediaHoras " +
            "FROM Pessoa p JOIN p.tarefas t " +
            "WHERE p.nome LIKE %:nome% AND t.prazo BETWEEN :inicio AND :fim " +
            "GROUP BY p.id, p.nome, p.departamento " +
            "ORDER BY p.id ASC")
    List<Object[]> findMediaHorasPorPessoaENome(String nome, LocalDate inicio, LocalDate fim);



    // Buscar pessoas por nome
    List<Pessoa> findByNomeContaining(String nome);

    // Buscar pessoas ordenadas por ID em ordem crescente
    List<Pessoa> findAllByOrderByIdAsc();

    @Query("SELECT p.departamento AS departamento, COUNT(p.id) AS totalPessoas, " +
            "(SELECT COUNT(t.id) FROM Tarefa t WHERE t.departamento = p.departamento) AS totalTarefas " +
            "FROM Pessoa p " +
            "GROUP BY p.departamento " +
            "ORDER BY p.departamento")
    List<Object[]> findDepartamentoPessoaTarefaCount();

}


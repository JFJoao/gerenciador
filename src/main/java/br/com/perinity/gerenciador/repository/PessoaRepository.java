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
    @Query("SELECT p.nome, p.departamento, SUM(t.duracao) as totalHoras " +
            "FROM Pessoa p JOIN p.tarefas t " +
            "GROUP BY p.id, p.nome, p.departamento " +
            "ORDER BY p.nome ASC")
    List<Object[]> findTotalHorasGastasPorPessoa();

    // Lista pessoas por nome e periodo + media de horas/tarefa
    @Query("SELECT p.nome, AVG(t.duracao) as mediaHoras " +
            "FROM Pessoa p JOIN p.tarefas t " +
            "WHERE p.nome LIKE %:nome% AND t.prazo BETWEEN :inicio AND :fim " +
            "GROUP BY p.id, p.nome " +
            "ORDER BY p.nome ASC")
    List<Object[]> findMediaHorasPorPessoaENome(String nome, LocalDate inicio, LocalDate fim);

    // Buscar pessoas por nome
    List<Pessoa> findByNomeContaining(String nome);
}


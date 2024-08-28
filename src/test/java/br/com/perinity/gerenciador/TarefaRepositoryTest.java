package br.com.perinity.gerenciador;

import br.com.perinity.gerenciador.model.Tarefa;
import br.com.perinity.gerenciador.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Test
    public void testSalvarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Implementar API");
        tarefa.setDescricao("Desenvolver endpoints REST");
        tarefa.setPrazo(LocalDate.of(2024, 12, 31));
        tarefa.setDepartamento("Desenvolvimento");
        tarefa.setDuracao(10);
        tarefa.setFinalizado(false);

        Tarefa tarefaSalva = tarefaRepository.save(tarefa);

        assertNotNull(tarefaSalva.getId());
        assertEquals("Implementar API", tarefaSalva.getTitulo());
    }

    @Test
    public void testFindTop3ByPessoaAlocadaIsNullOrderByPrazoAsc() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTitulo("Tarefa 1");
        tarefa1.setDescricao("Descrição da tarefa 1");
        tarefa1.setPrazo(LocalDate.of(2024, 10, 1));
        tarefa1.setDepartamento("Desenvolvimento");
        tarefa1.setDuracao(8);
        tarefa1.setFinalizado(false);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTitulo("Tarefa 2");
        tarefa2.setDescricao("Descrição da tarefa 2");
        tarefa2.setPrazo(LocalDate.of(2024, 9, 1));
        tarefa2.setDepartamento("Desenvolvimento");
        tarefa2.setDuracao(8);
        tarefa2.setFinalizado(false);

        Tarefa tarefa3 = new Tarefa();
        tarefa3.setTitulo("Tarefa 3");
        tarefa3.setDescricao("Descrição da tarefa 3");
        tarefa3.setPrazo(LocalDate.of(2024, 8, 1));
        tarefa3.setDepartamento("Desenvolvimento");
        tarefa3.setDuracao(8);
        tarefa3.setFinalizado(false);

        tarefaRepository.save(tarefa1);
        tarefaRepository.save(tarefa2);
        tarefaRepository.save(tarefa3);

        List<Tarefa> tarefasPendentes = tarefaRepository.findTop3ByPessoaAlocadaIsNullOrderByPrazoAsc();
        assertEquals(3, tarefasPendentes.size());
        assertEquals("Tarefa 3", tarefasPendentes.get(0).getTitulo());
    }

    @Test
    public void testDeleteById() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Remover Tarefa");
        tarefa.setDescricao("Descrição da tarefa a ser removida");
        tarefa.setPrazo(LocalDate.of(2024, 11, 30));
        tarefa.setDepartamento("Desenvolvimento");
        tarefa.setDuracao(10);
        tarefa.setFinalizado(false);
        tarefa = tarefaRepository.save(tarefa);

        tarefaRepository.deleteById(tarefa.getId());

        assertFalse(tarefaRepository.findById(tarefa.getId()).isPresent());
    }
}

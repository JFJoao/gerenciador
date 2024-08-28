package br.com.perinity.gerenciador;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.model.Tarefa;
import br.com.perinity.gerenciador.repository.PessoaRepository;
import br.com.perinity.gerenciador.repository.TarefaRepository;
import br.com.perinity.gerenciador.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(TarefaService.class) // Importa o TarefaService para o contexto do teste
public class TarefaServiceTest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TarefaService tarefaService;

    @Test
    public void testAdicionarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Implementar API");
        tarefa.setDescricao("Desenvolver endpoints REST");
        tarefa.setPrazo(LocalDate.of(2024, 12, 31));
        tarefa.setDepartamento("TI");
        tarefa.setDuracao(10);
        tarefa.setFinalizado(false);

        Tarefa novaTarefa = tarefaService.adicionarTarefa(tarefa);

        assertNotNull(novaTarefa.getId());
        assertEquals("Implementar API", novaTarefa.getTitulo());
    }

    @Test
    public void testAlocarPessoaNaTarefa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Pereira");
        pessoa.setDepartamento("TI");
        pessoa = pessoaRepository.save(pessoa);

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Configurar Servidor");
        tarefa.setDescricao("Configurar o novo servidor");
        tarefa.setPrazo(LocalDate.of(2024, 10, 20));
        tarefa.setDepartamento("TI");
        tarefa.setDuracao(8);
        tarefa.setFinalizado(false);
        tarefa = tarefaRepository.save(tarefa);

        Tarefa tarefaAtualizada = tarefaService.alocarPessoaNaTarefa(tarefa.getId(), pessoa);

        assertNotNull(tarefaAtualizada.getPessoaAlocada());
        assertEquals(pessoa.getId(), tarefaAtualizada.getPessoaAlocada().getId());
    }

    @Test
    public void testFinalizarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Finalizar Relatório");
        tarefa.setDescricao("Finalizar o relatório anual");
        tarefa.setPrazo(LocalDate.of(2024, 11, 15));
        tarefa.setDepartamento("Financeiro");
        tarefa.setDuracao(5);
        tarefa.setFinalizado(false);
        tarefa = tarefaRepository.save(tarefa);

        Tarefa tarefaFinalizada = tarefaService.finalizarTarefa(tarefa.getId());

        assertTrue(tarefaFinalizada.getFinalizado());
    }

    @Test
    public void testListarTarefasPendentes() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTitulo("Tarefa 1");
        tarefa1.setDescricao("Descrição da tarefa 1");
        tarefa1.setPrazo(LocalDate.of(2024, 10, 1));
        tarefa1.setDepartamento("TI");
        tarefa1.setDuracao(8);
        tarefa1.setFinalizado(false);
        tarefaRepository.save(tarefa1);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTitulo("Tarefa 2");
        tarefa2.setDescricao("Descrição da tarefa 2");
        tarefa2.setPrazo(LocalDate.of(2024, 9, 1));
        tarefa2.setDepartamento("TI");
        tarefa2.setDuracao(8);
        tarefa2.setFinalizado(false);
        tarefaRepository.save(tarefa2);

        Tarefa tarefa3 = new Tarefa();
        tarefa3.setTitulo("Tarefa 3");
        tarefa3.setDescricao("Descrição da tarefa 3");
        tarefa3.setPrazo(LocalDate.of(2024, 8, 1));
        tarefa3.setDepartamento("TI");
        tarefa3.setDuracao(8);
        tarefa3.setFinalizado(false);
        tarefaRepository.save(tarefa3);

        List<Tarefa> tarefasPendentes = tarefaService.listarTarefasPendentes();

        assertEquals(3, tarefasPendentes.size());
        assertEquals("Tarefa 3", tarefasPendentes.get(0).getTitulo());
    }
}

package br.com.perinity.gerenciador.service;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
    public List<Object[]> listarPessoasComTotalHoras() {
        return pessoaRepository.findTotalHorasGastasPorPessoa();
    }

    // Buscar pessoas por nome e período, retorna média de horas gastas por tarefa
    public List<Map<String, Object>> buscarMediaHorasPorPessoa(String nome, LocalDate inicio, LocalDate fim) {
        List<Object[]> resultados = pessoaRepository.findMediaHorasPorPessoaENome(nome, inicio, fim);
        return resultados.stream().map(record -> Map.of(
                "nome", record[0],
                "departamento", record[1],
                "mediaHoras", record[2]
        )).collect(Collectors.toList());
    }

    // Adicionar uma pessoa
    public Pessoa adicionarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
    // Remover uma pessoa
    public void removerPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    // Alterar uma pessoa
    public Optional<Pessoa> alterarPessoa(Long id, Pessoa pessoa) {
        return pessoaRepository.findById(id).map(pessoaExistente -> {
            pessoaExistente.setNome(pessoa.getNome());
            pessoaExistente.setDepartamento(pessoa.getDepartamento());
            return pessoaRepository.save(pessoaExistente);
        });
    }

    // Método para listar todas as pessoas, departamento e horas gastas
    public List<Map<String, Object>> listarTodasPessoas() {
        List<Object[]> resultadosPessoas = pessoaRepository.findTotalHorasGastasPorPessoa();
        return resultadosPessoas.stream().map(record -> Map.of(
                "nome", record[0],
                "departamento", record[1],
                "horas gastas", record[2]
        )).collect(Collectors.toList());
    }

    // Método para listar departamentos com a quantidade de pessoas e tarefasq
    public List<Map<String, Object>> listarDepartamentosComQuantidade() {
        List<Object[]> resultados = pessoaRepository.findDepartamentoPessoaTarefaCount();
        return resultados.stream().map(record -> Map.of(
                "departamento", record[0],
                "totalPessoas", record[1],
                "totalTarefas", record[2]
        )).collect(Collectors.toList());
    }

}

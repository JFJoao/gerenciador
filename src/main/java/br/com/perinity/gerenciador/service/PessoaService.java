package br.com.perinity.gerenciador.service;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
    public List<Object[]> listarPessoasComTotalHoras() {
        return pessoaRepository.findTotalHorasGastasPorPessoa();
    }

    // Buscar pessoas por nome e período, retorna média de horas gastas por tarefa
    public List<Object[]> buscarMediaHorasPorPessoa(String nome, LocalDate inicio, LocalDate fim) {
        return pessoaRepository.findMediaHorasPorPessoaENome(nome, inicio, fim);
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



}

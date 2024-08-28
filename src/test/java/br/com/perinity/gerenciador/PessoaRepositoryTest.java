package br.com.perinity.gerenciador;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testSalvarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Pereira");
        pessoa.setDepartamento("TI");

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        assertNotNull(pessoaSalva.getId());
        assertEquals("João Pereira", pessoaSalva.getNome());
    }

    @Test
    public void testFindByNomeContaining() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Ana Paula");
        pessoa.setDepartamento("Financeiro");
        pessoaRepository.save(pessoa);

        List<Pessoa> pessoas = pessoaRepository.findByNomeContaining("Ana");
        assertFalse(pessoas.isEmpty());
        assertEquals("Ana Paula", pessoas.get(0).getNome());
    }

    @Test
    public void testDeleteById() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carlos Souza");
        pessoa.setDepartamento("RH");
        pessoa = pessoaRepository.save(pessoa);

        pessoaRepository.deleteById(pessoa.getId());

        assertFalse(pessoaRepository.findById(pessoa.getId()).isPresent());
    }
}

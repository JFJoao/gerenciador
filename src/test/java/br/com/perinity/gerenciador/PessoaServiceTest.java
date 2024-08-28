package br.com.perinity.gerenciador;

import br.com.perinity.gerenciador.model.Pessoa;
import br.com.perinity.gerenciador.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PessoaServiceTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testAdicionarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Pereira");
        pessoa.setDepartamento("TI");

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        assertThat(pessoaSalva).isNotNull();
        assertThat(pessoaSalva.getId()).isNotNull();
        assertThat(pessoaSalva.getNome()).isEqualTo("João Pereira");
    }

    @Test
    public void testAlterarDadosPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Maria Santos");
        pessoa.setDepartamento("RH");
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        pessoaSalva.setNome("Maria Aparecida Santos");
        pessoaSalva.setDepartamento("Marketing");
        Pessoa pessoaAtualizada = pessoaRepository.save(pessoaSalva);

        Optional<Pessoa> pessoaBuscada = pessoaRepository.findById(pessoaAtualizada.getId());

        assertThat(pessoaBuscada).isPresent();
        assertThat(pessoaBuscada.get().getNome()).isEqualTo("Maria Aparecida Santos");
        assertThat(pessoaBuscada.get().getDepartamento()).isEqualTo("Marketing");
    }

    @Test
    public void testRemoverPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carlos Alberto");
        pessoa.setDepartamento("Financeiro");
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        pessoaRepository.deleteById(pessoaSalva.getId());

        Optional<Pessoa> pessoaBuscada = pessoaRepository.findById(pessoaSalva.getId());

        assertThat(pessoaBuscada).isNotPresent();
    }
}

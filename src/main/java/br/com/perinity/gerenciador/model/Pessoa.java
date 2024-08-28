package br.com.perinity.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;
    @NotBlank(message = "Departamento é obrigatório")
    @Size(min = 2, max = 100, message = "Departamento deve ter entre 3 e 100 caracteres")
    private String departamento;
    @OneToMany(mappedBy = "pessoaAlocada", cascade = CascadeType.ALL)
    @JsonManagedReference // previne loops
    private List<Tarefa> tarefas;
}

package br.com.perinity.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Título é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String titulo;
    @NotBlank(message = "Descricao é obrigatório")
    @Size(min = 7, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String descricao;
    @NotNull(message = "Prazo é obrigatório")
    private LocalDate prazo;
    @NotBlank(message = "Departamento é obrigatório")
    private String departamento;
    @NotNull(message = "Duração é obrigatório")
    private Integer duracao;
    private Boolean finalizado;
    @ManyToOne
    @JoinColumn(name = "pessoa_alocada_id")
    @JsonBackReference // previne loops
    private Pessoa pessoaAlocada;


}

package br.senac.lll.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // Anotação do Lombok para criar getters, setters, etc. automaticamente
@Entity // Avisa ao JPA que esta classe é uma "entidade" que espelha uma tabela
@Table(name = "pessoas") // Liga esta classe à tabela específica "pessoas"
public class Pessoa {

    @Id // Marca o campo 'id' como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Avisa que o ID é autoincremento no banco
    private Long id; // Usamos Long por ser mais robusto que int

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 20)
    private String status;

    @Column(length = 50)
    private String grupo;
}
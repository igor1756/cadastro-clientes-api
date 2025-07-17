package com.igor.projetoclientes.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do cliente deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O CPF do cliente é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "O email do cliente é obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;
    
    @NotNull(message = "A data de nascimento do cliente é obrigatória")
    @Past(message = "A data de nascimento do cliente deve ser no passado")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    // Construtores
    // Construtor padrão para JPA
    public Cliente() {
    }

    // Construtor com todos os campos
    public Cliente(String nome, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // toString
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}

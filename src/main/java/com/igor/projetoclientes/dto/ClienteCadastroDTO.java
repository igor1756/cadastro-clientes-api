package com.igor.projetoclientes.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para cadastro de cliente")
public class ClienteCadastroDTO {

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do cliente deve ter entre 3 e 100 caracteres")
    @Schema(description = "Nome completo do cliente", example = "João da Silva")
    private String nome;

    @NotBlank(message = "O CPF do cliente é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    @Schema(description = "CPF do cliente", example = "12345678901")
    private String cpf;

    @NotBlank(message = "O email do cliente é obrigatório")
    @Email(message = "Email inválido")
    @Schema(description = "Email do cliente", example = "joao@example.com")
    private String email;

    @NotNull(message = "A data de nascimento do cliente é obrigatória")
    @Schema(description = "Data de nascimento do cliente no formato YYYY-MM-DD", example = "1990-01-01")
    @Past(message = "A data de nascimento do cliente deve ser no passado")
    private LocalDate dataNascimento;

    // Construtores
    // Construtor padrão para JPA
    public ClienteCadastroDTO() {
    }

    // getters e setters
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
}

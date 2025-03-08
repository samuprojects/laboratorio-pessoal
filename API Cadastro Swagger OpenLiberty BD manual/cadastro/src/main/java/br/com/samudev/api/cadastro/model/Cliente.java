package br.com.samudev.api.cadastro.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Modelo de Cliente")
public class Cliente {

    @Schema(description = "ID do cliente", example = "1")
    private Long id;

    @Schema(description = "Nome do cliente", example = "João da Silva")
    private String nome;

    @Schema(description = "Data de nascimento do cliente", example = "1990-01-01")
    private LocalDate dataNascimento;

    @Schema(description = "Email do cliente", example = "joao.silva@email.com")
    private String email;

    @Schema(description = "Telefone do cliente", example = "11-99999-9999")
    private String telefone;

    public Cliente() {
    }

    public Cliente(Long id, String nome, LocalDate dataNascimento, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
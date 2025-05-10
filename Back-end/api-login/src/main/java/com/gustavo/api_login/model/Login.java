package com.gustavo.api_login.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Schema(description = "Representa os dados de login de um usuário")
public class Login {
    @Id
    @Schema(description = "ID do login", example = "1")
    private String id;
    @Schema(description = "Email do usuário", example = "Agivaldo@email.com")
    private String email;
    @Schema(description = "Senha do usuário", example = "12345678")
    private String senha;

    public Login(String id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

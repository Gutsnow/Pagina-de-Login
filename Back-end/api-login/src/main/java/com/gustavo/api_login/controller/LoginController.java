package com.gustavo.api_login.controller;
import com.gustavo.api_login.model.Login;
import com.gustavo.api_login.repository.LoginRepository;
import com.gustavo.api_login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class LoginController{

    private LoginService loginService;

    public LoginController (LoginService loginService) {
        this.loginService = loginService;
    }
    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados no sistema.")
    public List encontrar() {
        return loginService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procura um usuário por ID", description = "Retorna um usário cadastrado no sistema pelo ID.")
    public Optional<Login> encontrarPorId(@Parameter(description = "ID do usuário", required = true, example = "1", name = "id") @PathVariable String id) {
        return loginService.listarPorId(id);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Procura um usuário pelo email", description = "Retorna um usário cadastrado no sistema pelo email.",parameters = {@Parameter(example = "Agivaldo@email.com", name = "email")})
    public Optional<Login> encontrarPorEmail(@PathVariable String email){
       return loginService.encontrarPorEmails(email);
    }


    @PostMapping
    @Operation(summary = "Adiciona um Login manualmente.", description = "adiciona um usuário manualmente no banco de dados ")
    public Login adiciona (@RequestBody Login login1) {
        loginService.salvar(login1);
        return login1;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login")
    @Operation(summary = "Autentica o Login do Usuário", description = "é o caminho da API que recebe as requisições feitas no front-end")
    public ResponseEntity<?> fazerLogin(@RequestBody Login login) {
        return loginService.autenticar(login);
    }


    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login/save")
    @Operation(summary = "Salvar um Login no Banco de dados", description = "É o caminho que a requisição feita no front-end para criar um Login no banco de dados")
    public ResponseEntity<?> salva(@RequestBody Login login){
        return loginService.salvar(login);
    }

    @DeleteMapping
    @Operation(summary = "Deleta todos os usuarios manualmente", description = "é o comando que serve para limpar o banco de dados.")
    public void deletar(){
        loginService.deletarTodos();
    }
}

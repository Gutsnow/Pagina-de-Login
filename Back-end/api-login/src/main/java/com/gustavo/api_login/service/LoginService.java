package com.gustavo.api_login.service;

import com.gustavo.api_login.model.Login;
import com.gustavo.api_login.repository.LoginRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public List<Login> listarTodos() {
        return loginRepository.findAll();
    }

    public Optional<Login> listarPorId(String id) {
        return loginRepository.findById(id);
    }

    public ResponseEntity<?> salvar(Login login) {
        if (procurarEmail(login.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("o email já está cadastrado!");
        }
        else{
            loginRepository.save(login);
            return ResponseEntity.ok("cadastrado com sucesso!");
        }
    }

    public Optional<Login> buscarPorEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    public void deletarTodos() {
        loginRepository.deleteAll();
    }

    public ResponseEntity<?> autenticar(Login login) {
        Optional<Login> loginOptional = loginRepository.findByEmail(login.getEmail());
        if (loginOptional.isPresent()) {
            Login loginEncontrado = loginOptional.get();
            if (loginEncontrado.getSenha().equals(login.getSenha())) {
                return ResponseEntity.ok("logado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("senha incorreta!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email não encontrado!");
        }
    }

    public boolean procurarEmail(String email) {
        if (loginRepository.findByEmail(email).isPresent()) return true;
        else return false;
    }

    public Optional<Login> encontrarPorEmails(String email){
       return loginRepository.findByEmail(email);
    }
}

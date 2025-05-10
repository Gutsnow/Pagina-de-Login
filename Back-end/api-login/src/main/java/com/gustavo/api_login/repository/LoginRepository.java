package com.gustavo.api_login.repository;

import com.gustavo.api_login.model.Login;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.Optional;

public interface LoginRepository extends MongoRepository<Login,String> {

    Optional<Login> findByEmail(String email);
}

package com.survey.repository;

import com.survey.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // JpaRepository< [Nome Entità (classe riferita alla tabella)], [Tipo della variabile Id]>

    Optional<User> findByMail(String mail); // oggetto Optional può trovarsi in stato null o contenere qualcosa

    Page<User> findAll(Pageable pageable);
}

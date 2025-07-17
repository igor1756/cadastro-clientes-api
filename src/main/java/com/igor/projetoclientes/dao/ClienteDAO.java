package com.igor.projetoclientes.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.projetoclientes.model.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByEmail(String email);
}

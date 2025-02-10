package com.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    // Custom query method to find a client by name
    Client findByName(String name);
    Client findByNameAndPassword(String name, String password);
}
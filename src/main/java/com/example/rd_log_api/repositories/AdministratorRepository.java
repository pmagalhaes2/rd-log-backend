package com.example.rd_log_api.repositories;

import com.example.rd_log_api.domain.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findByEmail(String email);

}
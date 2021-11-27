package com.example.demo.repositories;

import com.example.demo.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
    Iterable<CIModule> findAllByName(String name);
}

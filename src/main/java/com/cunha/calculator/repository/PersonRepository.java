package com.cunha.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cunha.calculator.model.Pessoa;

@Repository
public interface PersonRepository extends JpaRepository<Pessoa, Long> {

}

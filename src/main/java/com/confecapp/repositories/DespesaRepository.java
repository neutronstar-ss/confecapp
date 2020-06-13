package com.confecapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.confecapp.models.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}

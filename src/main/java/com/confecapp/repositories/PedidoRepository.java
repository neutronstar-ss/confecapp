package com.confecapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.confecapp.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

package com.confecapp.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.confecapp.models.Pedido;

public interface PedidoService {
	
	List < Pedido > getAllPedidos();
    void savePedido(Pedido pedido);
    Pedido getPedidoById(Long id);
    void deletePedidoById(Long id);
    Page < Pedido > findPedidoPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}

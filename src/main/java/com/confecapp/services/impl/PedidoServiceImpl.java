package com.confecapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.confecapp.models.Pedido;
import com.confecapp.repositories.PedidoRepository;
import com.confecapp.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	  @Autowired
	    private PedidoRepository pedidoRepository;

	    @Override
	    public List < Pedido > getAllPedidos() {
	        return pedidoRepository.findAll();
	    }

	    @Override
	    public void savePedido(Pedido pedido) {
	        this.pedidoRepository.save(pedido);
	    }

	    @Override
	    public Pedido getPedidoById(Long id) {
	        Optional < Pedido > optional = pedidoRepository.findById(id);
	        Pedido pedido = null;
	        if (optional.isPresent()) {
	            pedido = optional.get();
	        } else {
	            throw new RuntimeException(" Pedido não encontrado id :: " + id);
	        }
	        return pedido;
	    }

	    @Override
	    public void deletePedidoById(Long id) {
	        this.pedidoRepository.deleteById(id);
	    }

	    @Override
	    public Page < Pedido > findPedidoPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
	        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
	            Sort.by(sortField).descending();

	        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	        return this.pedidoRepository.findAll(pageable);
	    }

}

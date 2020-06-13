package com.confecapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.confecapp.models.Despesa;
import com.confecapp.repositories.DespesaRepository;
import com.confecapp.services.DespesaService;

@Service
public class DespesaServiceImpl implements DespesaService {
	
	  @Autowired
	    private DespesaRepository despesaRepository;

	    @Override
	    public List < Despesa > getAllDespesas() {
	        return despesaRepository.findAll();
	    }

	    @Override
	    public void saveDespesa(Despesa despesa) {
	        this.despesaRepository.save(despesa);
	    }

	    @Override
	    public Despesa getDespesaById(Long id) {
	        Optional < Despesa > optional = despesaRepository.findById(id);
	        Despesa despesa = null;
	        if (optional.isPresent()) {
	            despesa = optional.get();
	        } else {
	            throw new RuntimeException(" Despesa não encontrada id :: " + id);
	        }
	        return despesa;
	    }

	    @Override
	    public void deleteDespesaById(Long id) {
	        this.despesaRepository.deleteById(id);
	    }

	    @Override
	    public Page < Despesa > findDespesaPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
	        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
	            Sort.by(sortField).descending();

	        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	        return this.despesaRepository.findAll(pageable);
	    }

}

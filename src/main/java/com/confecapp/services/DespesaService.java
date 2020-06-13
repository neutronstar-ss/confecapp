package com.confecapp.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.confecapp.models.Despesa;

public interface DespesaService {
	
	List < Despesa > getAllDespesas();
    void saveDespesa(Despesa despesa);
    Despesa getDespesaById(Long id);
    void deleteDespesaById(Long id);
    Page < Despesa > findDespesaPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}

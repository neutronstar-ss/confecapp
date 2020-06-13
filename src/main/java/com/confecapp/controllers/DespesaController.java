package com.confecapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.confecapp.models.Despesa;
import com.confecapp.services.DespesaService;

@Controller
public class DespesaController {
	
	@Autowired
    private DespesaService despesaService;

    @GetMapping("/despesas")
    public String viewHomePage(Model model) {
        return findDespesaPaginated(1, "data", "asc", model);
    }

    @GetMapping("/showNewDespesaForm")
    public String showNewDespesaForm(Model model) {
        // create model attribute to bind form data
        Despesa despesa = new Despesa();
        model.addAttribute("despesa", despesa);
        return "nova_despesa";
    }

    @PostMapping("/saveDespesa")
    public String saveDespesa(@ModelAttribute("despesa") Despesa despesa) {
        despesaService.saveDespesa(despesa);
        return "redirect:/despesas";
    }

    @GetMapping("/showDespesaFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {

        Despesa despesa = despesaService.getDespesaById(id);

        model.addAttribute("despesa", despesa);
        return "update_despesa";
    }

    @GetMapping("/deleteDespesa/{id}")
    public String deleteDespesa(@PathVariable(value = "id") Long id) {

        this.despesaService.deleteDespesaById(id);
        return "redirect:/despesas";
    }


    @GetMapping("/pagedespesa/{pageNo}")
    public String findDespesaPaginated(@PathVariable(value = "pageNo") int pageNo,
        @RequestParam("sortField") String sortField,
        @RequestParam("sortDir") String sortDir,
        Model model) {
        int pageSize = 5;

        Page < Despesa > page = despesaService.findDespesaPaginated(pageNo, pageSize, sortField, sortDir);
        List < Despesa > listDespesas = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listDespesas", listDespesas);
        return "despesas";
    }

}

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

import com.confecapp.models.Pedido;
import com.confecapp.services.PedidoService;

@Controller
public class PedidoController {
	
	@Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedidos")
    public String viewHomePage(Model model) {
        return findPedidoPaginated(1, "cliente", "asc", model);
    }

    @GetMapping("/showNewPedidoForm")
    public String showNewPedidoForm(Model model) {
        // create model attribute to bind form data
        Pedido pedido = new Pedido();
        model.addAttribute("pedido", pedido);
        return "novo_pedido";
    }

    @PostMapping("/savePedido")
    public String savePedido(@ModelAttribute("pedido") Pedido pedido) {
        pedidoService.savePedido(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/showPedidoFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {

        Pedido pedido = pedidoService.getPedidoById(id);

        model.addAttribute("pedido", pedido);
        return "update_pedido";
    }

    @GetMapping("/deletePedido/{id}")
    public String deletePedido(@PathVariable(value = "id") Long id) {

        this.pedidoService.deletePedidoById(id);
        return "redirect:/pedidos";
    }


    @GetMapping("/pagepedido/{pageNo}")
    public String findPedidoPaginated(@PathVariable(value = "pageNo") int pageNo,
        @RequestParam("sortField") String sortField,
        @RequestParam("sortDir") String sortDir,
        Model model) {
        int pageSize = 5;

        Page < Pedido > page = pedidoService.findPedidoPaginated(pageNo, pageSize, sortField, sortDir);
        List < Pedido > listPedidos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPedidos", listPedidos);
        return "pedidos";
    }

}

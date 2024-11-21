package com.bd.sitebd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bd.sitebd.model.Reserva;
import com.bd.sitebd.model.ReservaService;
import com.bd.sitebd.model.Tool;

@Controller
public class CadastroController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/") 
    public String Principal(Model model){
        model.addAttribute("activePage", "principal");
        return "principal"; 
    }

    @GetMapping("/atualizar/{id}") 
    public String atualizar(Model model, @PathVariable int id) {
        ReservaService cs = context.getBean(ReservaService.class);
        Reserva res = cs.obterReserva(id);
        model.addAttribute("id", id);
        model.addAttribute("reserva", res);  // Defina uma chave válida
        return "atualizar";  // Retorna para a página "atualizar"
    }


    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable int id, @ModelAttribute Reserva res){
        ReservaService cs = context.getBean(ReservaService.class);
        cs.atualizarReserva(id, res);
        return "redirect:/listagem";
    }



    @GetMapping("/reservar")
    public String reserva(@RequestParam("numero") String numero, Model model) {
        // Criando o objeto Reserva com o número da sala e passando para o modelo
        model.addAttribute("reserva", new Reserva(numero, "", null, null, 0));
        model.addAttribute("activePage", "reservar");
        return "reservar";
}

    @PostMapping("/reservar")
    public String cadastrar(@ModelAttribute Reserva res) {
        ReservaService cs = context.getBean(ReservaService.class);
        cs.inserir(res);
        return "sucesso";  // Página de sucesso após a reserva
    }
    // @PostMapping("/reservar")
    // public String cadastrar(Model model, @ModelAttribute Reserva res){
    //     ReservaService cs = context.getBean(ReservaService.class);
    //     cs.inserir(res);
    //     return "sucesso";
    // }

    @GetMapping("/listagem")
    public String listagem(Model model){
        ReservaService cs = context.getBean(ReservaService.class);
        List<Map<String,Object>> lista = cs.obterTodasReservas();
        List<Reserva> listaReservas = new ArrayList<Reserva>();
        for(Map<String,Object> registro : lista){
            listaReservas.add(Tool.converterReserva(registro));
        }
        model.addAttribute("reservas", listaReservas);
        model.addAttribute("activePage", "listagem");
        return "listagem";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable int id){
        ReservaService cs = context.getBean(ReservaService.class);
        cs.deletarReserva(id);
        return "redirect:/listagem";
    }

    @GetMapping("/contato") 
    public String Contato(Model model){
        model.addAttribute("activePage", "contato");
        return "contato"; 
    }

}
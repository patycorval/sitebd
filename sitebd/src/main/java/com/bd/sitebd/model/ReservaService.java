package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    // CONEXAO É O SERVICE, OU SEJA, O FRONT SO ENXERGA AQUI, ENQUANTO O DAO É ESCONDIDO (A VIEW NAO O VÊ)
    
    @Autowired
    ReservaDAO rdao;

    public void inserir(Reserva res){
        rdao.inserir(res);
    }

    public List<Map<String,Object>> obterTodasReservas(){
        return rdao.obterTodasReservas();
    }

    public void atualizarReserva(int id, Reserva res){
        rdao.atualizarCliente(id, res);
    }

    public Reserva obterReserva(int id){
        return rdao.obterReserva(id);
    }

    public void deletarReserva(int id){
        rdao.deletarReserva(id);
    }
}

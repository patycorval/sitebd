package com.bd.sitebd.model;


import java.util.Map;

public class Tool {

    public static Reserva converterReserva(Map<String, Object> registro) {
        // Como registro.get retorna Object, devemos usar o polimorfismo
        // de subtipos (downcast) para recuperar os tipos originais.
        return new Reserva((Integer) registro.get("id"), 
                        (String) registro.get("numero"), 
                        (String) registro.get("nome"),
                        ((java.sql.Date) registro.get("data")).toLocalDate(), // 
                        ((java.sql.Time) registro.get("hora")).toLocalTime(), // Conversão direta para LocalTime
                        (Integer) registro.get("duracao"));
    }
}

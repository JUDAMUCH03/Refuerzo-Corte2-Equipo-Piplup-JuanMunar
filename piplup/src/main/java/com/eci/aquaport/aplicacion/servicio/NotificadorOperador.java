package com.eci.aquaport.aplicacion.servicio;

public class NotificadorOperador {

    public void informar(String mensaje) {
        if (mensaje == null || mensaje.isBlank()) {
            throw new IllegalArgumentException("El mensaje del operador no puede ser nulo ni vacío.");
        }
        System.out.println("[OPERADOR] " + mensaje);
    }

    public void alertar(String mensaje) {
        if (mensaje == null || mensaje.isBlank()) {
            throw new IllegalArgumentException("La alerta del operador no puede ser nula ni vacía.");
        }
        System.err.println("[ALERTA OPERADOR] " + mensaje);
    }
}

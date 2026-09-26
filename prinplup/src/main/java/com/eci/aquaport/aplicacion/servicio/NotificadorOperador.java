package com.eci.aquaport.aplicacion.servicio;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificadorOperador {

    private static final Logger LOGGER = Logger.getLogger(NotificadorOperador.class.getName());

    public void notificarAsignacion(String mensaje) {
        LOGGER.log(Level.INFO, () -> "[NOTIFICACIÓN OPERADOR] " + mensaje);
    }

    public void notificarError(String mensajeError) {
        LOGGER.log(Level.WARNING, () -> "[ALERTA OPERADOR] " + mensajeError);
    }
}
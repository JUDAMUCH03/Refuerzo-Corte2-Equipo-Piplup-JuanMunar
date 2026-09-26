package com.eci.aquaport.aplicacion.observador;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.Mision;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TecnicoMantenimientoObserver implements ObservadorMision {

    private static final Logger LOGGER = Logger.getLogger(TecnicoMantenimientoObserver.class.getName());

    @Override
    public void notificarFallo(DroneAcuatico drone) {
        LOGGER.log(Level.SEVERE, () -> "[TECNICO] Orden de recuperacion inmediata para drone " + drone.id());
    }

    @Override
    public void notificarFalloAsignacion(Mision mision) {
        LOGGER.log(Level.INFO, () -> "[TECNICO] Mision en cola pendiente por flota disponible: " + mision.id());
    }
}
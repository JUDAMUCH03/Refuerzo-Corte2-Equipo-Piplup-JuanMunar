package com.eci.aquaport.aplicacion.observador;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.Mision;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CentroControlObserver implements ObservadorMision {

    private static final Logger LOGGER = Logger.getLogger(CentroControlObserver.class.getName());

    @Override
    public void notificarFallo(DroneAcuatico drone) {
        LOGGER.log(Level.WARNING, () -> "[CENTRO CONTROL] Alerta critica: Drone " + drone.id() + " entro en FALLO en zona " + drone.zona());
    }

    @Override
    public void notificarFalloAsignacion(Mision mision) {
        LOGGER.log(Level.WARNING, () -> "[CENTRO CONTROL] No fue posible asignar unidad a mision: " + mision.id());
    }
}
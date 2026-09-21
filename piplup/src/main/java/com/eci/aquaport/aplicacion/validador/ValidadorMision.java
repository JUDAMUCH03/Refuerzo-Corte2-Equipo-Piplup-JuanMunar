package com.eci.aquaport.aplicacion.validador;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.Mision;

public class ValidadorMision {

    public boolean tieneBateriaSuficiente(DroneAcuatico drone) {
        return drone != null && drone.bateria() >= 35;
    }

    public boolean puedeOperarEnZona(DroneAcuatico drone, String zona) {
        if (drone == null || zona == null || zona.isBlank()) {
            return false;
        }
        return drone.disponible() && drone.zona() != null && drone.zona().equalsIgnoreCase(zona.trim());
    }

    public void validarAsignacion(Mision mision) {
        if (mision == null) {
            throw new IllegalArgumentException("La misión no puede ser nula.");
        }

        DroneAcuatico drone = mision.getDrone();
        if (drone == null) {
            throw new IllegalStateException("La misión debe tener un drone asociado.");
        }
        if (!tieneBateriaSuficiente(drone)) {
            throw new IllegalStateException("El drone no tiene batería suficiente para asignar la misión.");
        }
        if (!puedeOperarEnZona(drone, mision.getPuntoPartida())) {
            throw new IllegalStateException("El drone no puede operar en la zona de salida especificada.");
        }
    }
}

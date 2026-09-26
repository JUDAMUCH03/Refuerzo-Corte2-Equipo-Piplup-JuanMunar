package com.eci.aquaport.aplicacion.validador;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import java.util.Set;

public class ValidadorMision {

    private static final int BATERIA_MINIMA_OPERATIVA = 35;
    
    // Zonas acuáticas habilitadas en el campus ECI
    private static final Set<String> ZONAS_PERMITIDAS = Set.of(
            "Embalse Norte",
            "Canal Central",
            "Laguna Sur",
            "Punto Ribereño Este",
            "Muelle Principal"
    );

    public boolean tieneBateriaSuficiente(DroneAcuatico drone) {
        if (drone == null) {
            return false;
        }
        return drone.bateria() >= BATERIA_MINIMA_OPERATIVA;
    }

    public boolean estaDisponible(DroneAcuatico drone) {
        if (drone == null) {
            return false;
        }
        return drone.disponible();
    }

    public void validarPuntoLlegada(String puntoLlegada) {
        if (puntoLlegada == null || puntoLlegada.isBlank()) {
            throw new IllegalArgumentException("El punto de llegada no puede ser nulo ni vacío");
        }
    }

    public void validarZona(String zona) {
        if (zona == null || !ZONAS_PERMITIDAS.contains(zona.trim())) {
            throw new IllegalArgumentException("Zona no autorizada para operaciones acuáticas: " + zona);
        }
    }
}
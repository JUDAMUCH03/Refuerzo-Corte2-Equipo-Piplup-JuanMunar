package com.eci.aquaport.dominio.fabrica;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.DroneBuceador;
import com.eci.aquaport.dominio.modelo.DroneSemisumergido;
import com.eci.aquaport.dominio.modelo.DroneSuperficial;
import com.eci.aquaport.dominio.modelo.EstadoDrone;

public class FabricaDrones {

    public DroneAcuatico crearDrone(String tipo, String id, int bateria, EstadoDrone estado, String zona) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de drone no puede ser nulo");
        }

        return switch (tipo.toUpperCase().trim()) {
            case "SUPERFICIAL" -> new DroneSuperficial(id, bateria, estado, zona);
            case "SEMISUMERGIDO" -> new DroneSemisumergido(id, bateria, estado, zona);
            case "BUCEADOR" -> new DroneBuceador(id, bateria, estado, zona);
            default -> throw new IllegalArgumentException("Tipo de drone desconocido: " + tipo);
        };
    }
}
package com.eci.aquaport.aplicacion.estrategia;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.Mision;

import java.util.List;
import java.util.Optional;

public class ZonaCercanaStrategy implements EstrategiaSeleccion {

    private final String zonaObjetivo;

    public ZonaCercanaStrategy(String zonaObjetivo) {
        this.zonaObjetivo = zonaObjetivo;
    }

    @Override
    public Optional<DroneAcuatico> seleccionar(List<DroneAcuatico> candidatos, Mision mision) {
        return candidatos.stream()
                .filter(DroneAcuatico::disponible)
                .filter(d -> d.zona().equalsIgnoreCase(zonaObjetivo))
                .findFirst();
    }
}
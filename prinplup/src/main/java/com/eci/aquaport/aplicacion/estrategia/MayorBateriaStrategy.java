package com.eci.aquaport.aplicacion.estrategia;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.Mision;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MayorBateriaStrategy implements EstrategiaSeleccion {

    @Override
    public Optional<DroneAcuatico> seleccionar(List<DroneAcuatico> candidatos, Mision mision) {
        return candidatos.stream()
                .filter(DroneAcuatico::disponible)
                .max(Comparator.comparingInt(DroneAcuatico::bateria));
    }
}
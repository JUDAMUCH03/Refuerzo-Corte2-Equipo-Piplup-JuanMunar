package com.eci.aquaport.aplicacion.estrategia;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.Mision;

import java.util.List;
import java.util.Optional;

public interface EstrategiaSeleccion {
    Optional<DroneAcuatico> seleccionar(List<DroneAcuatico> candidatos, Mision mision);
}
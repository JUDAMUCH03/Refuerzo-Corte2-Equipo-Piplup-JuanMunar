package com.eci.aquaport.aplicacion.servicio;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.EstadoDrone;
import com.eci.aquaport.dominio.modelo.Mision;
import com.eci.aquaport.dominio.modelo.Prioridad;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalizadorFlotaAvanzado {

    public Map<String, List<DroneAcuatico>> agruparDisponiblesPorTipo(List<DroneAcuatico> flota) {
        return flota.stream()
                .filter(d -> d.estado() == EstadoDrone.DISPONIBLE)
                .collect(Collectors.groupingBy(DroneAcuatico::tipo));
    }

    public Optional<DroneAcuatico> obtenerDroneOptimo(List<DroneAcuatico> flota, String zona, String tipoRequerido) {
        Optional<DroneAcuatico> optimo = flota.stream()
                .filter(d -> d.estado() == EstadoDrone.DISPONIBLE)
                .filter(d -> d.zona().equals(zona))
                .filter(d -> d.tipo().equals(tipoRequerido))
                .max(Comparator.comparing(DroneAcuatico::bateria));

        return optimo;
    }

    public Map<String, Double> calcularPromedioBateriaPorTipo(List<DroneAcuatico> flota) {
        return flota.stream()
                .collect(Collectors.groupingBy(
                        DroneAcuatico::tipo,
                        Collectors.averagingDouble(DroneAcuatico::bateria)
                ));
    }

    public Map<Boolean, List<Mision>> separarMisionesCriticas(List<Mision> misiones) {
        return misiones.stream()
                .collect(Collectors.partitioningBy(m -> m.prioridad() == Prioridad.CRITICA));
    }

    public Set<String> obtenerZonasActivas(List<DroneAcuatico> flota) {
        return flota.stream()
                .filter(d -> d.estado() == EstadoDrone.EN_MISION)
                .map(d -> d.zona())
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println("Iniciando analisis de flota v2...");
    }
}
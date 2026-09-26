package com.eci.aquaport.aplicacion.servicio;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.EstadoDrone;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultorFlota {

    private static final Logger LOGGER = Logger.getLogger(ConsultorFlota.class.getName());
    private static final String MODELO_DEFECTO = "Aqua-Ranger 100";
    private static final int BATERIA_MINIMA = 35;

    public List<DroneAcuatico> obtenerDisponiblesBateriaDesc(List<DroneAcuatico> flota) {
        return flota.stream()
                .filter(DroneAcuatico::disponible)
                .filter(d -> d.bateria() >= BATERIA_MINIMA)
                .sorted(Comparator.comparingInt(DroneAcuatico::bateria).reversed())
                .toList();
    }

    public List<String> obtenerIdsDisponibles(List<DroneAcuatico> flota) {
        return flota.stream()
                .filter(DroneAcuatico::disponible)
                .map(DroneAcuatico::id)
                .toList();
    }

    public boolean existeDisponibleBateriaSuficiente(List<DroneAcuatico> flota) {
        return flota.stream()
                .anyMatch(d -> d.disponible() && d.bateria() >= BATERIA_MINIMA);
    }

    public long contarDisponibles(List<DroneAcuatico> flota) {
        return flota.stream()
                .filter(DroneAcuatico::disponible)
                .count();
    }

    public Optional<DroneAcuatico> obtenerConMayorBateria(List<DroneAcuatico> flota) {
        return flota.stream()
                .max(Comparator.comparingInt(DroneAcuatico::bateria));
    }

    public static void main(String[] args) {
        ConsultorFlota consultor = new ConsultorFlota();

        List<DroneAcuatico> flota = List.of(
                new DroneAcuatico("AR-01", MODELO_DEFECTO, 92, EstadoDrone.DISPONIBLE, "Embalse Norte"),
                new DroneAcuatico("AR-02", MODELO_DEFECTO, 45, EstadoDrone.DISPONIBLE, "Canal Central"),
                new DroneAcuatico("AR-03", MODELO_DEFECTO, 18, EstadoDrone.MANTENIMIENTO, "Laguna Sur"),
                new DroneAcuatico("AR-04", MODELO_DEFECTO, 73, EstadoDrone.DISPONIBLE, "Punto Ribereño Este")
        );

        LOGGER.info("=== REPORTE DE CONSULTAS DE FLOTA AQUAPORT ===");

        LOGGER.log(Level.INFO, "1. Drones disponibles (bateria >= 35%) desc: {0}",
                consultor.obtenerDisponiblesBateriaDesc(flota));

        LOGGER.log(Level.INFO, "2. IDs de drones disponibles: {0}",
                consultor.obtenerIdsDisponibles(flota));

        LOGGER.log(Level.INFO, "3. Existe disponible con bateria suficiente: {0}",
                consultor.existeDisponibleBateriaSuficiente(flota));

        LOGGER.log(Level.INFO, "4. Cantidad de drones disponibles: {0}",
                consultor.contarDisponibles(flota));

        LOGGER.log(Level.INFO, "5. Drone con mayor bateria: {0}",
                consultor.obtenerConMayorBateria(flota).orElse(null));
    }
}
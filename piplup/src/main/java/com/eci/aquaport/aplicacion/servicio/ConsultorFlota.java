package com.eci.aquaport.aplicacion.servicio;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ConsultorFlota {

    /**
     * Consulta 1: Drones disponibles con bateria >= 35%, ordenados de mayor a menor bateria.
     */
    public List<DroneAcuatico> obtenerDisponiblesOperativosOrdenados(List<DroneAcuatico> flota) {
        List<DroneAcuatico> dronesOperativos = flota.stream().filter(drone -> drone.disponible() && drone.bateria() >= 35)
                .sorted(Comparator.comparingInt(DroneAcuatico::bateria).reversed())
                .toList();
        return dronesOperativos;
    }

    /**
     * Consulta 2: Solo los IDs de los drones disponibles.
     */
    public List<String> obtenerIdsDronesDisponibles(List<DroneAcuatico> flota) {
        return flota.stream().filter(drone -> drone.disponible()).map(DroneAcuatico::id).toList();
    }

    /**
     * Consulta 3: Verifica si existe algun drone disponible con bateria >= 35%.
     */
    public boolean existeDisponibleOperativo(List<DroneAcuatico> flota) {
        boolean existeDron = flota.stream().anyMatch(droneAcuatico -> droneAcuatico.bateria() >= 35 && droneAcuatico.disponible());
        return existeDron;
    }

    /**
     * Consulta 4: Cantidad total de drones disponibles.
     */
    public long contarDronesDisponibles(List<DroneAcuatico> flota) {
        long numDrones = flota.stream().filter(DroneAcuatico::disponible).count();
        return numDrones;
    }

    /**
     * Consulta 5: Drone con la mayor bateria de toda la flota.
     */
    public Optional<DroneAcuatico> obtenerDroneConMayorBateria(List<DroneAcuatico> flota) {
        Optional<DroneAcuatico> maxDrone = flota.stream().max(Comparator.comparingInt(DroneAcuatico::bateria));
        return maxDrone;
    }

    public static void main(String[] args) {
        ConsultorFlota consultor = new ConsultorFlota();

        List<DroneAcuatico> flota = List.of(
            new DroneAcuatico("AR-01", "Aqua-Ranger 100", 92, true,  "Embalse Norte"),
            new DroneAcuatico("AR-02", "Aqua-Ranger 100", 45, true,  "Canal Central"),
            new DroneAcuatico("AR-03", "Aqua-Ranger 100", 18, false, "Laguna Sur"),
            new DroneAcuatico("AR-04", "Aqua-Ranger 100", 73, true,  "Punto Ribereño Este")
        );

        System.out.println("--- PRUEBAS CONSULTOR FLOTA (PIPLUP) ---");
        
        // 1.
        System.out.println("1. Disponibles >= 35% ordenados desc:");
        System.out.println(consultor.obtenerDisponiblesOperativosOrdenados(flota));

        // 2.
        System.out.println("2. IDs de disponibles:");
        System.out.println(consultor.obtenerIdsDronesDisponibles(flota));

        // 3.
        System.out.println("3. ¿Existe disponible >= 35%?:");
        System.out.println(consultor.existeDisponibleOperativo(flota));

        // 4.
        System.out.println("4. Conteo de disponibles:");
        System.out.println(consultor.contarDronesDisponibles(flota));

        // 5.
        System.out.println("5. Drone con mayor batería:");
        System.out.println(consultor.obtenerDroneConMayorBateria(flota));
    }
}
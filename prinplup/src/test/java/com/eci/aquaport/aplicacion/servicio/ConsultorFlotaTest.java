package com.eci.aquaport.aplicacion.servicio;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ConsultorFlotaTest {

    private ConsultorFlota consultor;
    private List<DroneAcuatico> flota;

    @BeforeEach
    void setUp() {
        consultor = new ConsultorFlota();
        flota = List.of(
                new DroneAcuatico("AR-01", "Aqua-Ranger 100", 92, true, "Embalse Norte"),
                new DroneAcuatico("AR-02", "Aqua-Ranger 100", 45, true, "Canal Central"),
                new DroneAcuatico("AR-03", "Aqua-Ranger 100", 18, false, "Laguna Sur"),
                new DroneAcuatico("AR-04", "Aqua-Ranger 100", 73, true, "Punto Ribereño Este")
        );
    }

    @Test
    @DisplayName("1. Obtener disponibles con batería >= 35% ordenados descendentemente")
    void obtenerDisponiblesBateriaDesc_retornaDronesFiltradosYOrdenados() {
        List<DroneAcuatico> resultado = consultor.obtenerDisponiblesBateriaDesc(flota);

        assertEquals(3, resultado.size());
        assertEquals("AR-01", resultado.get(0).id());
        assertEquals("AR-04", resultado.get(1).id());
        assertEquals("AR-02", resultado.get(2).id());
    }

    @Test
    @DisplayName("2. Obtener únicamente los IDs de drones disponibles")
    void obtenerIdsDisponibles_retornaListaDeIds() {
        List<String> ids = consultor.obtenerIdsDisponibles(flota);

        assertEquals(List.of("AR-01", "AR-02", "AR-04"), ids);
    }

    @Test
    @DisplayName("3. Verificar existencia de drone disponible con batería suficiente")
    void existeDisponibleBateriaSuficiente_retornaTrue() {
        assertTrue(consultor.existeDisponibleBateriaSuficiente(flota));
    }

    @Test
    @DisplayName("4. Contar cantidad total de drones disponibles")
    void contarDisponibles_retornaTres() {
        assertEquals(3, consultor.contarDisponibles(flota));
    }

    @Test
    @DisplayName("5. Obtener drone con mayor batería de toda la flota")
    void obtenerConMayorBateria_retornaOptionalConDroneMaximo() {
        Optional<DroneAcuatico> max = consultor.obtenerConMayorBateria(flota);

        assertTrue(max.isPresent());
        assertEquals("AR-01", max.get().id());
        assertEquals(92, max.get().bateria());
    }

    @Test
    @DisplayName("6. Ejecutar método main de demostración sin errores")
    void mainDemostrativo_ejecutaCorrectamente() {
        assertDoesNotThrow(() -> ConsultorFlota.main(new String[]{}));
    }
}
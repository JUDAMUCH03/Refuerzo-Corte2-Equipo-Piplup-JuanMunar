package com.eci.aquaport.aplicacion.validador;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorMisionTest {

    private ValidadorMision validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorMision();
    }

    @Test
    @DisplayName("1. Drone con batería ≥ 35% es apto para misión")
    void droneBateriaSuficiente_puedeAsignarse() {
        // Arrange
        DroneAcuatico drone = new DroneAcuatico("AR-01", "Aqua-Ranger 100", 85, true, "Embalse Norte");

        // Act
        boolean resultado = validador.tieneBateriaSuficiente(drone);

        // Assert
        assertTrue(resultado, "El drone con 85% debe tener batería suficiente");
    }

    @Test
    @DisplayName("2. Drone con batería < 35% NO es apto para misión")
    void droneBateriaCritica_noAsignable() {
        // Arrange
        DroneAcuatico drone = new DroneAcuatico("AR-03", "Aqua-Ranger 100", 18, true, "Laguna Sur");

        // Act
        boolean resultado = validador.tieneBateriaSuficiente(drone);

        // Assert
        assertFalse(resultado, "El drone con 18% no debe tener batería suficiente");
    }

    @Test
    @DisplayName("3. Punto de llegada nulo lanza IllegalArgumentException")
    void puntoLlegadaNulo_lanzaExcepcion() {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validador.validarPuntoLlegada(null));
        
        assertEquals("El punto de llegada no puede ser nulo ni vacío", ex.getMessage());
    }

    @Test
    @DisplayName("4. Punto de llegada vacío o en blanco lanza IllegalArgumentException")
    void puntoLlegadaVacio_lanzaExcepcion() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> validador.validarPuntoLlegada("   "));
    }

    @Test
    @DisplayName("5. Drone con disponible=false NO puede ser asignado")
    void droneNoDisponible_noAsignable() {
        // Arrange
        DroneAcuatico droneOcupado = new DroneAcuatico("AR-02", "Aqua-Ranger 100", 90, false, "Canal Central");

        // Act
        boolean resultado = validador.estaDisponible(droneOcupado);

        // Assert
        assertFalse(resultado, "Un drone no disponible no debe ser apto para nueva misión");
    }

    @Test
    @DisplayName("6. Zona de destino inválida o no navegable lanza IllegalArgumentException (Edge Case)")
    void zonaInvalida_lanzaExcepcion() {
        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validador.validarZona("Zona Desconocida Tierradentro"));
        
        assertTrue(ex.getMessage().contains("Zona no autorizada"));
    }

    @Test
    @DisplayName("7. Punto de llegada válido no lanza ninguna excepción")
    void puntoLlegadaValido_noLanzaExcepcion() {
        assertDoesNotThrow(() -> validador.validarPuntoLlegada("Laboratorio Hídrico Central"));
    }

    @Test
    @DisplayName("8. Zona hídrica permitida no lanza ninguna excepción")
    void zonaValida_noLanzaExcepcion() {
        assertDoesNotThrow(() -> validador.validarZona("Embalse Norte"));
    }

    @Test
    @DisplayName("9. Drone disponible retorna true")
    void droneDisponible_retornaTrue() {
        DroneAcuatico drone = new DroneAcuatico("AR-04", "Aqua-Ranger 100", 75, true, "Punto Ribereño Este");
        assertTrue(validador.estaDisponible(drone));
    }

    @Test
    @DisplayName("10. Drone nulo retorna false en verificaciones sin lanzar NullPointerException")
    void droneNulo_retornaFalse() {
        assertFalse(validador.tieneBateriaSuficiente(null));
        assertFalse(validador.estaDisponible(null));
    }
}
package com.eci.aquaport.aplicacion.servicio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NotificadorOperadorTest {

    @Test
    @DisplayName("Notificador emite mensajes informativos y de advertencia sin lanzar excepciones")
    void notificarOperador_emiteLogsCorrectamente() {
        NotificadorOperador notificador = new NotificadorOperador();

        assertDoesNotThrow(() -> notificador.notificarAsignacion("Misión asignada con éxito"));
        assertDoesNotThrow(() -> notificador.notificarError("Batería por debajo del umbral"));
    }
}
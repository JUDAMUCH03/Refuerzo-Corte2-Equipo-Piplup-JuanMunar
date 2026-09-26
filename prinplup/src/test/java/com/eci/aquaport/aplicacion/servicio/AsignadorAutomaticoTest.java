package com.eci.aquaport.aplicacion.servicio;

import com.eci.aquaport.aplicacion.estrategia.EstrategiaSeleccion;
import com.eci.aquaport.aplicacion.observador.ObservadorMision;
import com.eci.aquaport.dominio.fabrica.FabricaDrones;
import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.EstadoDrone;
import com.eci.aquaport.dominio.modelo.Mision;
import com.eci.aquaport.dominio.modelo.Prioridad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AsignadorAutomaticoTest {

    @Mock
    private EstrategiaSeleccion estrategiaMock;

    @Mock
    private ObservadorMision centroControlMock;

    @Mock
    private ObservadorMision tecnicoMock;

    private AsignadorAutomatico asignador;
    private FabricaDrones fabrica;

    @BeforeEach
    void setUp() {
        asignador = new AsignadorAutomatico(estrategiaMock);
        fabrica = new FabricaDrones();
    }

    @Test
    @DisplayName("Al procesar una asignacion con un drone en FALLO, todos los observadores son notificados exactamente una vez")
    void registrarMision_conDroneEnFallo_notificaATodosLosObservadoresUnaVez() {
        asignador.registrarObservador(centroControlMock);
        asignador.registrarObservador(tecnicoMock);

        DroneAcuatico droneAveriado = fabrica.crearDrone("SUPERFICIAL", "AR-05", 15, EstadoDrone.FALLO, "Laguna Sur");
        DroneAcuatico droneDisponible = fabrica.crearDrone("BUCEADOR", "AR-06", 80, EstadoDrone.DISPONIBLE, "Laguna Sur");
        List<DroneAcuatico> flota = List.of(droneAveriado, droneDisponible);

        Mision mision = new Mision("MIS-101", Prioridad.NORMAL, null);
        when(estrategiaMock.seleccionar(flota, mision)).thenReturn(Optional.of(droneDisponible));

        Optional<DroneAcuatico> resultado = asignador.asignar(mision, flota);

        assertTrue(resultado.isPresent());
        assertEquals("AR-06", resultado.get().id());

        verify(centroControlMock, times(1)).notificarFallo(droneAveriado);
        verify(tecnicoMock, times(1)).notificarFallo(droneAveriado);
    }
}
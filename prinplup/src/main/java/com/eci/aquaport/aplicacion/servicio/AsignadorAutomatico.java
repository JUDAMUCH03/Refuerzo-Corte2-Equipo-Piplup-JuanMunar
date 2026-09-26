package com.eci.aquaport.aplicacion.servicio;

import com.eci.aquaport.aplicacion.estrategia.EstrategiaSeleccion;
import com.eci.aquaport.aplicacion.observador.ObservadorMision;
import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.EstadoDrone;
import com.eci.aquaport.dominio.modelo.Mision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AsignadorAutomatico {

    private EstrategiaSeleccion estrategia;
    private final List<ObservadorMision> observadores = new ArrayList<>();

    public AsignadorAutomatico(EstrategiaSeleccion estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaSeleccion estrategia) {
        this.estrategia = estrategia;
    }

    public void registrarObservador(ObservadorMision observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void removerObservador(ObservadorMision observador) {
        observadores.remove(observador);
    }

    public Optional<DroneAcuatico> asignar(Mision mision, List<DroneAcuatico> flota) {
        // Notificar observadores si existen unidades en fallo reportadas en la flota
        for (DroneAcuatico drone : flota) {
            if (drone.estado() == EstadoDrone.FALLO) {
                notificarFalloObservadores(drone);
            }
        }

        Optional<DroneAcuatico> seleccionado = estrategia.seleccionar(flota, mision);

        if (seleccionado.isEmpty()) {
            notificarFalloAsignacionObservadores(mision);
        }

        return seleccionado;
    }

    private void notificarFalloObservadores(DroneAcuatico drone) {
        for (ObservadorMision obs : observadores) {
            obs.notificarFallo(drone);
        }
    }

    private void notificarFalloAsignacionObservadores(Mision mision) {
        for (ObservadorMision obs : observadores) {
            obs.notificarFalloAsignacion(mision);
        }
    }
}
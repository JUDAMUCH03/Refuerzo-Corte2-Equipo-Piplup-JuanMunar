package com.eci.aquaport.aplicacion.observador;

import com.eci.aquaport.dominio.modelo.DroneAcuatico;
import com.eci.aquaport.dominio.modelo.Mision;

public interface ObservadorMision {
    void notificarFallo(DroneAcuatico drone);
    void notificarFalloAsignacion(Mision mision);
}
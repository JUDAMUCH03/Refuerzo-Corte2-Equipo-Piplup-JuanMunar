package com.eci.aquaport.dominio.modelo;

public class DroneBuceador extends DroneAcuatico {
    public DroneBuceador(String id, int bateria, EstadoDrone estado, String zona) {
        super(id, "BUCEADOR", bateria, estado, zona, 300);
    }
}
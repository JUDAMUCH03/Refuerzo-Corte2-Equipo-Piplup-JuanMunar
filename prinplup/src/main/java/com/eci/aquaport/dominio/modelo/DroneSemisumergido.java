package com.eci.aquaport.dominio.modelo;

public class DroneSemisumergido extends DroneAcuatico {
    public DroneSemisumergido(String id, int bateria, EstadoDrone estado, String zona) {
        super(id, "SEMISUMERGIDO", bateria, estado, zona, 1500);
    }
}
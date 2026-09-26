package com.eci.aquaport.dominio.modelo;

public class DroneSuperficial extends DroneAcuatico {
    public DroneSuperficial(String id, int bateria, EstadoDrone estado, String zona) {
        super(id, "SUPERFICIAL", bateria, estado, zona, 500);
    }
}
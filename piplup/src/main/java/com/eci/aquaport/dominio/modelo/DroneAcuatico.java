package com.eci.aquaport.dominio.modelo;

public record DroneAcuatico(
    String  id,
    String  modelo,
    int     bateria,
    boolean disponible,
    String  zona
) {}
package com.eci.aquaport.dominio.modelo;

import java.util.Objects;

public class DroneAcuatico {

    private final String id;
    private final String tipo;
    private final int bateria;
    private final EstadoDrone estado;
    private final String zona;
    private final int capacidadCargaGramos;

    public DroneAcuatico(String id, String tipo, int bateria, EstadoDrone estado, String zona, int capacidadCargaGramos) {
        this.id = id;
        this.tipo = tipo;
        this.bateria = bateria;
        this.estado = estado;
        this.zona = zona;
        this.capacidadCargaGramos = capacidadCargaGramos;
    }

    public DroneAcuatico(String id, String tipo, int bateria, EstadoDrone estado, String zona) {
        this(id, tipo, bateria, estado, zona, 500);
    }

    public DroneAcuatico(String id, String tipo, int bateria, boolean disponible, String zona) {
        this(id, tipo, bateria, disponible ? EstadoDrone.DISPONIBLE : EstadoDrone.MANTENIMIENTO, zona, 500);
    }

    public String id() { return id; }
    public String getId() { return id; }

    public String tipo() { return tipo; }
    public String getTipo() { return tipo; }

    public int bateria() { return bateria; }
    public int getBateria() { return bateria; }

    public EstadoDrone estado() { return estado; }
    public EstadoDrone getEstado() { return estado; }

    public String zona() { return zona; }
    public String getZona() { return zona; }

    public int capacidadCargaGramos() { return capacidadCargaGramos; }
    public int getCapacidadCargaGramos() { return capacidadCargaGramos; }

    public boolean disponible() { return this.estado == EstadoDrone.DISPONIBLE; }
    public boolean isDisponible() { return this.estado == EstadoDrone.DISPONIBLE; }

    public String modelo() { return this.tipo; }
    public String getModelo() { return this.tipo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneAcuatico that = (DroneAcuatico) o;
        return bateria == that.bateria &&
                capacidadCargaGramos == that.capacidadCargaGramos &&
                Objects.equals(id, that.id) &&
                Objects.equals(tipo, that.tipo) &&
                estado == that.estado &&
                Objects.equals(zona, that.zona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, bateria, estado, zona, capacidadCargaGramos);
    }
}
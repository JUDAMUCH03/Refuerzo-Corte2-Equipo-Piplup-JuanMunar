package com.eci.aquaport.dominio.modelo;

import java.util.Objects;

public class DroneAcuatico {

    private final String id;
    private final String tipo;
    private final int bateria;
    private final EstadoDrone estado;
    private final String zona;

    // Constructor principal v2
    public DroneAcuatico(String id, String tipo, int bateria, EstadoDrone estado, String zona) {
        this.id = id;
        this.tipo = tipo;
        this.bateria = bateria;
        this.estado = estado;
        this.zona = zona;
    }

    // Constructor sobrecargado para retrocompatibilidad con v1
    public DroneAcuatico(String id, String tipo, int bateria, boolean disponible, String zona) {
        this(id, tipo, bateria, disponible ? EstadoDrone.DISPONIBLE : EstadoDrone.MANTENIMIENTO, zona);
    }

    public String id() {
        return id;
    }

    public String getId() {
        return id;
    }

    public String tipo() {
        return tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public int bateria() {
        return bateria;
    }

    public int getBateria() {
        return bateria;
    }

    public EstadoDrone estado() {
        return estado;
    }

    public EstadoDrone getEstado() {
        return estado;
    }

    public String zona() {
        return zona;
    }

    public String getZona() {
        return zona;
    }

    public boolean disponible() {
        return this.estado == EstadoDrone.DISPONIBLE;
    }

    public boolean isDisponible() {
        return this.estado == EstadoDrone.DISPONIBLE;
    }

    public String modelo() {
        return this.tipo;
    }

    public String getModelo() {
        return this.tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneAcuatico that = (DroneAcuatico) o;
        return bateria == that.bateria &&
                Objects.equals(id, that.id) &&
                Objects.equals(tipo, that.tipo) &&
                estado == that.estado &&
                Objects.equals(zona, that.zona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, bateria, estado, zona);
    }

    @Override
    public String toString() {
        return "DroneAcuatico{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", bateria=" + bateria +
                ", estado=" + estado +
                ", zona='" + zona + '\'' +
                '}';
    }
}
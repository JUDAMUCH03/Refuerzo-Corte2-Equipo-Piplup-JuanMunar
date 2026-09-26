package com.eci.aquaport.dominio.modelo;

import java.util.Objects;

public class Mision {

    private final String id;
    private final Prioridad prioridad;
    private final DroneAcuatico droneAsignado;

    public Mision(String id, Prioridad prioridad, DroneAcuatico droneAsignado) {
        this.id = id;
        this.prioridad = prioridad;
        this.droneAsignado = droneAsignado;
    }

    public String id() {
        return id;
    }

    public String getId() {
        return id;
    }

    public Prioridad prioridad() {
        return prioridad;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public DroneAcuatico droneAsignado() {
        return droneAsignado;
    }

    public DroneAcuatico getDroneAsignado() {
        return droneAsignado;
    }

    public DroneAcuatico getDrone() {
        return droneAsignado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mision mision = (Mision) o;
        return Objects.equals(id, mision.id) &&
                prioridad == mision.prioridad &&
                Objects.equals(droneAsignado, mision.droneAsignado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prioridad, droneAsignado);
    }

    @Override
    public String toString() {
        return "Mision{" +
                "id='" + id + '\'' +
                ", prioridad=" + prioridad +
                ", droneAsignado=" + droneAsignado +
                '}';
    }
}
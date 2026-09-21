package com.eci.aquaport.dominio.modelo;

public class DroneAcuatico {
    private String id;
    private String modelo;
    private int bateria;
    private boolean disponible;
    private String zona;

    public DroneAcuatico() {
    }

    public DroneAcuatico(String id, String modelo, int bateria, boolean disponible, String zona) {
        this.id = id;
        this.modelo = modelo;
        this.bateria = bateria;
        this.disponible = disponible;
        this.zona = zona;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}

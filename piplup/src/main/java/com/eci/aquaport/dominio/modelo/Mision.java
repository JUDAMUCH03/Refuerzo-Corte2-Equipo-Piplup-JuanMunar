package com.eci.aquaport.dominio.modelo;

public class Mision {
    private String id;
    private DroneAcuatico drone;
    private String puntoPartida;
    private String puntoLlegada;
    private TipoCarga tipoCarga;
    private EstadoMision estado;

    public Mision() {
    }

    public Mision(String id, DroneAcuatico drone, String puntoPartida, String puntoLlegada,
                  TipoCarga tipoCarga, EstadoMision estado) {
        this.id = id;
        this.drone = drone;
        this.puntoPartida = puntoPartida;
        this.puntoLlegada = puntoLlegada;
        this.tipoCarga = tipoCarga;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DroneAcuatico getDrone() {
        return drone;
    }

    public void setDrone(DroneAcuatico drone) {
        this.drone = drone;
    }

    public String getPuntoPartida() {
        return puntoPartida;
    }

    public void setPuntoPartida(String puntoPartida) {
        this.puntoPartida = puntoPartida;
    }

    public String getPuntoLlegada() {
        return puntoLlegada;
    }

    public void setPuntoLlegada(String puntoLlegada) {
        this.puntoLlegada = puntoLlegada;
    }

    public TipoCarga getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public EstadoMision getEstado() {
        return estado;
    }

    public void setEstado(EstadoMision estado) {
        this.estado = estado;
    }
}

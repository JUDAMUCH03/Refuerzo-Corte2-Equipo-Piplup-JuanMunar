package com.eci.aquaport.aplicacion.servicio;

import com.eci.aquaport.dominio.modelo.Mision;
import com.eci.aquaport.dominio.repositorio.RepositorioMisiones;

import java.util.List;
import java.util.Optional;

public class RegistradorMisiones {

    private final RepositorioMisiones repositorioMisiones;

    public RegistradorMisiones(RepositorioMisiones repositorioMisiones) {
        if (repositorioMisiones == null) {
            throw new IllegalArgumentException("El repositorio de misiones no puede ser nulo.");
        }
        this.repositorioMisiones = repositorioMisiones;
    }

    public void registrar(Mision mision) {
        if (mision == null) {
            throw new IllegalArgumentException("La misión no puede ser nula.");
        }
        repositorioMisiones.guardar(mision);
    }

    public Optional<Mision> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id de la misión no puede ser nulo ni vacío.");
        }
        return repositorioMisiones.buscarPorId(id);
    }

    public List<Mision> obtenerTodas() {
        return repositorioMisiones.obtenerTodas();
    }
}

package co.edu.uniquindio.poo.parcial_1.model;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {
    private static Gimnasio instancia;
    private String nombre;
    private List<Cliente> listaClientes;
    private List<Entrenador> listaEntrenadores;
    private List<Inscripcion> listaInscripciones;

    private Gimnasio(String nombre) {
        this.nombre = nombre;
        this.listaClientes = new ArrayList<>();
        this.listaEntrenadores = new ArrayList<>();
        this.listaInscripciones = new ArrayList<>();
    }

    /**
     * Implementacion del patron singleton
     *
     */
    public static Gimnasio getInstancia() {
        if (instancia == null) {
            instancia = new Gimnasio("SmartGym");
        }
        return instancia;
    }

    /**
     * Metodo para registrar la inscripcion en el gimnasio
     * @param inscripcion
     * @return
     */
    public boolean registrarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null) {
            listaInscripciones.add(inscripcion);
            return true;
        }
        return false;
    }

    public List<Inscripcion> getListaInscripciones() {
        return listaInscripciones;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Entrenador> getListaEntrenadores() {
        return listaEntrenadores;
    }
}
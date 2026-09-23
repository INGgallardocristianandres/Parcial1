package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Fábrica concreta encargada de construir objetos de tipo {@link ServiciosEspecializados}.
 */
public class ServiciosEspecializadosFactory implements ServicioAdicionalFactory {

    /**
     * Crea un nuevo servicio adicional especializado con aforo limitado .
     */
    @Override
    public ServicioAdicional crearServicio(String codigo, String nombre, String desc, double precio) {
        return new ServiciosEspecializados(codigo, nombre, desc, precio, 20);
    }
}
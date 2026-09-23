package co.edu.uniquindio.poo.parcial_1.model;
/**
 * Fábrica concreta encargada de construir objetos de tipo {@link ServiciosSalud}.
 */
public class ServiciosSaludFactory implements ServicioAdicionalFactory {

    /**
     * Crea un nuevo servicio adicional enfocado en la salud.
     */
    @Override
    public ServicioAdicional crearServicio(String codigo, String nombre, String desc, double precio) {
        return new ServiciosSalud(codigo, nombre, desc, precio, true);
    }
}
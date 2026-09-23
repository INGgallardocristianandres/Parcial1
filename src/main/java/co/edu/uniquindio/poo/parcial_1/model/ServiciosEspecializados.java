package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Representa servicios especializados dentro del gimnasio.
 */
public class ServiciosEspecializados extends ServicioAdicional {
    private int cupoMaximo;

    /**
     * Constructor para un servicio especializado.
     */
    public ServiciosEspecializados(String codigo, String nombre, String descripcion, double precio, int cupoMaximo) {
        super(codigo, nombre, descripcion, precio, true);
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }
}
package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Representa servicios de salud dentro del gimnasio.
 */
public class ServiciosSalud extends ServicioAdicional {
    private boolean requiereValoracionPrevia;

    /**
     * Constructor para un servicio adicional de salud.
     */
    public ServiciosSalud(String codigo, String nombre, String descripcion, double precio, boolean requiereValoracionPrevia) {
        super(codigo, nombre, descripcion, precio, true);
        this.requiereValoracionPrevia = requiereValoracionPrevia;
    }

    /**
     * Metodo para validar si la valoracion medica esta realizada o no
     * @return True si requiere valoración médica previa.
     */
    public boolean isRequiereValoracionPrevia() {
        return requiereValoracionPrevia;
    }
}

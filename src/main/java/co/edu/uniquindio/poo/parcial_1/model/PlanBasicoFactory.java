package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Fábrica concreta encargada de construir objetos de tipo {@link PlanBasico}.
 * Guarda los datos del plan al momento de crear la fábrica, y
 * {@link #crearPlan()} construye la instancia con esos datos.
 */
public class PlanBasicoFactory extends PlanFactory {

    private final String codigo;
    private final String nombre;
    private final String descripcion;
    private final int duracionMeses;
    private final double valorMensual;
    private final boolean accesoMaquinas;

    public PlanBasicoFactory(String codigo, String nombre, String descripcion,
                             int duracionMeses, double valorMensual, boolean accesoMaquinas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.accesoMaquinas = accesoMaquinas;
    }

    /**
     * Crea un nuevo plan de entrenamiento básico con los datos guardados.
     */
    @Override
    public PlanEntrenamiento crearPlan() {
        return new PlanBasico(codigo, nombre, descripcion, duracionMeses, valorMensual, accesoMaquinas);
    }
}
package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Plan de entrenamiento premium: incluye acceso VIP y/o a clases
 * grupales, según lo que se configure al crearlo.
 */
public class PlanPremium extends PlanEntrenamiento {

    private boolean accesoVIP;
    private boolean accesoClasesGrupales;

    /**
     * Crea un plan premium con los datos indicados. Pensado para ser
     * construido a través de {@link PlanPremiumFactory}.
     */
    public PlanPremium(String codigo, String nombre, String descripcion,
                       int duracionMeses, double valorMensual,
                       boolean accesoVIP, boolean accesoClasesGrupales) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        this.accesoVIP = accesoVIP;
        this.accesoClasesGrupales = accesoClasesGrupales;
    }

    /**
     * Constructor de copia, usado por {@link #clonar()}.
     */
    private PlanPremium(PlanPremium otro) {
        super(otro);
        this.accesoVIP = otro.accesoVIP;
        this.accesoClasesGrupales = otro.accesoClasesGrupales;
    }

    public boolean isAccesoVIP() {
        return accesoVIP;
    }

    public void setAccesoVIP(boolean accesoVIP) {
        this.accesoVIP = accesoVIP;
    }

    public boolean isAccesoClasesGrupales() {
        return accesoClasesGrupales;
    }

    public void setAccesoClasesGrupales(boolean accesoClasesGrupales) {
        this.accesoClasesGrupales = accesoClasesGrupales;
    }

    @Override
    public double calcularAdicional() {
        double adicional = 0.0;
        if (accesoVIP) {
            adicional += 30000.0;
        }
        if (accesoClasesGrupales) {
            adicional += 20000.0;
        }
        return adicional;
    }

    @Override
    public PlanEntrenamiento clonar() {
        return new PlanPremium(this);
    }
}
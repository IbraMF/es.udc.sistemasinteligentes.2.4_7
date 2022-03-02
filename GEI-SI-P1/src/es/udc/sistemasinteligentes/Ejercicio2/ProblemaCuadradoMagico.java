package es.udc.sistemasinteligentes.Ejercicio2;

import es.udc.sistemasinteligentes.*;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
    }

    @Override
    public boolean esMeta(Estado es) {
        return false;
    }

    @Override
    public Accion[] acciones(Estado es) {
        return new Accion[0];
    }
}

package es.udc.sistemasinteligentes.Ejercicio2;

import es.udc.sistemasinteligentes.*;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadradoMagico extends Estado{



        private int [][] cuadrado;

        public EstadoCuadradoMagico(int[][] cuadrado) {//Se usa para definir el estado inicial del cuadrado
            this.cuadrado = cuadrado;
        }

        @Override
        public String toString() {
            return "(Matriz de tamaño" + cuadrado.length+ ")" ;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

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

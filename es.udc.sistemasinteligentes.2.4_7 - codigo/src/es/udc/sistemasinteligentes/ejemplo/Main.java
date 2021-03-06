package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);
        EstrategiaBusqueda buscador = new Estrategia4();
        EstrategiaBusqueda buscador2 =new EstrategiaBusquedaGrafo();
        Nodo[] solucion=buscador.soluciona(aspiradora);
        System.out.println(solucion[solucion.length-1].getEstado());
        Nodo[] solucion2=buscador2.soluciona(aspiradora);
        System.out.println(solucion2[solucion2.length-1].getEstado());
    }
}

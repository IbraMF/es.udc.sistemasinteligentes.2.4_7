package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

    /** Cambiar(copiado de estrategia4), aparece en pag56 teoria */
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {

        ArrayList<Nodo> listaNodo = new ArrayList<>();
        ArrayList<Estado> explorados = new ArrayList<>();
        ArrayList<Estado> sucesores = new ArrayList<>();
        Nodo padre = null;
        Accion accion;
        Estado estadoActual = p.getEstadoInicial();

        explorados.add(estadoActual);

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)) {
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc : accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    accion = acc;
                    Nodo nodo = new Nodo(estadoActual,padre,accion);
                    listaNodo.add(nodo);
                    padre = nodo;
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if(!modificado) throw new Exception("No se ha podido encontrar una solución");

        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        Nodo[] arrayNodo = new Nodo[listaNodo.size()]; int j=0;
        for (Nodo nodo:listaNodo) {
            arrayNodo[j]= nodo;
            j++;
        }
        return arrayNodo;
    }

}

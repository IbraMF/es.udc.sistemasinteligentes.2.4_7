package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

    /** Cambiar(copiado de estrategia4), aparece en pag56 teoria */
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {

        ArrayList<Nodo> n = new ArrayList<>();
        ArrayList<Estado> explorados = new ArrayList<>();
        Nodo padre=null;
        Accion ac;
        Estado estadoActual = p.getEstadoInicial();
        //explorados.add(estadoActual);
        ArrayList<Nodo> frontera = new ArrayList<>();
        frontera.add(new Nodo(estadoActual,null,null));
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);
        while (!p.esMeta(estadoActual)){
            if(frontera.isEmpty()){
                throw new Exception("No se ha podido encontrar una solución");
            }
            padre=frontera.get(0);
            n.add(frontera.get(0));
            frontera.remove(0);
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            for(Accion acc: accionesDisponibles){
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if(p.esMeta(sc)){
                    n.add(new Nodo(sc,padre,acc));
                    break;
                }
                if(!explorados.contains(estadoActual)){
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);}
                if(!explorados.contains(sc)){
                    boolean esta=false;
                    for (Nodo nf: frontera) {
                        if(nf.estado == sc){
                            esta=true;
                            break;
                        }
                    }
                    if(!esta){
                        frontera.add(new Nodo(sc,padre,acc));
                    }
                }
            }
            if(frontera.size()>0){
                estadoActual=frontera.get(0).estado;
                System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
            }
            else{
                break;
            }

        }
        Nodo[] nodos = new Nodo[n.size()]; int j=0;
        for (Nodo nodo:n) {
            nodos[j]= nodo;
            j++;
        }
        return nodos;
    }

}

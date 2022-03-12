package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {
    public Queue<Nodo> sucesores(ProblemaBusqueda p, Queue<Nodo> frontera, Estado estadoActual,Nodo padre,ArrayList<Estado> Explorados){
        System.out.println("Expandiendo frontera{");
        Accion[] accionesDisponibles = p.acciones(p.getEstadoInicial());
        int i=0;
        Estado sc;
        for(Accion accion : accionesDisponibles){
            if(accion.esAplicable(estadoActual)){//comprobar si esta explorado ??
                sc = p.result(estadoActual,accion);
                System.out.println("-" + (i++) + " - RESULT(" + estadoActual + ","+ accion + ")=" + sc);
                if(!Explorados.contains(sc)){
                    System.out.println("-" + (i++) + " - " + sc + " NO explorado");
                    boolean esta=false;
                    for (Nodo nf: frontera) {
                        if(nf.estado == sc){
                            esta=true;
                            break;
                        }
                    }
                    if(!esta){
                        System.out.println("-" + (i++) + " - " + sc + " NO está en la forntera");
                        frontera.add(new Nodo(sc,padre,accion));

                    }
                }
                else{
                    System.out.println("-" + (i++) + " - " + sc + " ya explorado");
                }

            }
        }
        System.out.println("}Frontera expandida");
        return frontera;
    }

    /** Cambiar(copiado de estrategia4), aparece en pag56 teoria */
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {

        Queue<Nodo> frontera = new LinkedList<>();
        ArrayList<Nodo> listaNodo = new ArrayList<>();
        ArrayList<Estado> explorados = new ArrayList<>();
        Nodo padre = null;
        Nodo actual ;
        Accion accion;
        Estado estadoActual = p.getEstadoInicial();
        frontera.add(new Nodo(estadoActual,null,null));

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);
        while(!p.esMeta(estadoActual)){
            if(frontera.isEmpty()){
                throw new Exception("No se ha podido encontrar una solución");
            }
            else{
                actual=frontera.poll();//cojo el nodo padre (primero de la frontera) y lo elimino de la cola
            }
            if(p.esMeta(actual.estado)){
                System.out.println("Es meta"+ estadoActual + actual.estado );
                listaNodo.add(actual);
                break;
            }
            else{
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                explorados.add(estadoActual);
                frontera=sucesores(p,frontera,estadoActual,actual,explorados);

            }
            if(frontera.size()>0){
                estadoActual=frontera.peek().estado;
                System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
            }
            listaNodo.add(frontera.peek());
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
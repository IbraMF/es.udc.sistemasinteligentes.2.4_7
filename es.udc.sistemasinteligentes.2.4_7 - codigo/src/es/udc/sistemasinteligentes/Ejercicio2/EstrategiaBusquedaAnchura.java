package es.udc.sistemasinteligentes.Ejercicio2;

import es.udc.sistemasinteligentes.*;
import es.udc.sistemasinteligentes.ejemplo.Nodo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class EstrategiaBusquedaAnchura implements EstrategiaBusqueda {//misma implementacion que la busqueda en grafo?
    /** PAG 70 Teoría */
    private int nodosCreados;
    private int nodosExplorados;

    public Queue<Nodo> sucesores(ProblemaBusqueda p, Queue<Nodo> frontera, Estado estadoActual, Nodo padre, ArrayList<Estado> Explorados){
        System.out.println("Expandiendo frontera{");
        Accion[] accionesDisponibles = p.acciones(p.getEstadoInicial());
        int i=0;
        Estado sc;
        for(Accion accion : accionesDisponibles){
            if(accion.esAplicable(estadoActual)){//comprobar si esta explorado ??
                System.out.println(accion + " Es aplicable");
                sc = p.result(estadoActual,accion);
                //System.out.println("\t-" + (i++) + "RESULT:" + estadoActual + ","+ accion + " = " + sc);
                if(!Explorados.contains(sc)){
                    System.out.println("\t-" + (i++) + " - " + sc + " NO explorado");
                    boolean esta=false;
                    for (Nodo nf: frontera) {
                        if(nf.getEstado() == sc){
                            esta=true;
                            break;
                        }
                    }
                    if(!esta){
                        System.out.println("\t-" + (i++) + " - " + sc + " NO está en la frontera");
                        frontera.add(new Nodo(sc,padre,accion,0));
                        System.out.println("\t-" + (i++) + " - " + sc + " Añadido a la frontera");
                        nodosCreados++;
                    }
                    else{
                        System.out.println("\t-" + (i++) + " - " + sc + " SI está en la frontera");
                    }
                }
                else{
                    System.out.println("\t-" + (i++) + " - " + sc + " YA explorado");
                }

            }
            else{
                System.out.println(accion + " NO es aplicable");
            }
        }
        System.out.println("}Frontera expandida");
        return frontera;
    }
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {

        Queue<Nodo> frontera = new LinkedList<>();
        ArrayList<Nodo> listaNodo = new ArrayList<>();
        ArrayList<Estado> explorados = new ArrayList<>();
        Nodo padre = null;
        Nodo actual ;
        Accion accion;
        Estado estadoActual = p.getEstadoInicial();
        frontera.add(new Nodo(estadoActual,null,null,0));

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);
        while(true){
            if(frontera.isEmpty()){
                throw new Exception("No se ha podido encontrar una solución");
            }
            else{
                actual=frontera.poll();//cojo el nodo padre (primero de la frontera) y lo elimino de la cola
                nodosExplorados++;
            }
            if(p.esMeta(actual.getEstado())){
                System.out.println((i++) + " - " + actual.getEstado() + " es meta" );
                break;
            }
            else{
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                explorados.add(estadoActual);
                frontera=sucesores(p,frontera,estadoActual,actual,explorados);
            }
            if(frontera.size()>0){
                estadoActual=frontera.peek().getEstado();
                System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                listaNodo.add(frontera.peek());
            }
        }
        System.out.println((i++) + " - FIN - " + estadoActual + " : Creados: " + nodosCreados + " nodos ,Explorados: "
            + nodosExplorados + " nodos");
        Nodo[] arrayNodo = new Nodo[listaNodo.size()]; int j=0;
        for (Nodo nodo:listaNodo) {
            arrayNodo[j]= nodo;
            j++;
        }
        return arrayNodo;
    }
}
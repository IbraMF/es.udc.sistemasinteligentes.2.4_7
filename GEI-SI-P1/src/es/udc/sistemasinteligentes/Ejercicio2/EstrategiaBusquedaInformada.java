package es.udc.sistemasinteligentes.Ejercicio2;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ejemplo.Nodo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

/**IMmplementa busqueda informada A* */
public class EstrategiaBusquedaInformada implements EstrategiaBusqueda {
    public Queue<Nodo> ordenarForntera(Queue<Nodo> frontera){
        boolean ordenada=false;
        Nodo menor=frontera.peek();
        int s=frontera.size();
        Queue<Nodo> nFrontera=new LinkedList<>();
        while(!ordenada){
            for (Nodo n:frontera) {
                assert menor != null;
                if(n.compareTo(menor)<=0){
                    menor=n;
                }
            }
            nFrontera.add(menor);
            frontera.remove(menor);
            menor=frontera.peek();

            if(nFrontera.size() == s){
                ordenada=true;
            }
        }
        return nFrontera;
    }
    public Queue<Nodo> sucesores(ProblemaBusqueda p, Queue<Nodo> frontera, Estado estadoActual,Nodo padre,ArrayList<Estado> Explorados){
        HeuristicaC h=new HeuristicaC();
        System.out.println("Expandiendo frontera{");
        Accion[] accionesDisponibles = p.acciones(p.getEstadoInicial());
        int i=0;
        Estado sc;
        for(Accion accion : accionesDisponibles){
            if(accion.esAplicable(estadoActual)){//comprobar si esta explorado ??
                sc = p.result(estadoActual,accion);
                System.out.println("\t-" + (i++) + " - RESULT(" + estadoActual + ","+ accion + ")=" + sc);
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
                        frontera.add(new Nodo(sc,padre,accion,h.evalua(sc)));
                    }
                }
                else{
                    System.out.println("\t-" + (i++) + " - " + sc + " ya explorado");
                }

            }
        }
        System.out.println("}Frontera expandida");
        return frontera;
    }
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        HeuristicaC h = new HeuristicaC();
        Queue<Nodo> frontera = new LinkedList<>();
        ArrayList<Nodo> listaNodo = new ArrayList<>();
        ArrayList<Estado> explorados = new ArrayList<>();
        Nodo padre = null;
        Nodo actual ;
        Accion accion;
        Estado estadoActual = p.getEstadoInicial();
        frontera.add(new Nodo(estadoActual,null,null,h.evalua(estadoActual)));

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);
        while(true){
            if(frontera.isEmpty()){
                throw new Exception("No se ha podido encontrar una solución");
            }
            else{
                actual=frontera.poll();//cojo el nodo padre (primero de la frontera) y lo elimino de la cola
            }
            if(p.esMeta(actual.getEstado())){
                System.out.println((i++) + " - " + actual.getEstado() + " es meta" );
                break;
            }
            else{
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                explorados.add(estadoActual);
                frontera=sucesores(p,frontera,estadoActual,actual,explorados);
                System.out.println("Ordenando nodos");
                frontera=ordenarForntera(frontera);

            }
            if(frontera.size()>0){
                estadoActual=frontera.peek().getEstado();
                System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                listaNodo.add(frontera.peek());
            }
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

package es.udc.sistemasinteligentes.ejemplo;


import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo implements Comparable<Nodo> {
    Estado estado;
    Nodo padre;
    Accion accion;
    float coste;



    public Nodo(Estado estado, Nodo padre, Accion accion, float coste) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        this.coste =coste;
    }

    public Estado getEstado() {
         return estado;
    }
    public float getCoste() {
        return coste;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Accion getAccion() {
        return accion;
    }

    @Override
    public int compareTo(Nodo o) {
        return (int)(this.coste*100-o.getCoste()*100);
    }
}

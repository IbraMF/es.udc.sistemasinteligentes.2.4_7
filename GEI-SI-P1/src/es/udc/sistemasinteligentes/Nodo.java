package es.udc.sistemasinteligentes;

public class Nodo {
    Estado estado;
    Estado padre;
    Estado accion;

    public Nodo(Estado estado, Estado padre, Estado accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }

    public Estado getEstado() {
         return estado;
    }

    public Estado getPadre() {
        return padre;
    }

    public Estado getAccion() {
        return accion;
    }

}

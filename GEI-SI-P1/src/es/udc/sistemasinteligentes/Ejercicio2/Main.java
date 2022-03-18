package es.udc.sistemasinteligentes.Ejercicio2;
import es.udc.sistemasinteligentes.*;

import es.udc.sistemasinteligentes.ejemplo.Nodo;

public class Main {
    public static void main(String[] args) throws Exception{
        int [][]CuadradoT = new int[3][3];
        for (int i = 0; i < 3   ; i++) {
            for (int j = 0; j < 3; j++) {
                CuadradoT[i][j]=0;
            }
        }
        CuadradoT[0][0]=4;
                            CuadradoT[0][1]=9;CuadradoT[0][2]=2;
        CuadradoT[1][0]=3;  CuadradoT[1][1]=5;
        CuadradoT[2][1]=1;

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =new ProblemaCuadradoMagico.EstadoCuadradoMagico(CuadradoT);
        ProblemaBusqueda cuadrado= new ProblemaCuadradoMagico(estadoInicial);
        Accion[] accionesDisponibles = cuadrado.acciones(estadoInicial);
        EstrategiaBusqueda buscador1 =new EstrategiaBusquedaAnchura();
        Nodo[] solucion= buscador1.soluciona(cuadrado);
        System.out.println(solucion[solucion.length-1].getEstado());
    }
}
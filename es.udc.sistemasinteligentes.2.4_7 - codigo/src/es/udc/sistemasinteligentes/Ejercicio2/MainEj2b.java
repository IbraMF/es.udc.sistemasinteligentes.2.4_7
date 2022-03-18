package es.udc.sistemasinteligentes.Ejercicio2;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ejemplo.Nodo;

public class MainEj2b {
    public static void main(String[] args) throws Exception{
        int [][]CuadradoT = new int[4][4];
        for (int i = 0; i < 3   ; i++) {
            for (int j = 0; j < 3; j++) {
                CuadradoT[i][j]=0;
            }
        }
        CuadradoT[0][0]=2;
        CuadradoT[3][1]=1;
        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =new ProblemaCuadradoMagico.EstadoCuadradoMagico(CuadradoT,CuadradoT);
        ProblemaBusqueda cuadrado= new ProblemaCuadradoMagico(estadoInicial);
        EstrategiaBusqueda buscador3 =new EstrategiaBusquedaInformada();
        Nodo[] solucion3 = buscador3.soluciona(cuadrado);
        System.out.println("Estrategia en Informada " + solucion3[solucion3.length-1].getEstado());
    }
}

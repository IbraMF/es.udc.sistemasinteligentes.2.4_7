package es.udc.sistemasinteligentes.Ejercicio2;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ejemplo.Nodo;

public class MainEj2c {
    public static void main(String[] args) throws Exception{
        int [][]CuadradoT = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CuadradoT[i][j]=0;
            }
        }
        CuadradoT[0][0]=4;
        CuadradoT[0][1]=9;CuadradoT[0][2]=2;
        CuadradoT[1][0]=3;
        CuadradoT[2][1]=1;



        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =new ProblemaCuadradoMagico.EstadoCuadradoMagico(CuadradoT,CuadradoT);
        ProblemaBusqueda cuadrado= new ProblemaCuadradoMagico(estadoInicial);
        EstrategiaBusqueda buscador1 =new EstrategiaBusquedaAnchura();/* Anchura funciona*/
        Nodo[] solucion= buscador1.soluciona(cuadrado);
        EstrategiaBusqueda buscador2 =new EstrategiaBusquedaProfundidad();
        Nodo[] solucion2= buscador2.soluciona(cuadrado);
        System.out.println("Estrategia en anchura " + solucion[solucion.length-1].getEstado());
        System.out.println("Estrategia en profundidad " + solucion2[solucion2.length-1].getEstado());
    }
}

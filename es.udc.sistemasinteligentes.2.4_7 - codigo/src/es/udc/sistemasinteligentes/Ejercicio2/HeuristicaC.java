package es.udc.sistemasinteligentes.Ejercicio2;

import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;

public class HeuristicaC extends Heuristica {
    @Override
    public float evalua(Estado e) {
        float H=0,h=0;
        ProblemaCuadradoMagico.EstadoCuadradoMagico eC = (ProblemaCuadradoMagico.EstadoCuadradoMagico) e;
        int N=eC.getCuadrado().length;
        int[][] c = eC.getCuadrado();
        int sum=N*((N*N)+1)/2;//Suma de todos lo numeros desde 1 asta N
        int sumF=0,sumC=0,sumD1=0,sumD2=0;
        float sumasPosibles=N*2+2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumF+=c[i][j];
                sumC+=c[j][i];
            }
            if(sumF ==sum){
                h++;
            }
            if(sumC == sum){
                h++;
            }
        }
        for(int i=0;i<N;i++){
            sumD1+=c[i][i];
            sumD2+=c[N-1-i][i];
        }
        if(sumD1==sum){
            h++;
        }if(sumD2==sum){
            h++;
        }
        H=h/sumasPosibles;//valor entre 0 y 1 que determina cauntas de las posibles combinaciones en
                          // el estado actual dan el valor buscado
        return H;
    }
}

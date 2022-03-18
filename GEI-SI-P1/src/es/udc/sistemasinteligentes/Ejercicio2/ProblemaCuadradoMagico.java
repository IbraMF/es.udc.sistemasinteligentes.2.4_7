package es.udc.sistemasinteligentes.Ejercicio2;
import es.udc.sistemasinteligentes.*;
import es.udc.sistemasinteligentes.ejemplo.ProblemaAspiradora;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Arrays;

/**Con esto se pueden modificar los valores que entran distintos de 0 en la matriz (buscar forma de que no pase) */

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadradoMagico extends Estado{
        private final int [][] cuadrado;
        private final int [][] cuadradoInicial;

        public int[][] getCuadradoInicial() {
            return cuadradoInicial;
        }

        public int[][] getCuadrado() {
            return cuadrado;
        }

        public EstadoCuadradoMagico(int[][] cuadrado,int[][] cuadradoInicial) {//Se usa para definir el estado inicial del cuadrado
            this.cuadrado = cuadrado;
            this.cuadradoInicial = cuadradoInicial;
        }

        @Override
        public String toString() {
            StringBuilder matirz= new StringBuilder("[ ");
            for (int i = 0; i < cuadrado.length; i++) {
                for (int j = 0; j < cuadrado.length; j++) {
                    matirz.append(cuadrado[i][j]).append(",");
                }
                matirz.deleteCharAt(matirz.length()-1).append(" | ");
            }
            matirz.delete(matirz.length()-3,matirz.length()-1); //elimina caracteres finales
            return matirz + "]";
        }

        @Override
        public boolean equals(Object obj) {
            int igual=0;
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            for (int i = 0; i < cuadrado.length; i++) {
                for (int j = 0; j < cuadrado.length; j++) {
                    if(((EstadoCuadradoMagico) obj).cuadrado[i][j]==cuadrado[i][j]){
                        igual++;
                    }
                }
            }
            return igual==(cuadrado.length*cuadrado.length);
        }

        @Override
        public int hashCode() {
            int result = Arrays.deepHashCode(cuadrado);
            result = 31 * result;
            return result;
        }
    }

    public static class AccionCuadrado extends Accion{

        private int nuevoValorCasilla,casillaMf,casillaMc;


        public AccionCuadrado(int nuevoValorCasilla, int casillaMf, int casillaMc) {
            this.nuevoValorCasilla = nuevoValorCasilla;
            this.casillaMf = casillaMf;
            this.casillaMc = casillaMc;
        }

        @Override
        public String toString() {
            return "accion cambiar casilla[" + casillaMf+ "][" + casillaMc +"] por " + nuevoValorCasilla;
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadradoMagico eC=(EstadoCuadradoMagico) es;
            int [][] c = eC.getCuadrado();
            int [][] cI= eC.getCuadradoInicial();
            int N=c.length;
            if(nuevoValorCasilla>N*N||nuevoValorCasilla<=0||casillaMf<0||casillaMf>N||casillaMc<0||casillaMc>N){//casilla a modificar y nuevo valor válidos
                return false;
            }
            if(cI[casillaMf][casillaMc]!=0){//comprueba si se puede o no modificar el valor de esa casilla
                return false;
            }
            if(cI[casillaMf][casillaMc]==0) {
                for (int i = 0; i < N; i++) {//comprueba si el nuevo valor existe en el caudrado
                    for (int j = 0; j < N; j++) {
                        if (cI[i][j] == nuevoValorCasilla) {//evita usar los valores asignados en el estado inicial
                            return false;
                        }
                    }
                }
                return  true;
            }
            return false;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadradoMagico eC = (EstadoCuadradoMagico) es;

            int [][] c = new int[eC.getCuadrado().length][eC.getCuadrado().length];
            int [][] cI= new int[eC.getCuadradoInicial().length][eC.getCuadradoInicial().length];
            for (int i = 0; i < eC.getCuadrado().length; i++){
                for (int j = 0; j < eC.getCuadrado().length; j++){
                    c[i][j] = eC.getCuadrado()[i][j];
                    cI[i][j]= eC.getCuadradoInicial()[i][j];
                }
            }

            int N=c.length;
            int fil=casillaMf,col=casillaMc;
            boolean esta=false;


            for (int i = 0; i < N; i++) {//comprueba si el nuevo valor existe en el cuadrado
                for (int j = 0; j < N; j++) {
                    if(c[i][j]==nuevoValorCasilla){
                        esta =true;
                        fil=i;col=j;
                        break;
                    }
                }
                if(esta) break;
            }
            if(esta){//si el valor existe intercambio las casillas(solo si se puede modificar la casilla)
                int aux = c[fil][col];
                c[fil][col]=c[casillaMf][casillaMc];
                c[casillaMf][casillaMc]= aux;
            }
            else{ //si no existe se cambia directamente
                c[casillaMf][casillaMc]=nuevoValorCasilla;
            }
            System.out.println("El estado actual sigue siendo " + Arrays.deepToString(((EstadoCuadradoMagico) es).cuadrado));
            return new EstadoCuadradoMagico(c,cI);
        }
    }

    private Accion[] listaAcciones;

    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
        ArrayList<AccionCuadrado> acciones = new ArrayList<>();
        EstadoCuadradoMagico eC=(EstadoCuadradoMagico) estadoInicial;
        int N=eC.getCuadrado().length;
        for(int i=0;i<N;i++){//por cada elemento de la matriz se pueden hacer N cambios de valor (N*N*N acciones)
            for(int j=0;j<N;j++){
                for(int h=1;h<N*N+1;h++){
                    acciones.add(new AccionCuadrado(h,i,j));
                }
            }
        }
        int i=0;
        Accion [] lista= new Accion[acciones.size()];
        for (AccionCuadrado a:acciones) {
            lista[i]=a;
            i++;
        }
        listaAcciones =lista;
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico eC=(EstadoCuadradoMagico) es;
        int [][] c = eC.getCuadrado();
        int N=c.length;
        int sum=N*((N*N)+1)/2;//Suma de todos lo numeros desde 1 asta N
        int sumF=0,sumC=0,sumD1=0,sumD2=0;
        for(int i=0;i<N;i++){
            for (int j =0; j<N;j++){//compruebo si todas las filas y columnas suman lo mismo
                if(c[i][j]==0){
                    return false;//aún existen huecos vacios
                }
                sumF+=c[i][j];
                sumC+=c[j][i];
            }
            if(sumF!=sum || sumC!=sum){//Si sumF o sumC no son iguales a sum no es meta
                return false;
            }
            sumF=0;sumC=0;
        }
        for(int i=0;i<N;i++){
            sumD1+=c[i][i];
        }
        for(int i=0;i<N;i++){
            sumD2+=c[N-1-i][i];
        }
        return (sumD1 == sum && sumD2 == sum);
    }

    @Override
    public Accion[] acciones(Estado es) {
        return listaAcciones;
    }
}

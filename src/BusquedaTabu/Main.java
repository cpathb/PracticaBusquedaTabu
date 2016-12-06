package BusquedaTabu;

import java.util.*;

public class Main {
    public static final int ciudades = 100; // Definimos en esta constante el número de ciudades que habrá en la búsqueda
    public static int maxVecinos = ((ciudades-1)*(ciudades-2)/2); // Variable global que lleva cuenta de el número de vecinos que hemos generado
    public static int posAleatorios = 0; // Variable global que lleva cuenta de el número de aleaorios que se han empleado
    public static int distanciaSolucion=0;
    public static int distanciaSolucionOptima=0;
    public static int noMejora=0;
    public static int reinicio=1;
    public static int iteracionMejorSolucion=1;
    public static List<Integer> solucion    = new ArrayList<Integer>();
    public static List<Integer> solucionOptima    = new ArrayList<Integer>();
    public static List<List<Integer>> listaTabu = new ArrayList<List<Integer>>();
    public static List<Integer> distancias  = new ArrayList<Integer>();
    public static List<Integer> aleatorios  = new ArrayList<Integer>(); // Array que guardará enteros entre 0 y 8 leidos de un fichero


    public static void main(String[] args) {
        // Inicio etapa de inicialización
        int i=1;
        if(args.length==0){ // No se pasa ningún argumento
            Operations.distancias("distancias.txt");
            Operations.aleatorio();
            Operations.sobreescribirContenidoLista(solucion,solucionOptima);
            distanciaSolucion=Operations.calculoDistancia(solucion);
            distanciaSolucionOptima=distanciaSolucion;
            System.out.println("RECORRIDO INICIAL");
            System.out.print("\tRECORRIDO: ");
            Operations.printSolution(solucion);
            System.out.println("\tCOSTE (km): "+ distanciaSolucion);
            while(i<=10000){
                System.out.println("\nITERACION: "+i);
                Operations.generarVecinos(i);
                if(noMejora==100){
                    System.out.println("***************");
                    System.out.println("REINICIO: "+reinicio);
                    System.out.println("***************");
                    reinicio++;
                    noMejora=0;
                    Operations.reinicializarListaTabu();
                    Operations.sobreescribirContenidoLista(solucionOptima,solucion);
                    distanciaSolucion=distanciaSolucionOptima;
                }
                i++;
            }

            System.out.println("\nMEJOR SOLUCION: ");
            System.out.print("\tRECORRIDO: ");
            Operations.printSolution(solucionOptima);
            System.out.println("\tCOSTE (km): "+ distanciaSolucionOptima);
            System.out.println("\tITERACION: "+ iteracionMejorSolucion);
        }
        else{
            if(args.length == 1){ // Se introdujo solo el archivo de distancias como argumento
                Operations.distancias(args[0]);
                Operations.aleatorio();
                Operations.sobreescribirContenidoLista(solucion,solucionOptima);
                distanciaSolucion=Operations.calculoDistancia(solucion);
                distanciaSolucionOptima=distanciaSolucion;
                System.out.println("RECORRIDO INICIAL");
                System.out.print("\tRECORRIDO: ");
                Operations.printSolution(solucion);
                System.out.println("\tCOSTE (km): "+ distanciaSolucion);
                while(i<=10000){
                    System.out.println("\nITERACION: "+i);
                    Operations.generarVecinos(i);
                    if(noMejora==100){
                        System.out.println("***************");
                        System.out.println("REINICIO: "+reinicio);
                        System.out.println("***************");
                        reinicio++;
                        noMejora=0;
                        Operations.reinicializarListaTabu();
                        Operations.sobreescribirContenidoLista(solucionOptima,solucion);
                        distanciaSolucion=distanciaSolucionOptima;
                    }
                    i++;
                }

                System.out.println("\nMEJOR SOLUCION: ");
                System.out.print("\tRECORRIDO: ");
                Operations.printSolution(solucionOptima);
                System.out.println("\tCOSTE (km): "+ distanciaSolucionOptima);
                System.out.println("\tITERACION: "+ iteracionMejorSolucion);
            }
            else{ // Se introdujo el archivo de distancias y el de aleatorios como argumento
                Operations.distancias(args[0]);
                Operations.aleatorio(args[1]);
                Operations.sobreescribirContenidoLista(solucion,solucionOptima);
                distanciaSolucion=Operations.calculoDistancia(solucion);
                distanciaSolucionOptima=distanciaSolucion;
                System.out.println("RECORRIDO INICIAL");
                System.out.print("\tRECORRIDO: ");
                Operations.printSolution(solucion);
                System.out.println("\tCOSTE (km): "+ distanciaSolucion);
                while(i<=10000){
                    System.out.println("\nITERACION: "+i);
                    Operations.generarVecinos(i);
                    if(noMejora==100){
                        System.out.println("\n***************");
                        System.out.println("REINICIO: "+reinicio);
                        System.out.println("***************");
                        reinicio++;
                        noMejora=0;
                        Operations.reinicializarListaTabu();
                        Operations.sobreescribirContenidoLista(solucionOptima,solucion);
                        distanciaSolucion=distanciaSolucionOptima;
                    }
                    i++;
                }

                System.out.println("\n\nMEJOR SOLUCION: ");
                System.out.print("\tRECORRIDO: ");
                Operations.printSolution(solucionOptima);
                System.out.println("\tCOSTE (km): "+ distanciaSolucionOptima);
                System.out.println("\tITERACION: "+ iteracionMejorSolucion);
            }
        }
    }
}
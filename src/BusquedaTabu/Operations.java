package BusquedaTabu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Operations{

    /*
        Función que mete los valores de las distancias presentes en el archivo ciudades en una lista.
    */
    public static void distancias(String FileName){
        try{
            BufferedReader br = new BufferedReader(new FileReader(FileName)); // Abrimos el archivo
            int i=0, j=0;
            String line;
            String linesplitted[];
            while(i<Main.ciudades-1){
                line = br.readLine();
                linesplitted=line.split("\t");
                while(j<linesplitted.length){
                    Main.distancias.add(Integer.parseInt(linesplitted[j]));
                    j++;
                }
                j=0;
                i++;
            }
            br.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Función que realiza una inicialización de la lista Solucion tomando valores aleatorios generados.
        Existen condiciones especiales, si el aleatorio que se obtiene en la lista está generado, se incrementará en 1 su valor y se comprobará si ya está empleado,
        esto se hace para evitar tener que generar aleatorios demasiadas veces para una misma posición de la lista.
    */
    public static void aleatorio(){
        int i=0;
        Double valor;
        while(i<Main.ciudades-1){ // Mientras no asociemos todas las ciudades a la solución
            valor=random();

            int randomNumber= (int)(floor((valor*(Main.ciudades-1)))) + 1;
            if(!Main.solucion.contains(randomNumber)){
                Main.solucion.add(randomNumber);
            }
            else{
                while(Main.solucion.contains(randomNumber)){
                    if(randomNumber==(Main.ciudades-1)){ // Si es la ultima ciudad posible, la siguiente será la primera
                        randomNumber=1;
                    }
                    else{
                        randomNumber++; // Cuando la ciudad ya existe, se intenta asignar la siguiente inmediata
                    }
                }
                Main.solucion.add(randomNumber);
            }
            i++;
        }
    }

    /*
        Función que realiza una inicialización de la lista Solucion tomando valores aleatorios de un archivo. El archivo solo se leerá si no se ha leido anteriormente y almacenado en la lista de aleatorios.
        Existen condiciones especiales, si el aleatorio que se obtiene en la lista está generado, se incrementará en 1 su valor y se comprobará si ya está empleado,
        esto se hace para evitar tener que generar aleatorios demasiadas veces para una misma posición de la lista.
    */
    public static void aleatorio(String FileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName)); // Abrimos el archivo
            int i = 0,randomNumber;
            String line;
            Double valor;
            if(Main.aleatorios.size()==0){ // Si no se inicializó el array de aleatorios lo inicializamos
                line = br.readLine();
                while(line!=null){
                    valor = Double.parseDouble(line);
                    randomNumber = (int) (floor((valor * (Main.ciudades-1))));
                    Main.aleatorios.add(randomNumber);
                    line = br.readLine();
                }
                br.close();
            }
            while (i < Main.ciudades - 1) { // Mientras no asociemos todas las ciudades a la solución
                randomNumber = (Main.aleatorios.get(Main.posAleatorios))+1;
                if (!Main.solucion.contains(randomNumber)) {
                    Main.solucion.add(randomNumber);
                    Main.posAleatorios++;
                }
                else {
                    while (Main.solucion.contains(randomNumber)) {
                        if (randomNumber == (Main.ciudades - 1)) { // Si es la ultima ciudad posible, la siguiente será la primera
                            randomNumber = 1;
                        } else {
                            randomNumber++; // Cuando la ciudad ya existe, se intenta asignar la siguiente inmediata
                        }
                    }
                    Main.solucion.add(randomNumber);
                    Main.posAleatorios++;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Función para convertir un par de números a una posición en la lista, tanto de distancias como de vecinos generados
    */
    private static int conversorTuplaPosicion(int a, int b){
        int mayor, menor, posicion=0,i=1;
        if(a>b){
            mayor=a;
            menor=b;
        }
        else{
            mayor=b;
            menor=a;
        }

        while(i<mayor){
            posicion+=i;
            i++;
        }

        posicion+=menor;

        return posicion;
    }

    /*
        Función para realizar la exploración de los vecinos de una solución y actualizar esta con el mejor de ellos
    */
    public static void generarVecinos(int numIteracion){
        int i=1,j=1,k=0;
        List<Integer> mejorVecino=new ArrayList<Integer>();
        int distanciaMejorVecino=0;
        List<Integer> intercambioMejor=new ArrayList<Integer>();
        List<Integer> vecino=new ArrayList<Integer>();
        int distanciaVecino=0;
        List<Integer> intercambio=new ArrayList<Integer>();

        while(i<Main.maxVecinos){
            while(j<Main.ciudades-1){
                while(k<j){
                    reiniciarVecino(intercambio);
                    intercambio.add(i);
                    intercambio.add(j);
                    if(!Main.listaTabu.contains(intercambio)) {
                        if (mejorVecino.isEmpty()) {
                            intercambiarIndices(Main.solucion, mejorVecino, j, k);
                            distanciaMejorVecino = calculoDistancia(mejorVecino);
                            intercambioMejor.add(i);
                            intercambioMejor.add(j);
                        }
                        else {
                            reiniciarVecino(vecino);
                            intercambiarIndices(Main.solucion, vecino, j, k);
                            distanciaVecino = calculoDistancia(vecino);
                            if (distanciaVecino < distanciaMejorVecino) {
                                sobreescribirContenidoLista(vecino, mejorVecino);
                                distanciaMejorVecino = distanciaVecino;
                                reiniciarVecino(intercambioMejor);
                                intercambioMejor.add(i);
                                intercambioMejor.add(j);
                            }
                        }
                    }
                    i++;
                    k++;
                }
                k=0;
                j++;
            }
        }
        añadirTabu(intercambioMejor);
        sobreescribirContenidoLista(mejorVecino,Main.solucion);
        Main.distanciaSolucion=distanciaMejorVecino;
        if(Main.distanciaSolucion<Main.distanciaSolucionOptima){
            sobreescribirContenidoLista(Main.solucion,Main.solucionOptima);
            Main.distanciaSolucionOptima=Main.distanciaSolucion;
            Main.noMejora=0;
            Main.iteracionMejorSolucion=numIteracion;
        }
        else{
            Main.noMejora++;
        }
    }
    /*
        Función para calcular el coste (Distancia total) de una solución
    */
    public static Integer calculoDistancia(List<Integer> Lista){
        Integer distancia=0;

        distancia=distancia+Main.distancias.get(conversorTuplaPosicion(Lista.get(0),0));
        distancia=distancia+Main.distancias.get(conversorTuplaPosicion(Lista.get(Lista.size()-1),0));
        int i=0;
        while(i<Main.ciudades-2){
            distancia=distancia+Main.distancias.get(conversorTuplaPosicion(Lista.get(i),Lista.get(i+1)));
            i++;
        }
        return distancia;
    }

    /*
        Función para imprimir una solución
    */
    public static void printSolution(List<Integer> Lista){
        int i=0;
        while(i<Lista.size()-1){
            System.out.print(Lista.get(i)+", ");
            i++;
        }
        System.out.print(Lista.get(i));
    }

    /*
        Función para reinicializar la lista tabú cuando sea necesario
    */
    public static void reinicializarListaTabu(){
        while(Main.listaTabu.size()!=0){
            Main.listaTabu.remove(0);
        }
    }

    /*
        Función para copiar el contenido de una lista a otra sustituyendo el contenido original
    */
    public static void sobreescribirContenidoLista(List<Integer> Origen, List<Integer> Destino){
        while(Destino.size()!=0){
            Destino.remove(0);
        }

        Destino.addAll(Origen);
    }



    private static void intercambiarIndices(List<Integer> Origen, List<Integer> Destino, int mayor, int menor){
        int i=0;
        while(i<Origen.size()){
            if(i==mayor){
                Destino.add(Origen.get(menor));
            }
            else{
                if(i==menor){
                    Destino.add(Origen.get(mayor));
                }
                else{
                    Destino.add(Origen.get(i));
                }
            }
            i++;
        }
    }

    private static void reiniciarVecino(List<Integer> vecino){
        while(vecino.size()!=0){
            vecino.remove(0);
        }
    }

    private static void añadirTabu(List<Integer> intercambio){
        if(Main.listaTabu.size()==100){
            Main.listaTabu.remove(0);
            Main.listaTabu.add(intercambio);

        }
        else{
            Main.listaTabu.add(intercambio);
        }
    }
}

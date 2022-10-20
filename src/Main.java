import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        /*------CLASE GESTORARCHIVOS--------*/
        GestorArchivos gestorArchivos = new GestorArchivos();

        String url_1= "Beethoven.txt";
        String url_2= "L-gante.txt";

      //Cámbie el valor de las url´s para imprimir los distintos archivos
        gestorArchivos.leerDatos(url_2);
        int cantidadSimbolos = gestorArchivos.cantSimbolos();
        //Necesito saber la cantidad de simbolos distintos que tengo para poder generar mi matriz
        int matrizDatos[][] = new int[2][cantidadSimbolos];

        //Inicializo la matriz
        for (int i = 0; i < cantidadSimbolos; i++) {
            matrizDatos[0][i] = -1;
            matrizDatos[1][i] = -1;
        }
        gestorArchivos.setMatrizDato(matrizDatos);

        //Metodos para completar la matriz y asi encontrar la distribución de las probabilidades
        gestorArchivos.operarDatos();
        gestorArchivos.imprimirMatriz();


        /*------CLASE LISTATUPLA--------*/
        ListaTupla listTuplas = new ListaTupla();
        gestorArchivos.generarListaTuplas(listTuplas);
        //En este metodo, se ordenan las tuplas en funcion de la probabilidad de mayor a menor
        listTuplas.sort();
        //Se crea la lista de codigos y luego se generan las referencia para cada simbolo
        ListaCodigo listCodigos = new ListaCodigo();
        for (int i = 0; i < listTuplas.getListTuplas().size(); i++) {
                CodigoSimple code = new CodigoSimple(listTuplas.getListTuplas().get(i));
                listCodigos.agregarCodigoSimple(code);
        }

        listTuplas.ejecutar(listCodigos);
        System.out.println("----Los codigos---");

        //Imprimo los codigos
        for (int i = 0; i < listCodigos.getListaCodigos().size(); i++) {
            listCodigos.getCodigoSimple(i).imprimirCodigo();
        }


      
        //----RESULTADOS NUMERICOS--------
        System.out.println("----Cantidad de bits---");
        int cantidad = gestorArchivos.calcularEspacio(listCodigos);
        System.out.println("La cantidad de bits es: "+ cantidad);

        double entropia = gestorArchivos.calcularEntropia();
        System.out.println("La Entropia del archivo es: "+ entropia);

        double longMedia = gestorArchivos.calcularLongitudMedia(listCodigos);
        System.out.println("La longitud media del archivo es: "+ longMedia);

    }
}
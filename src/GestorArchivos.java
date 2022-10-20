import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
    private List<Integer> simbolos;
    private List<Integer> datosArchivo;
    private int matrizDato [][];

  //----CONSTRUCTOR------
    public GestorArchivos() {
        this.simbolos = new ArrayList<>();
        matrizDato = null;
        this.datosArchivo = new ArrayList<>();
    }

  
  //----GETTERS AND SETTERS
    public void setMatrizDato(int[][] matrizDato) {
        this.matrizDato = matrizDato;
    }
  
    public int getPos(int dato){
        for (int i = 0; i < simbolos.size(); i++) {
            if(matrizDato[0][i] == dato)
                return i;
        }
        return -1;
    }
    
    //----METODOS---------
  
    public void leerDatos(String ruta){
    //Leo los datos, los paso a valores enteros
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(ruta));
            String temp = "";
            String bfRead;

            while ((bfRead = entrada.readLine()) != null){
                // Leo la entrada
                temp= bfRead;
                // Paso el dato a valor entero
                int datoParce = Integer.parseInt(temp);
                // Copio los datos del archivo en un arreglo, para despues operar
                datosArchivo.add(datoParce);

                //Funcion que guarda los elementos que no se repiten
                insertarDatoNoRepetido(datoParce);
            }
        } catch (IOException e) {
            //No se encontro el archivo
            System.out.println("No se encontro el archivo");
        }
    }
  
    public void operarDatos(){
        // Este metodo, completa la matriz con los simbolos y su frecuencia
        //Leo mi arreglo y completo la matriz
        int dato = 0;
        int index = -1;
        for (int i = 0; i < datosArchivo.size(); i++) {
            dato = datosArchivo.get(i);
            //Insertar en la Matriz y contar los repetidos
            index = getPos(dato);
            if (index!= -1){
                matrizDato[1][index] = matrizDato[1][index] +1;
            }else {
                //Supongo que la matriz esta llena de -1's
                int j = 0;
                while (j < simbolos.size()) {
                    if(matrizDato[0][j]== -1){
                        matrizDato[0][j] = dato;
                        matrizDato[1][j] = 1;
                        j = simbolos.size();
                    }
                    j++;
                }
            }

        }

    }

    public void insertarOrdenado(int dato){
        if (simbolos.isEmpty()){
            simbolos.add(dato);
            return;
        }
        else{
            for (int i = 0; i < simbolos.size(); i++) {
                if (simbolos.get(i) > dato) {
                    simbolos.add(i, dato);
                    return;
                }
            }
        }
        simbolos.add(dato);
    }
  
    public void insertarDatoNoRepetido(int dato){
      //Este metodo acumula los simbolos que son distintos
        if(!simbolos.contains(dato)) {
            simbolos.add(dato);
        }
    }

    public int cantSimbolos(){
        return simbolos.size();
    }

    public void imprimirDatos(){
        for (int i = 0; i < simbolos.size(); i++) {
            System.out.print("->"+ simbolos.get(i));
        }
    }
  
    public void imprimirMatriz(){
      for (int i = 0; i < simbolos.size(); i++) {
          System.out.println("Simbolo: " + matrizDato[0][i] + ", Probabilidad: " + matrizDato[1][i] + "/" + datosArchivo.size());
      }
    }

    public void generarListaTuplas (ListaTupla listaTupla){
        int dato;
        double datoDouble;
        for (int i = 0; i < simbolos.size(); i++) {
            //Leo la frecuencia del simbolo en el archivo
            dato = matrizDato[1][i];
            datoDouble= (double)dato;
            Tupla nuevaTupla = new Tupla(datoDouble/datosArchivo.size(),matrizDato[0][i]);
            listaTupla.addTupla(nuevaTupla);
        }
    }

    public int cantRepetidos(int dato){
      //Con este metodo devuelvo la frecuencia del archivo, se encuntra la frecuencia en la fila 2 de la matriz
        for (int i = 0; i < simbolos.size(); i++) {
            if (dato == matrizDato[0][i])
                return matrizDato[1][i];
        }
        return -1;
    }

    public double calcularEntropia(){
        double datoParce = 0.0;
        double logBn= 0.0;
        for (int i = 0; i < simbolos.size(); i++) {
            datoParce = (double) matrizDato[1][i]/datosArchivo.size();
            logBn +=-Math.log(datoParce)/Math.log(2.0) * datoParce;
        }
        return logBn;
    }

    public double calcularLongitudMedia(ListaCodigo listaCodigo){
        double datoParce = 0.0;
        double longMedia = 0.0;
        for (int i = 0; i < simbolos.size(); i++) {
            datoParce = (double) matrizDato[1][i] / datosArchivo.size();
            longMedia += datoParce* listaCodigo.cantidadBits(matrizDato[0][i]);
        }
        return  longMedia;
    }

    public int calcularEspacio (ListaCodigo listaCodigos){
        int suma = 0;
        int repeticiones;
        int valorTupla;
        for (int i = 0; i< listaCodigos.getListaCodigos().size(); i++){
            //debería buscar la tupla asociada, el nuemro
            valorTupla = listaCodigos.getListaCodigos().get(i).getTuplaAsociada().getSimboloAsociado();
            //ir a buscar en la matriz para saber cuantas veces se repite
            repeticiones = cantRepetidos(valorTupla);
            //luego multiplicar la longitud de L(i) * lo que me retorna la suma
            suma = suma + (repeticiones* listaCodigos.getListaCodigos().get(i).getCantBits());
        }
        return suma;
    }
}
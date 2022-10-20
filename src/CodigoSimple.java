import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CodigoSimple {
    private List<Integer> codigoBinario;
    private Tupla tuplaAsociada;

    //----CONSTRUCTORES----------
  
    public CodigoSimple() {
        this.codigoBinario = new ArrayList<>();
    }

    public CodigoSimple(Tupla tuplaAsociada) {
        this.codigoBinario = new ArrayList<>();
        this.tuplaAsociada = tuplaAsociada;
    }
  
    public CodigoSimple(List<Integer> codigoBinario, Tupla tuplaAsociada) {
        this.codigoBinario = codigoBinario;
        this.tuplaAsociada = tuplaAsociada;
    }

  //----GETTERS AND SETTERS-------

    public Tupla getTuplaAsociada() {
        return tuplaAsociada;
    }

    public void setTuplaAsociada(Tupla tuplaAsociada) {
        this.tuplaAsociada = tuplaAsociada;
    }

    public List<Integer> getCodigoBinario() {
        return codigoBinario;
    }

    public void setCodigoBinario(List<Integer> codigoBinario) {
        this.codigoBinario = codigoBinario;
    }

    public void setBitCodigo(Integer bit) {
        codigoBinario.add(bit);
    }

  //----METODOS-----------

    public void imprimirCodigo(){
        int pos = codigoBinario.size() -1;
        while (pos >=0){
            System.out.print(codigoBinario.get(pos));
            pos--;
        }
        System.out.println(" Representación del simbolo : "+ tuplaAsociada.getSimboloAsociado());
    }

    public int getCantBits(){
        return codigoBinario.size();
    }

}

import java.util.ArrayList;
import java.util.List;

public class Tupla {
    private double probabilidadSigno;
    private List<Integer> referenciaPos;
    private int simboloAsociado;

  //----CONSTRUCTORES----------
    public Tupla(double probabilidadSigno, int simboloAsociado) {
        this.probabilidadSigno = probabilidadSigno;
        this.referenciaPos = new ArrayList<>();
        this.simboloAsociado = simboloAsociado;
    }

    public Tupla(double probabilidadSigno) {
        this.probabilidadSigno = probabilidadSigno;
        this.referenciaPos = new ArrayList<>();
    }

  //----GETTERS Y SETTERS-------

    public void setReferenciaPos(List<Integer> referenciaPos) {
        this.referenciaPos = referenciaPos;
    }

    public int getSimboloAsociado() {
        return simboloAsociado;
    }

    public void setSimboloAsociado(int simboloAsociado) {
        this.simboloAsociado = simboloAsociado;
    }

    public void setProbabilidadSigno(double prob){
        probabilidadSigno = prob;
    }
  
    public double getProbabilidadSigno(){
        return probabilidadSigno;
    }

    public List<Integer> getReferenciaPos (){
        return  referenciaPos;
    }

    public int getElementPost(int index){
        return referenciaPos.get(index);
    }

  //----METODOS-----
  
    public void addPos(int pos){
        referenciaPos.add(pos);
    }

    public void addPosicionesTupla (Tupla tupla){
        for (int i = 0; i < tupla.getReferenciaPos().size(); i++) {
            referenciaPos.add(tupla.getElementPost(i));
        }
    }

    public void imprimirPosicionTuplas (){
        for (int i = 0; i < referenciaPos.size(); i++) {
            System.out.println(" - "+ referenciaPos.get(i) );
        }
    }
}

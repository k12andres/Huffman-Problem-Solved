import java.util.ArrayList;
import java.util.List;

public class ListaCodigo {
    private List<CodigoSimple> listaCodigos;

  //----CONSTRUCTOR------
    public ListaCodigo() {
        this.listaCodigos = new ArrayList<>();
    }
  

  //----GETTERS AND SETTERS------------

    public List<CodigoSimple> getListaCodigos() {
        return listaCodigos;
    }

    public void setListaCodigos(List<CodigoSimple> listaCodigos) {
        this.listaCodigos = listaCodigos;
    }

    public CodigoSimple getCodigoSimple (int pos){
        return listaCodigos.get(pos);
    }

  //----METODOS-----

    public void agregarCodigoSimple(CodigoSimple codigoSimple){
        listaCodigos.add(codigoSimple);
    }
  
    public void agregarSimboloCodigo(int pos, int bit){
        listaCodigos.get(pos).setBitCodigo(bit);
    }

    public int cantidadBits(int referenciaTupla){
        for (int i = 0; i < listaCodigos.size(); i++) {
            if (referenciaTupla == listaCodigos.get(i).getTuplaAsociada().getSimboloAsociado())
                return listaCodigos.get(i).getCantBits();
        }
        return -1;
    }

    public void imprimirListaCodigo(){
        for (int i = 0; i < listaCodigos.size(); i++) {
            System.out.print(listaCodigos.get(i).getTuplaAsociada().getProbabilidadSigno() + " --> ");
        }
    }
}

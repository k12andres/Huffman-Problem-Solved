import java.util.ArrayList;
import java.util.List;

public class ListaTupla {
    private List<Tupla> listTuplas;

  //---CINSTRUCTOR------

    public ListaTupla() {
        this.listTuplas = new ArrayList<>();
    }


  //----GETTERS AND SETTERS-------

    public Tupla getTupla(int index){
        return listTuplas.get(index);
    }

    public List<Tupla> getListTuplas() {
        return listTuplas;
    }


  //----METODOS-----
  
    public void addTupla(Tupla tupla){
          listTuplas.add(tupla);
      }

    public void ejecutar(ListaCodigo listaCodigo){
      //Ejecuta hasta que la lista sea de longitud 1
          while (listTuplas.size()>1)
              removeAndSum(listaCodigo);
      }

    public void removeAndSum(ListaCodigo listaCodigo){
      //Obtengo mi ultima pos y recupero las ultimas tuplas
        int index = listTuplas.size() -1;
        Tupla aux = listTuplas.get(index);
        Tupla aux2 = listTuplas.get(index-1);

        // Coloco 0 y 1 dependiendo de las pos
        for (int i = 0; i < aux.getReferenciaPos().size(); i++) {
            int posCodigo = aux.getReferenciaPos().get(i);
            listaCodigo.agregarSimboloCodigo(posCodigo, 0);
        }
        for (int j = 0; j < aux2.getReferenciaPos().size(); j++) {
            int pos = aux2.getReferenciaPos().get(j);
            listaCodigo.agregarSimboloCodigo(pos, 1);
        }
        //Sumo las tuplas
        double sum = aux.getProbabilidadSigno() + aux2.getProbabilidadSigno();
        //creo una nueva tupla
        Tupla nuevaTupla = new Tupla(sum);
        // Agrego las nuevas pociciones a las tuplas
        nuevaTupla.addPosicionesTupla(aux);
        nuevaTupla.addPosicionesTupla(aux2);
        listTuplas.remove(index);
        listTuplas.remove(index-1);
        // Agrego la nueva tupla ordenada, para no perder la consistencia
        insertarOrdenado(nuevaTupla);
    }

    public void insertarOrdenado(Tupla tupla){
          for (int i = 0; i < listTuplas.size(); i++) {
              if (listTuplas.get(i).getProbabilidadSigno() < tupla.getProbabilidadSigno()){
                  listTuplas.add(i, tupla);
                  return;
              }
          }
          listTuplas.add(tupla);
      }

    public void imprimir(){
        for (int i = 0; i < listTuplas.size(); i++) {
            System.out.print("La tupla con valor: "+listTuplas.get(i).getSimboloAsociado() + ", "+ listTuplas.get(i).getProbabilidadSigno() + "--> ");
        }
    }

    public void sort (){
        Tupla auxiliar;
        for(int i = 0;i < listTuplas.size()-1;i++){
            for(int j = 0;j < listTuplas.size()-i-1;j++){
                if(listTuplas.get(j+1).getProbabilidadSigno() >  listTuplas.get(j).getProbabilidadSigno()){
                    auxiliar = listTuplas.get(j+1);
                    listTuplas.set(j+1,listTuplas.get(j));
                    listTuplas.set(j,auxiliar);
                }
            }
        }
        // configuro las posiciones iniciales de mi nuevo arreglo de codigos
        for (int k = 0; k < listTuplas.size(); k++) {
            listTuplas.get(k).addPos(k);
        }
    }
}

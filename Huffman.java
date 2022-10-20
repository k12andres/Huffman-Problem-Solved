import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.lang.Math;


public class Huffman{
	private static ArrayList<String> Ordenado = new ArrayList<String>();
	private static ArrayList<String> Original = new ArrayList<String>();
	List<String> bits = new ArrayList<String>();
	String artista;
	
    public Huffman(String a){
		artista=a;
	}
	
	public void Escribir_Archivo(float probabilidades[], List<String> NumUnicos, double valor){
		PrintWriter printWriter = null;
		try {
        	printWriter = new PrintWriter(artista);
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo :( : " + e.getMessage());
        }
		for (int cursor=0; cursor<probabilidades.length-1; cursor++){
			Objects.requireNonNull(printWriter).println("Simbolo: " + NumUnicos.get(cursor) + " - Probabilidad: " + probabilidades[cursor]);
		}
		Objects.requireNonNull(printWriter).println(valor);
        printWriter.close();
	}
	
    public void Lectura_Ordenamiento_Archivos(String arch){
		File archivo = null;
    	FileReader fr = null;
    	BufferedReader br = null;
    	try {
        	archivo = new File(arch);
        	fr = new FileReader(archivo.toString());
        	br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
            	Original.add(linea);
            	Ordenado.add(linea);
        	}
		}
		catch (IOException e) {
        	System.out.println(e);
    	}
		Collections.sort(Ordenado);
    }

	public void DistribucionProbabilidades(){
		int contador=0;
		int recorredor=0;
		int siguiente=1;
		List<String> NumUnicos = Ordenado.stream().distinct().collect(Collectors.toList());
		int largo = NumUnicos.size();
		float proba[] = new float[largo];
		float rango[] = new float[largo];
		float probaIndividual =(float) (1.0/Ordenado.size());
		proba[0]=probaIndividual;
		
		while (recorredor<(Ordenado.size()-1)){
			rango[contador]=(float)(recorredor+1)/Ordenado.size(); 
			
			if (Ordenado.get(recorredor).equals(Ordenado.get(siguiente))){
				proba[contador]=(float)proba[contador] + probaIndividual;
			} else {
				contador++;
				proba[contador]=(float)probaIndividual;
			}
			recorredor++;
			siguiente++;
		}
		Escribir_Archivo(proba, NumUnicos, ProbabilidadConRango(rango, NumUnicos));
		bitSize(NumUnicos, proba);
	}
	
	public double ProbabilidadConRango(float[] rango, List<String> NumUnicos){
		double low = 0.0; 		
		double high = 1.0;
		double range = 0;
		int index;
		
		for (int i = 0; i < Original.size(); i++){
			range = high - low;
			index = NumUnicos.indexOf(Original.get(i));
			high = low + range * rango[index];
			low = low + range * rango[index];
		}
		System.out.println("Codigo Huffman: " + low);
		return low;
	}

	class ImplementComparator implements Comparator<HuffmanNode> {
		public int compare(HuffmanNode x, HuffmanNode y) {
		    return x.proba - y.proba;
		}
	}
	
	public static void getcode(HuffmanNode root, String s, String elem, List<String> bits) {
	    if (root.izq == null && root.der == null && root.elemento==elem) {
			bits.add(s);
	      	return;
	    }
		if (root.izq!=null){
	    	getcode(root.izq, s + "0", elem, bits);
		} 
		if (root.der!=null) {
			getcode(root.der, s + "1", elem, bits);
		}
	}

	public void bitSize (List<String> NumUnicos, float probabilidades[]){
		int n=probabilidades.length;
		int probabilidadesAux[] = new int[n];
		float resultado;
		
		for (int cursor=0; cursor<probabilidades.length; cursor++ ){
			resultado = probabilidades[cursor]*probabilidades.length;
			probabilidadesAux[cursor]=(int)resultado;
		}

		PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(n, new ImplementComparator());
		
		for (int i = 0; i < n; i++) {
			HuffmanNode hn = new HuffmanNode();
			hn.elemento = NumUnicos.get(i);
			hn.proba = probabilidadesAux[i];
			hn.izq = null;
			hn.der = null;
			queue.add(hn);
		}

		HuffmanNode root = null;

		while (queue.size() > 1) {

			HuffmanNode x = queue.peek();
			queue.poll();

			HuffmanNode y = queue.peek();
			queue.poll();

			HuffmanNode nuevo = new HuffmanNode();

			nuevo.proba = x.proba + y.proba;
			nuevo.elemento = "-";
			nuevo.der = y;
			nuevo.izq = x;
			root = nuevo;

			queue.add(nuevo);
		}

		for (int cursor = 0; cursor<NumUnicos.size(); cursor++){
			getcode(root, "", NumUnicos.get(cursor), bits);
		}
		String valorBit="";
		for (int cursor = 0; cursor<Original.size(); cursor++){
			int recorredor=0;
			while (Original.get(cursor)!=bits.get(recorredor) && recorredor < bits.size()-1){
				recorredor++;
			}
			valorBit=valorBit+bits.get(recorredor);
		}
		//System.out.println(valorBit);
		obtenerRendimiento(bits, probabilidades);
	}

	public static void obtenerRendimiento (List<String> bits, float probabilidades[]){
		double h=0;
		float longitudMedia=0;
		for (int cursor = 0; cursor<bits.size(); cursor++){
			longitudMedia=(float) longitudMedia+(bits.get(cursor).length()*probabilidades[cursor]);
			h=(float)h + (probabilidades[cursor] * (-Math.log10(probabilidades[cursor])));
		}
		System.out.println("Rendimiento: " + h/longitudMedia);
	}
    public void arch() throws Exception{
        File doc = new File("./Fuentes/Beethoven");
        try (Scanner obj = new Scanner(doc)) {
			if (obj.hasNextLine())
			    System.out.println(obj.nextLine());
		}
    }
}

//Bibliografia utilizada:
//http://www.huffmancoding.com/my-uncle/scientific-american
//http://compression.ru/download/articles/huff/huffman_1952_minimum-redundancy-codes.pdf
//http://hlevkin.com/hlevkin/02imageprocC/The%20Data%20Compression%20Book%202nd%20edition.pdf
//https://webspace.science.uu.nl/~leeuw112/huffman.pdf

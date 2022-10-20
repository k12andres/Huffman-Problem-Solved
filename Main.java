class Main {
	public static void main(String[] args) throws Exception {
		String beethoven="./Fuentes/Beethoven";
		String lgante= "./Fuentes/L-gante";
/*		
		System.out.println("Datos Beethoven: ");
		Huffman huffmandebeethoven = new Huffman("beethoven");
	    huffmandebeethoven.Lectura_Ordenamiento_Archivos(beethoven);
		huffmandebeethoven.DistribucionProbabilidades();
*/
		System.out.println("Datos L-gante: ");
	    Huffman huffmandelgante = new Huffman("lgante");
	    huffmandelgante.Lectura_Ordenamiento_Archivos(lgante);
		huffmandelgante.DistribucionProbabilidades();	
  }
}

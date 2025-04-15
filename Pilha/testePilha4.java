public class testePilha4 { //Questão 4

	public static void main(String[] args) {			
		PilhaArray arrayPilha = new PilhaArray(1,0);
		
		System.out.println("inserindo");
		for(int numero = 1; numero <= 10; numero++){  
			System.out.println(numero);
		  	arrayPilha.push(new Integer(numero));
		}

		System.out.println("tamanho antes: " + arrayPilha.size());
		arrayPilha.empty();

	}
}

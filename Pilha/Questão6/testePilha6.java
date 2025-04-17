package Questão6;
public class testePilha6 { //Questão 6

	public static void main(String[] args) {		
		PilhaVector arrayVetor = new PilhaVector();
		System.out.println("está vazio:" + arrayVetor.isEmpty());
		System.out.println("inserindo");
		for(int numero = 1; numero <= 10; numero++){  
			System.out.println(numero);
		  	arrayVetor.push(new Integer(numero));
		}

		System.out.println("tamanho:" + arrayVetor.size());
		System.out.println("retirando");
		for(int f=1;f<=5;f++){
			  System.out.print(f);
			  System.out.println(" - "+ arrayVetor.pop());
		}

		System.out.println("tamanho:" + arrayVetor.size());
		System.out.println("ultimo elemento:" + arrayVetor.top());
	}
}

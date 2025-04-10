
public class testePilha {

	public static void main(String[] args) {		
		//Integer[] b = new Integer[1];		
		PilhaArray arrayPilha = new PilhaArray(1,0);

		//long tempoInicial = System.currentTimeMillis();
		System.out.println("inserindo");
		for(int numero = 1; numero <= 10; numero++){  
			System.out.println(numero);
		  	arrayPilha.push(new Integer(numero));
		}
		//System.out.println("Tempo final:" + (System.currentTimeMillis() - tempoInicial));

		// System.out.println("retirando");
		// for(int f=0;f<10;f++){
		// 	  System.out.print(f);
		// 	  System.out.println(" - "+arrayPilha.pop());
		// }
	}
}

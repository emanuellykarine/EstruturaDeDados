package PilhaComArrayEmpty;
// Exceção
public class PilhaVaziaExcecao extends RuntimeException {
	 public PilhaVaziaExcecao(String err){
	    super(err); //Chama o construtor da classe, vai na classe mãe e chama no caso a RuntimeException	
	 }   
}

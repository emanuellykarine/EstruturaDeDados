package Fila.FilaComDuasPilhas;

public class FilaComDuasPilhas implements Fila{
    private int capacidade; //tamanho da fila
    private int incremento;
    private PilhaArray pilhaEntrada;
    private PilhaArray pilhaSaida;

    public FilaComDuasPilhas(int capacidade, int incremento) {
        this.capacidade = capacidade;
        this.incremento = incremento;
        this.pilhaEntrada = new PilhaArray(capacidade, incremento);
        this.pilhaSaida = new PilhaArray(capacidade, incremento);
    }

    public void enqueue(Object o){ 
        pilhaEntrada.push(o);
    }

    public Object dequeue() throws FilaVaziaExcecao{
        if (isEmpty()) {
            throw new FilaVaziaExcecao("Fila vazia.");
        }

        while (!pilhaEntrada.isEmpty()){
            pilhaSaida.push(pilhaEntrada.pop());
        }

        Object remover = pilhaSaida.pop();
        
        while (!pilhaSaida.isEmpty()){
            pilhaEntrada.push(pilhaSaida.pop());
        }
        
        return remover;
    }

    public Object first() throws FilaVaziaExcecao{
        if (isEmpty()) {
            throw new FilaVaziaExcecao("Fila vazia");
        }

        while (!pilhaEntrada.isEmpty()){
            pilhaSaida.push(pilhaEntrada.pop());
        }

        Object primeiro = pilhaSaida.top();
        
        while (!pilhaSaida.isEmpty()){
            pilhaEntrada.push(pilhaSaida.pop());
        }
        
        return primeiro;
    } 

    public int size(){
        return pilhaEntrada.size();
    }

    public boolean isEmpty(){
        return pilhaEntrada.isEmpty();
    }
    
}

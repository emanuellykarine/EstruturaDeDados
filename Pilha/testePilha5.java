public class testePilha5 { //Questão 5
    public static void main(String[] args){
        PilhaVP coloridaPilha = new PilhaVP(3, 4);
        System.out.println("Pilha vermelha está vazia: " + coloridaPilha.isEmptyVermelha());
        System.out.println("Pilha preta está vazia: " + coloridaPilha.isEmptyPreta());

        coloridaPilha.pushVermelha(8);
        coloridaPilha.pushPreta(3);
        coloridaPilha.pushPreta(2);

        System.out.println("Tamanho pilha vermelha:" + coloridaPilha.sizeVermelha());
        System.out.println("Tamanho pilha preta:" + coloridaPilha.sizePreta());
        
        coloridaPilha.pushVermelha(5);
        
        System.out.println("Topo pilha vermelha:" + coloridaPilha.topVermelha());
        System.out.println("Topo pilha preta:" + coloridaPilha.topPreta());

        coloridaPilha.pushVermelha(4);
        System.out.println("Topo pilha vermelha:" + coloridaPilha.topVermelha());
        coloridaPilha.pushVermelha(7);
        System.out.println("Topo pilha vermelha:" + coloridaPilha.topVermelha());
        System.out.println("Topo pilha preta:" + coloridaPilha.topPreta());

    }
}

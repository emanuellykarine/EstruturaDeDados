public class ArvoreBClasse {
    private NoArvoreB raiz;

    //t é a ordem e nunca muda? ou t muda de acordo com a altura da arvore
    public ArvoreBClasse(int t) { //t é a ordem da arvore
        raiz = new NoArvoreB(t);
        raiz.setNumChaves(0);

    }

    
}

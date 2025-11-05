public class NoArvoreB {
    private int[] chaves;
    private int[] filhos;
    private int t; //ordem da arvore 
    private int numChaves;

    public NoArvoreB(int t){
        this.t = t;
        chaves = new int[t - 1];
        filhos = new int[t];
        numChaves = 0;
    }

    public int[] getChaves() {
        return chaves;
    }

    public void setChaves(int[] chaves) {
        this.chaves = chaves;
    }

    public int[] getFilhos() {
        return filhos;
    }

    public void setFilhos(int[] filhos) {
        this.filhos = filhos;
    }

    public int getNumChaves() {
        return numChaves;
    }

    public void setNumChaves(int numChaves) {
        this.numChaves = numChaves;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}

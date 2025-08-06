/*Double hashing is a collision resolution technique used in hash tables. 
It works by using two hash functions to compute two different hash values for a given key. 
The first hash function is used to compute the initial hash value, 
and the second hash function is used to compute the step size for the probing sequence.
 */

/* o tamanho primo é frequentemente escolhido para a função de dispersão (hash)
 devido à sua capacidade de reduzir colisões, distribuindo os dados
  de forma mais uniforme na tabela, ajuda a evitar agrupamento de chaves em posições específicas*/
package Hash;

import java.util.ArrayList;
import java.util.Iterator;

public class HashHashingDuplo implements Hash{
    private Item[] table;
    private int tamanho;
    private int capacidade;

    public HashHashingDuplo(int capacidade){
        if (isPrimo(capacidade)){
            this.capacidade = capacidade;
        } else {
            this.capacidade = proximoPrimo(capacidade);
        }
        this.table = new Item[this.capacidade];
        this.tamanho = 0;
    }

    public void insertItem(int chave, Object valor){
        if ((double) this.tamanho / this.capacidade > 0.75){ //fator de carga 0.75
            this.reHash();
        }

        Item novoItem = new Item(chave, valor);

        int index = hash(chave); //posição inicial na tabela
        int doubleIndex = doubleHash(chave); //passo a ser somado em caso de colisão
        int i = 0; //qtd de colisões

        while (table[index] != null && table[index].getChave() != chave) {
            i++;
            index = (hash(chave) + i * doubleIndex) % capacidade; //fórmula do double hash para encontrar o índice
        }

        if (table[index] == null || table[index] == Item.AVAILABLE) {
            table[index] = novoItem;
            tamanho++;
        } else {
            table[index].setValor(valor);
        }
    }

    public Object findElement(int chave){
        int index = hash(chave); //posição inicial na tabela
        int doubleIndex = doubleHash(chave); //passo a ser somado em caso de colisão
        int i = 0; //qtd de colisões

        while (table[index] != null) { //vê se tem algo naquela posição
            if (table[index] != Item.AVAILABLE && table[index].getChave() == chave) {
                return table[index].getValor();
            }
            i++;
            index = (hash(chave) + i * doubleIndex) % capacidade;
        }

        return "NO_SUCH_KEY";
    }

    public Object removeElement(int chave) {
        int index = hash(chave);
        int step = doubleHash(chave);
        int i = 0;

        while (table[index] != null && table[index].getChave() != chave) { //enquanto tiver algo na posição e a chave encontrada for diferente da que a gente quer
            i++;
            index = (hash(chave) + i * step) % capacidade;
        }

        if (table[index] == null) {
            return "NO_SUCH_KEY";
        } else {
            Object remover = table[index].getValor();
            table[index] = Item.AVAILABLE; // Marca como removido
            tamanho--;
            return remover;
        }
    }

    public void reHash() {
        Item[] tableAntiga = table;
        int novaCapacidade = proximoPrimo(this.capacidade * 2);

        this.table = new Item[novaCapacidade];
        this.capacidade = novaCapacidade;
        this.tamanho = 0;

        for (Item item : tableAntiga) {
            if (item != null && item != Item.AVAILABLE) {
                insertItem(item.getChave(), item.getValor());
            }
        }
    }

    public boolean isPrimo(int n){
        if (n <= 1) {
            return false;
        } else if (n == 2) {
            return true;
        } else {
            for (int i = 3; i <= Math.sqrt(n); i += 2) {
                if (n % i == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public int proximoPrimo(int n){
        int temp = n + 1;

        while (!isPrimo(temp)){
            temp++;
        }

        return temp;
    }

    public int hash(int chave) {
        return chave % capacidade;
    }

    public int doubleHash(int chave) {
        int primo = capacidade - 1;
        while (!isPrimo(primo)) {
            primo--;
        }

        int hash2 = primo - (chave % primo);
        return (hash2 == 0) ? 1 : hash2; // evita retorno 0 pra não ocasionar loop
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public Iterator<Integer> keys(){
        ArrayList<Integer> chaves = new ArrayList<>();
        if (this.tamanho != 0) {
            for (Item item : this.table) {
                if (item != null && item != Item.AVAILABLE) {
                    chaves.add(item.getChave());
                }
            }
        }
        return chaves.iterator();
    }

    public Iterator<Object> elements(){
        ArrayList<Object> elementos = new ArrayList<>();
        if (this.tamanho != 0) {
            for (Item item : this.table) {
                if (item != null && item != Item.AVAILABLE) {
                    elementos.add(item.getValor());
                }
            }
        }
        return elementos.iterator();
    }

}
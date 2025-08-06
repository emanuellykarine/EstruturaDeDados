/*In linear probing, the hash table is searched sequentially that starts from the original 
location of the hash. If in case the location that we get is already occupied, 
then we check for the next location.  */
package Hash;

import java.util.ArrayList;
import java.util.Iterator;

public class HashLinearProbing implements Hash{
    private Item[] table;
    private int tamanho;
    private int capacidade;

    public HashLinearProbing(int capacidade) {
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
        while (table[index] != null && table[index].getChave() != chave) {
            index = (hash(chave) + 1) % capacidade; //fórmula do double hash para encontrar o índice
        }

        if (table[index] == null || table[index] == Item.AVAILABLE) {
            table[index] = novoItem;
            tamanho++;
        } else {
            table[index].setValor(valor);
        }
    }

    public Object findElement(int chave){
        int index = hash(chave);
        int p = 0;
        while (p < this.table.length) {
            Item c = table[index];
            if (c == null || c == Item.AVAILABLE) {
                return "NO_SUCH_KEY";
            } else if (c.getChave() == chave) {
                return c.getValor();
            } else {
                index = (index + 1) % this.table.length;
                p++;
            }
        }

        return "NO_SUCH_KEY";
    }

    public Object removeElement(int chave) {
        int index = hash(chave);
        while (table[index] != null && table[index].getChave() != chave) {
            index = (hash(chave) + 1) % capacidade; //fórmula do double hash para encontrar o índice
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

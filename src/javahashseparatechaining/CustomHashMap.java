package javahashseparatechaining;
import java.util.LinkedList;

public class CustomHashMap<T> {
    private LinkedList<Dado<T>>[] tabela;
    private int tamanho;
    private int numElementos;
    private int comparacoes; // Implementar para apresentar o número de comparações necessárias para inserir, buscar e remover elementos
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static class Dado<T> {
        long key;
        T value;

        Dado(long key, T value) {
            this.key = key;
            this.value = value;
        }
    }


    public CustomHashMap(int tamanho) {
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int funcaoHash(long chave) {
        return (int) (chave % tamanho);
    }

    public void put(long key, T value) {
        if ((double) numElementos / tamanho >= DEFAULT_LOAD_FACTOR) {
            resize();
        }
        this.comparacoes = 0;
        Dado<T> dado = new Dado<>(key, value);
        int indice = funcaoHash(key);
        tabela[indice].add(dado);
        numElementos++;
        this.comparacoes++;
        System.out.println("Número de comparações ao inserir: " + this.comparacoes);
    }

    public boolean containsKey(long key) {
        int indice = funcaoHash(key);
        for (Dado<T> item : tabela[indice]) {
            if (item.key == key) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(long key) {
        int indice = funcaoHash(key);
        this.comparacoes = 0;
        for (Dado<T> item : tabela[indice]) {
            if (item.key == key) {
                tabela[indice].remove(item);
                numElementos--;
                this.comparacoes++;
                System.out.println("Número de comparações ao remover: " + this.comparacoes);
                return true;
            }
        }
        return false;
    }

    public T get(long key) {
        int indice = funcaoHash(key);
        this.comparacoes = 0;
        for(Dado<T> item : tabela[indice]){
            this.comparacoes++;
            if(item.key == key){
                System.out.println("Comparações realizadas ao pegar valor de uma chave: " + this.comparacoes);
                return item.value;
            }
        }
            return null;
        //Retorna o valor para o qual a chave especificada é mapeada ou null se este mapa não contém nenhum mapeamento para a chave.
        // Placeholder, implement this method
    }

    public void replace(long key, T value) {
        int indice = funcaoHash(key);
        this.comparacoes = 0;
        for(Dado<T> item : tabela[indice]){
            this.comparacoes++;
            if(item.key == key){
                item.value = value;
                break;
            }
        }
        System.out.println("Comparações realizadas ao tentar trocar o valor de uma chave: " + this.comparacoes);
        // implementar 
        //Substitui a entrada da chave especificada para o valor V somente se ela estiver mapeada para algum valor.
    }

    private void resize() {
        int novoTamanho = tamanho * 2;
        this.tamanho = novoTamanho;
        LinkedList<Dado<T>>[] novaTabela = new LinkedList[novoTamanho];
        for (int i = 0; i < novoTamanho; i++) {
            novaTabela[i] = new LinkedList<>();
        }

        for (LinkedList<Dado<T>> lista : tabela) {
            for (Dado<T> dado : lista) {
                int novoIndice = funcaoHash(dado.key);
                novaTabela[novoIndice].add(dado);
            }
        }

        this.tabela = novaTabela;
        System.out.println("Tabela redimensionada para " + novoTamanho + " elementos.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append("[").append(i).append("]");
            for (Dado<T> item : tabela[i]) {
                sb.append(" -> ").append(item.key).append(":").append(item.value);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

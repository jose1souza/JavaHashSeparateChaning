package javahashseparatechaining;

import java.util.LinkedList;
import java.util.Scanner;

public class JavaHashSeparateChaining<T> {
    private static class Dado<T> {
        long key;
        T value;

        Dado(long key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Dado<T>>[] tabela;
    private int tamanho;
    private int numElementos;
    private int comparacoes; // Implementar para apresentar o número de comparações necessárias para inserir, buscar e remover elementos

    public JavaHashSeparateChaining(int tamanho) {
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
        if ((double) numElementos / tamanho >= 0.75) {
            redimensiona();
        }
        
        Dado<T> dado = new Dado<>(key, value);
        int indice = funcaoHash(key);
        tabela[indice].add(dado);
        numElementos++;
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
        for (Dado<T> item : tabela[indice]) {
            if (item.key == key) {
                tabela[indice].remove(item);
                numElementos--;
                return true;
            }
        }
        return false;
    }

    public T get(long key) {
        // implementar 
        //Retorna o valor para o qual a chave especificada é mapeada ou null se este mapa não contém nenhum mapeamento para a chave.
        return null; // Placeholder, implement this method 
    }

    public void replace(long key, T value) {
        // implementar 
        //Substitui a entrada da chave especificada para o valor V somente se ela estiver mapeada para algum valor.
    }

    public void printHash() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("[" + i + "] ");
            for (Dado<T> item : tabela[i]) {
                System.out.print(" -> " + item.key + ":" + item.value);
            }
            System.out.println();
        }
    }

    private void redimensiona() {
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

    private static int menu(Scanner scanner) {
        System.out.println("\t\t*** IFSULDEMINAS - CAMPUS MACHADO ***");
        System.out.println("\t\t*** Estrutura de Dados I ***");
        System.out.println("\t\t*** HASH ENCADEADO - Separate Chaining ***");
        System.out.println("1-Inserir");
        System.out.println("2-Remover");
        System.out.println("3-Buscar");
        System.out.println("4-Alterar");
        System.out.println("0-Sair");
        System.out.print("Escolha uma opcao: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tamanho da tabela: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        JavaHashSeparateChaining<String> hashTable = new JavaHashSeparateChaining<>(n);
        int op;
        do {
            hashTable.printHash();
            op = menu(scanner);
            switch (op) {
                case 1:
                    System.out.print("Entre com a chave: ");
                    long chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Entre com o objeto: ");
                    String nome = scanner.nextLine();
                    hashTable.put(chave, nome);
                    break;

                case 2:
                    System.out.print("Chave para remover: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    boolean removeu = hashTable.remove(chave);
                    if (!removeu) {
                        System.out.println("Chave nao existente para remocao");
                    } else {
                        System.out.println("Chave removida com sucesso! :)");
                    }
                    break;

                case 3:
                    System.out.print("Chave para busca: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    boolean encontrado = hashTable.containsKey(chave);
                    if (!encontrado) {
                        System.out.println("Chave nao encontrada :(");
                    } else {
                        System.out.println("Chave encontrada!");
                    }
                    break;

                case 4:
                    System.out.print("Chave para alterar: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Novo valor: ");
                    String novoValor = scanner.nextLine();
                    if (hashTable.containsKey(chave)) {
                        hashTable.put(chave, novoValor);
                        System.out.println("Valor alterado com sucesso! :)");
                    } else {
                        System.out.println("Chave nao encontrada para alteracao :(");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
            try {
                Thread.sleep(1000); // Pausa para simular o getch()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J"); // Limpar a tela (ANSI escape code)
            System.out.flush();
        } while (op != 0);

        scanner.close();
    }
}




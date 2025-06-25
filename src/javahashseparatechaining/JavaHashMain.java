package javahashseparatechaining;

import java.util.Scanner;

public class JavaHashMain<T> {

    private static int menu(Scanner scanner) {
        System.out.println("\t\t*** IFSULDEMINAS - CAMPUS MACHADO ***");
        System.out.println("\t\t*** Estrutura de Dados I ***");
        System.out.println("\t\t*** HASH ENCADEADO - Separate Chaining ***");
        System.out.println("1-Inserir");
        System.out.println("2-Remover");
        System.out.println("3-Buscar");
        System.out.println("4-Alterar");
        System.out.println("5- Visualizar Valor");
        System.out.println("6- Substituição de Valor");
        System.out.println("0-Sair");
        System.out.print("Escolha uma opcao: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tamanho da tabela: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        CustomHashMap<String> meuHashMap = new CustomHashMap<>(n);
        int op;
        do {
            System.out.println(meuHashMap.toString());
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine(); // Aguarda o usuário pressionar Enter
            op = menu(scanner);
            switch (op) {
                case 1:
                    System.out.print("Entre com a chave: ");
                    long chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Entre com o objeto: ");
                    String nome = scanner.nextLine();
                    meuHashMap.put(chave, nome);
                    break;

                case 2:
                    System.out.print("Chave para remover: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    boolean removeu = meuHashMap.remove(chave);
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
                    boolean encontrado = meuHashMap.containsKey(chave);
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
                    if (meuHashMap.containsKey(chave)) {
                        meuHashMap.put(chave, novoValor);
                        System.out.println("Valor alterado com sucesso! :)");
                    } else {
                        System.out.println("Chave nao encontrada para alteracao :(");
                    }
                    break;

                case 5:
                    System.out.print("Chave para busca de valor: ");
                    chave = scanner.nextLong();
                    scanner.nextLine(); // Limpar o buffer
                    String retorno = meuHashMap.get(chave);
                    if (retorno != null) {
                        System.out.println("Valor: " + retorno);
                    } else {
                        System.out.println("O valor é nulo");
                    }
                    break;
                
                case 6:
                    System.out.println("Chave para alteração de valor: ");
                    chave = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Novo dado a ser inserido: ");
                    String dado = scanner.nextLine();
                    meuHashMap.replace(chave, dado);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
            
        } while (op != 0);

        scanner.close();
    }
}




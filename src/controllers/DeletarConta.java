package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Banco;
import services.email.Servidor;

/**
 * Classe responsável por fornecer métodos para deletar uma conta do banco.
 */
public class DeletarConta {

    /**
     * Método para deletar uma conta do banco.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta está armazenada.
     * @param indexDoUsuario    Índice da conta a ser deletada.
     * @param servidorEmail Servidor de e-mail para notificações.
     */
    public static void deletarConta(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario, Servidor servidorEmail){

        // Constantes para as opções do menu de deleção de conta
        final int DELETARCONTA = 1;
        final int VOLTAR = 2;

        boolean running = true;

        do {
            try {
                System.out.print("\033[H\033[2J");
                System.out.println("======== DELETAR CONTA ========");
                System.out.printf("[%d] Deletar conta%n", DELETARCONTA);
                System.out.printf("[%d] Voltar%n", VOLTAR);
                System.out.println("===============================");

                System.out.println("Digite uma opção: ");
                int opcao = entrada.nextInt();
                entrada.nextLine();

                switch (opcao) {
                    case DELETARCONTA:
                        if (confirmarDelecao(entrada)) {
                            System.out.print("\033[H\033[2J");
                            bancoCriado.removerConta(indexDoUsuario);
                            System.out.println("A conta foi deletada com sucesso!");
                            System.out.println("Pressione ENTER para continuar.");
                            entrada.nextLine();
                        }
                        running = false;
                        break;

                    case VOLTAR:
                        running = false;
                        System.out.print("\033[H\033[2J");
                        break;

                    default:
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite uma opção válida.");
                        break;
                }
        
            } catch(InputMismatchException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("============ ATENÇÃO! ===========");
                System.out.println("      Digite apenas números!     ");
                System.out.println("=================================");
                System.out.println("Pressione ENTER para continuar.");
                entrada.nextLine();
                entrada.nextLine();
            }

        } while(running);
    }

    /**
     * Método auxiliar para confirmar a deleção de uma conta.
     * 
     * @param entrada   Scanner para entrada de dados do usuário.
     * @return          true se o usuário confirmar a deleção, false caso contrário.
     */
    private static boolean confirmarDelecao(Scanner entrada) {
        System.out.println("Atenção! Essa ação não pode ser revertida.");
        System.out.println("Deseja encerrar a conta? [S]im ou [N]ão");

        while (true) {
            String resposta = entrada.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                return true;
            } else if (resposta.equals("n")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Digite [S] para sim ou [N] para não.");
            }
        }
    }
}

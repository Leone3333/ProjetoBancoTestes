package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Banco;
import services.email.Servidor;

public class DeletarConta {

    public static void deletarConta(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario, Servidor servidorEmail){

        // Constantes para as opções do menu de deleção de conta
        final int DELETARCONTA = 1;
        final int VOLTAR = 2;

        boolean running = true;

        do {

            try {

                // Menu para deleção de conta
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
                        // Opção para deletar a conta
                        System.out.println("Atenção! Essa ação não pode ser revertida.");
                        System.out.println("Deseja Encerrar a conta? [S]im ou [N]ão");
                        String valorEscolhido = entrada.nextLine().toLowerCase().substring(0,1);

                        if (valorEscolhido.equals("s")) {
                            // Deleta a conta do banco
                            System.out.print("\033[H\033[2J");
                            bancoCriado.removerConta(indexDoUsuario);
                            System.out.println("A conta foi deletada com sucesso!");
                            System.out.println("Pressione ENTER para continuar");
                            entrada.nextLine();
                        }

                        running = false;
                        break;

                    case VOLTAR:
                        // Retorna ao menu anterior
                        running = false;
                        System.out.print("\033[H\033[2J");
                        break;

                    default:
                        // Exibe mensagem se a opção for inválida
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite alguma das opções abaixo: ");
                        break;
                }
        
            } catch(InputMismatchException e) {
                // Trata erro de entrada inválida
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
}

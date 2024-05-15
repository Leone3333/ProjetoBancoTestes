package controllers;

import java.util.Scanner;

import entities.Banco;
import entities.enums.TipoDeDado;

public class AdministradorBanco {
    public static void administradorBanco(Scanner entrada, Banco bancoCriado) {

        // Constantes para as opções do menu após o login
        final int LISTARCONTAS = 1;
        final int DELETARCONTAS = 2;
        final int SAIR = 3;


        // Flag para controlar o fluxo do programa.
        boolean running = true;

        // Loop principal para o menu
        do {
            try {
                // Menu do programa
                System.out.println();
                System.out.println("=============== MENU - ADMINISTRADOR  ===============");
                System.out.printf("[%d] Listar Contas%n", LISTARCONTAS);
                System.out.printf("[%d] Deletar Contas%n", DELETARCONTAS);
                System.out.printf("[%d] Sair%n", SAIR);
                System.out.println("=====================================================");

                System.out.println();
                System.out.println("Digite uma opção: ");
                String opcao = entrada.next().trim();
                System.out.print("\033[H\033[2J");

                // Transformando a entrada String em Integer. Tratando exceções.
                Integer opcaoValida = Integer.parseInt(opcao);

                // Verificando qual opção foi digitada.
                switch (opcaoValida) {
                    case LISTARCONTAS:

                        listasContas(entrada, bancoCriado);
                        break;

                    case DELETARCONTAS:
                        break;

                    case SAIR:
                        // Encerrando o programa.
                        running = false;
                        break;

                    default:
                        // Exibindo mensagem de opção inválida.
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite uma opção válida.");
                        break;
                }
            } catch (NumberFormatException e) {
                // Tratando erro de conversão de String para Integer.
                System.out.print("\033[H\033[2J");
                System.out.println("============ ATENÇÃO! ===========");
                System.out.println("      Digite apenas números!     ");
                System.out.println("=================================");
                System.out.println("Pressione ENTER pra continuar.");
                entrada.nextLine();
            }
        } while (running);

    }

    public static void listasContas(Scanner entrada, Banco bancoCriado) {
        
        System.out.println("=========================================================================================================");
        System.out.printf("%-5s %-25s %-25s %-15s %-15s%n", "ID", "Nome", "Identificação", "Saldo", "Tipo da Conta");
        for (int indexDoUsuario = 0; indexDoUsuario < bancoCriado.getContasNoBanco().size(); indexDoUsuario++) {

            System.out.printf("%-5s %-25s %-25s %-15s %-15s%n" ,"[" + (indexDoUsuario + 1) + "]",  bancoCriado.getDados(indexDoUsuario, TipoDeDado.NOME), bancoCriado.getDados(indexDoUsuario, TipoDeDado.IDENTIFICACAO), 
            bancoCriado.getDados(indexDoUsuario, TipoDeDado.SALDO), bancoCriado.getDados(indexDoUsuario, TipoDeDado.TIPODACONTA));

        }

        System.out.println("=========================================================================================================");
    }
}

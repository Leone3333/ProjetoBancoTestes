package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Banco;
import entities.enums.TipoDeDado;
import services.email.Servidor;

public class AtualizarDadosConta {
    
    public static void atualizarDadosConta(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario, Servidor servidorEmail){

        // Constantes para as opções do menu de atualização de dados da conta
        final int ATUALIZARNOME = 1;
        final int ATUALIZARDATA = 2;
        final int ATUALIZAREMAIL = 3;
        final int ATUALIZARSENHA = 4;
        final int VOLTAR = 5;

        boolean running = true;

        do {
            try {

                // Menu para atualização de dados da conta
                System.out.println("======== ATUALIZAR DADOS DA CONTA ========");
                System.out.printf("[%d] Atualizar nome%n", ATUALIZARNOME);
                System.out.printf("[%d] Atualizar data%n", ATUALIZARDATA);
                System.out.printf("[%d] Atualizar e-mail%n", ATUALIZAREMAIL);
                System.out.printf("[%d] Atualizar senha%n", ATUALIZARSENHA);
                System.out.printf("[%d] Voltar%n", VOLTAR);
                System.out.println("==========================================");

                System.out.println("Digite uma opção: ");
                int opcao = entrada.nextInt();
                entrada.nextLine();

                switch (opcao) {

                    case ATUALIZARNOME:
                        // Atualizar nome da conta
                        System.out.println("====== ATUALIZAR NOME ======");
                        bancoCriado.getConta(indexDoUsuario).setNome(entrada);
                        System.out.println("O nome foi atualizado para " + bancoCriado.getDados(indexDoUsuario, TipoDeDado.NOME));
                        running = false;
                        break;

                    case ATUALIZARDATA:
                        // Atualizar data de nascimento ou criação da conta
                        System.out.println("====== ATUALIZAR DATA ======");
                        bancoCriado.getConta(indexDoUsuario).setData(entrada);    
                        System.out.println("A data foi atualizada para " + bancoCriado.getDados(indexDoUsuario, TipoDeDado.DATA));
                        running = false;
                        break;

                    case ATUALIZAREMAIL:
                        // Atualizar endereço de e-mail da conta
                        System.out.println("====== ATUALIZAR E-MAIL ======");
                        bancoCriado.getConta(indexDoUsuario).setEnderecoEmail(entrada, bancoCriado, servidorEmail);
                        System.out.println("O e-mail foi atualizado para " + bancoCriado.getDados(indexDoUsuario, TipoDeDado.EMAIL));
                        running = false;
                        break;

                    case ATUALIZARSENHA:
                        // Atualizar senha da conta
                        System.out.println("====== ATUALIZAR SENHA ======");
                        bancoCriado.getConta(indexDoUsuario).setSenha(entrada);    
                        running = false;
                        break;

                    case VOLTAR:
                        // Voltar ao menu anterior
                        running = false;
                        System.out.print("\033[H\033[2J");
                        break;

                    default:
                        // Exibir mensagem se a opção for inválida
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite alguma das opções abaixo: ");
                        break;
                }
    
            } catch(InputMismatchException e) {
                // Tratar erro de entrada inválida
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
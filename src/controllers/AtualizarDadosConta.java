package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Banco;
import entities.enums.TipoDeDado;

public class AtualizarDadosConta {
    
    public static void atualizarDadosConta(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        final int ATULIZARNOME = 1;
        final int ATULIZARDATA = 2;
        final int ATULIZAREMAIL = 3;
        final int VOLTAR = 4;

        boolean running = true;

        do{

            try{

            System.out.print("\033[H\033[2J");
            System.out.println("======== ATUALIZAR DADOS DA CONTA ========");
            System.out.printf("[%d] Atualizar nome%n", ATULIZARNOME);
            System.out.printf("[%d] Atualizar data%n", ATULIZARDATA);
            System.out.printf("[%d] Atualizar e-mail%n", ATULIZAREMAIL);
            System.out.printf("[%d] Voltar%n", VOLTAR);
            System.out.println("==========================================");

            System.out.println("Digite uma opção: ");
            int opcao = entrada.nextInt();


            switch (opcao) {

                case ATULIZARNOME:
                    bancoCriado.getConta(indexDoUsuario).setNome(entrada);
                    System.out.println(bancoCriado.getDados(indexDoUsuario, TipoDeDado.NOME));
                    running = false;
                    break;
    
                case ATULIZARDATA:

                    bancoCriado.getConta(indexDoUsuario).setData(entrada);    
                    System.out.println(bancoCriado.getDados(indexDoUsuario, TipoDeDado.DATA));
                    running = false;
                    break;
    
                case ATULIZAREMAIL:
                    running = false;
                    System.out.print("\033[H\033[2J");
                    break;
    

                case VOLTAR:
                    running = false;
                    System.out.print("\033[H\033[2J");
                    break;

                default:
                    //Exibir mensagem se a opção for válida.
                    System.out.print("\033[H\033[2J");
                    System.out.println("Digite alguma das opções abaixo: ");
                    break;
            }
    
            
                } catch(InputMismatchException e){
    
                    System.out.print("\033[H\033[2J");
                    System.out.println("============ ATENÇÃO! ===========");
                    System.out.println("      Digite apenas números!     ");
                    System.out.println("=================================");
                    System.out.println("Pressione ENTER pra continuar.");
                    entrada.nextLine();
                    entrada.nextLine();
    
                }



        } while(running);


    }
}

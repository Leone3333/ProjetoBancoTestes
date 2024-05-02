package controllers;

import java.util.Scanner;

import services.email.*;
import entities.*;


public class LogarNoBanco {

    public static void logarNoBanco(Scanner entrada, Servidor servidorGmail, Banco bancoCriado){

        final int DEPOSITAR = 1;
        final int SACAR = 2;
        final int TRANSFERIR = 3;
        final int EXTRATO = 4;
        final int SAIR = 5;

        entrada.nextLine();
        System.out.println("======== LOGIN ========");
        System.out.println("E-mail: ");
        String emailParaLogar = entrada.nextLine();
        System.out.println("Senha: ");
        String senhaParaLogar = entrada.nextLine();
        System.out.println("=======================");

        Integer indexDoUsuario = null;

        for(int i = 0; i < bancoCriado.getContasNoBanco().size(); i++){
            if (bancoCriado.getEmailConta(i).equals(emailParaLogar) && 
                bancoCriado.getSenhaConta(i).equals(senhaParaLogar)){

                System.out.print("\033[H\033[2J");
                System.out.println("Seja Bem-Vindo(a) " + bancoCriado.getNomeConta(i));

                if (bancoCriado.getConta(i).getTipoDaConta().equals("Pessoa Física")) {
                    
                    // Código para rodar em conta Pessoa física após login
                    indexDoUsuario = i;
                    boolean running = true;

                    do {
                    System.out.println("=========== Selecione a opção desejada: ===========");
                    System.out.println();
                    System.out.printf("[%d] Depositar%n", DEPOSITAR);
                    System.out.printf("[%d] Sacar%n", SACAR);
                    System.out.printf("[%d] Transferir%n", TRANSFERIR);
                    System.out.printf("[%d] Extrato%n", EXTRATO);
                    System.out.printf("[%d] Sair%n", SAIR);
                    
                    int opcaoDigitada = entrada.nextInt();

                    switch (opcaoDigitada) {
                        case DEPOSITAR:
                            // depositar
                            Operacoes.depositar(entrada, bancoCriado, indexDoUsuario);
                            break;

                        case SACAR:
                            // sacar
                            break;

                        case TRANSFERIR:
                            // transferir
                            break;
                        
                        case EXTRATO:

                            break;

                        case SAIR:

                            running = false;
                            break;

                        default:
                            System.out.println("Digite uma opção válida!");
                            break;
                    }

                }while(running);
                    

                }
                else if(bancoCriado.getConta(i).getTipoDaConta().equals("Pessoa Jurídica")) {
                
                    // Código para rodar em conta Pessoa jurídica após login
                    System.out.println("Deu certo, pessoa jurídica");

                }

                break;
            }
        }

    }
}

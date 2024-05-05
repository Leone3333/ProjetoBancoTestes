package controllers;

import java.util.Scanner;

import services.email.*;
import entities.*;
import entities.enums.TipoDeDado;


public class LogarNoBanco {

    public static void logarNoBanco(Scanner entrada, Servidor servidorEmail, Banco bancoCriado){

        final int DEPOSITAR = 1;
        final int SACAR = 2;
        final int TRANSFERIR = 3;
        final int EXTRATO = 4;
        final int ATUALIZARDADOS = 5;
        final int EXCLUIRCONTA = 6;
        final int SAIR = 7;

        //Flags do e-mail e senha.
        boolean emailValido = false;
        boolean senhaValida = false;

        String emailParaLogar;
        String senhaParaLogar;

        entrada.nextLine();
        System.out.println("=============== FAZER LOGIN  ===============");

        //Enquanto o e-mail não for válido, ficará em um loop.
        do{
        System.out.println("E-mail: ");
        emailParaLogar = entrada.nextLine();

        if(emailParaLogar.isEmpty()){
            System.out.println("O campo não pode estar vazio!");

        } else if(!(emailParaLogar.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
            System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
            System.out.println();

        } else{
            emailValido = true;
        }
        
        } while(!emailValido);






        //Enquanto a senha não for válida, ficará em um loop.
        do{
            System.out.println("Senha: ");
            senhaParaLogar = entrada.nextLine();

            if(senhaParaLogar.isEmpty()){
                System.out.println("O campo não pode estar vazio!");

            }  else if (!senhaParaLogar.matches("\\d+")) {
                System.out.println("A senha deve conter somente números!");
                System.out.println();
                

            } else if (senhaParaLogar.length() != 8) {
                System.out.println("A senha deve conter 8 dígitos!");
                System.out.println();
                
            } else{
                senhaValida = true;
                System.out.println("============================================");
            }

        } while(!senhaValida);




        
        Integer indexDoUsuario;
        Boolean usuarioEncontrado = false;

        //Pecorrer as contas no banco e depois verificar se o e-mail e a senha batem com o digitado./
        for(int i = 0; i < bancoCriado.getContasNoBanco().size(); i++){
            if (bancoCriado.getDados(i, TipoDeDado.EMAIL).equals(emailParaLogar) && 
                bancoCriado.getDados(i, TipoDeDado.SENHA).equals(senhaParaLogar)){

                usuarioEncontrado = true;

                    System.out.print("\033[H\033[2J");
                    System.out.println("Seja Bem-Vindo(a) " + bancoCriado.getDados(i, TipoDeDado.NOME));

                
                    
                    // Código para rodar em conta Pessoa física após login
                    indexDoUsuario = i;
                    boolean running = true;

                    do {
                    System.out.println("=========== Selecione a opção desejada: ===========");
                    System.out.printf("[%d] Depositar%n", DEPOSITAR);
                    System.out.printf("[%d] Sacar%n", SACAR);
                    System.out.printf("[%d] Transferir%n", TRANSFERIR);
                    System.out.printf("[%d] Extrato%n", EXTRATO);
                    System.out.printf("[%d] Atualizar Dados%n", ATUALIZARDADOS);
                    System.out.printf("[%d] Excluir Conta", EXCLUIRCONTA);
                    System.out.printf("[%d] Sair%n", SAIR);
                    System.out.println("===================================================");
                    
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


                        case ATUALIZARDADOS:
                            AtualizarDadosConta.atualizarDadosConta(entrada, bancoCriado, indexDoUsuario, servidorEmail);
                            break;


                        case EXCLUIRCONTA:

                            break;
                            
                        case SAIR:

                            running = false;
                            break;

                        default:
                            System.out.println("Digite uma opção válida!");
                            break;
                    }

                }   while(running);
            }
        }

        if(!usuarioEncontrado){
            System.out.println("Dados inválidos! Verifique o e-mail ou a senha!");
        }

    }
}

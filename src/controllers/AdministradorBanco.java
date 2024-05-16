package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Banco;
import entities.enums.TipoDeDado;
import services.email.Servidor;

public class AdministradorBanco {
    public static void administradorBanco(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {

        // Constantes para as opções do menu após o login
        final int LISTARCONTAS = 1;
        final int ALTERARSENHA = 2;
        final int SAIR = 3;

        // Constantes para as opções do menu após a seleção do usuário.
        final int ATUALIZARDADOS = 1;
        final int DELETARCONTA = 2;
        final int EXIBIRDADOS = 3;


        // Flag para controlar o fluxo do programa.
        boolean running = true;

         System.out.print("\033[H\033[2J");
        // Loop principal para o menu
        do {
            try {
                // Menu do programa

                System.out.println();
                System.out.println("=============== MENU - ADMINISTRADOR  ===============");
                System.out.printf("[%d] Listar Contas no Banco%n", LISTARCONTAS);
                System.out.printf("[%d] Alterar Senha do Administrador%n", ALTERARSENHA);
                System.out.printf("[%d] Sair%n", SAIR);
                System.out.println("=====================================================");

                System.out.println();
                System.out.println("Digite uma opção: ");
                String opcao = entrada.next().trim();
                System.out.print("\033[H\033[2J");

                // Transformando a entrada String em Integer. Tratando exceções.
                Integer opcaoValida = Integer.parseInt(opcao);

                // Verificando qual opção foi digitada.

                Integer indexDoUsuario;
                switch (opcaoValida) {

                    case LISTARCONTAS:

                        indexDoUsuario = listasContas(entrada, bancoCriado);

                        System.out.print("\033[H\033[2J");
                        System.out.println("Conta atual: " + bancoCriado.getDados(indexDoUsuario, TipoDeDado.NOME));
                        System.out.println("=======================================");
                        System.out.println("O que deseja fazer?");
                        System.out.printf("[%d] Atualizar Dados %n", ATUALIZARDADOS);
                        System.out.printf("[%d] Deletar Conta %n", DELETARCONTA);
                        System.out.printf("[%d] Exibir Dados %n", EXIBIRDADOS);
                        System.out.println("=======================================");

                        System.out.println();
                        System.out.println("Digite uma opção: ");
                        opcao = entrada.next().trim();
                        System.out.print("\033[H\033[2J");

                        // Transformando a entrada String em Integer. Tratando exceções.
                        opcaoValida = Integer.parseInt(opcao);

                        switch (opcaoValida) {

                            case ATUALIZARDADOS:

                                AtualizarDadosConta.atualizarDadosConta(entrada, bancoCriado, indexDoUsuario, servidorEmail);
                                break;

                            case DELETARCONTA:

                                DeletarConta.deletarConta(entrada, bancoCriado, indexDoUsuario, servidorEmail);
                                break;

                            case EXIBIRDADOS:

                                System.out.println(bancoCriado.exibirDados(indexDoUsuario)); 
                                break;

                            default:

                                // Exibindo mensagem de opção inválida.
                                System.out.print("\033[H\033[2J");
                                System.out.println("Digite uma opção válida.");
                                break;

                        }

                        break;

                    case ALTERARSENHA:

                        entrada.nextLine();
                        bancoCriado.setSenhaADM(entrada);
                        running = false;
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

    public static Integer listasContas(Scanner entrada, Banco bancoCriado) {
        
        
        System.out.println("=========================================================================================================");
        System.out.printf("%-5s %-25s %-25s %-15s %-15s%n", "ID", "Nome", "Identificação", "Saldo", "Tipo da Conta");

        for (int index = 0; index < bancoCriado.getContasNoBanco().size(); index++) {
            System.out.printf("%-5s %-25s %-25s %-15s %-15s%n" ,"[" + (index + 1) + "]",  bancoCriado.getDados(index, TipoDeDado.NOME), bancoCriado.getDados(index, TipoDeDado.IDENTIFICACAO), 
            "R$" + bancoCriado.getDados(index, TipoDeDado.SALDO), bancoCriado.getDados(index, TipoDeDado.TIPODACONTA));
        }

        System.out.println("=========================================================================================================");

        try{

            System.out.println("Digite o ID do usuário que deseja acessar: ");
            Integer indexDoUsuario = entrada.nextInt();
            return (indexDoUsuario - 1);

        } catch(InputMismatchException e){
            System.out.println("Digite apenas números!");

        } catch(IndexOutOfBoundsException e){
            System.out.println("ID inexistente!");
        }

        return null;
    } 
}

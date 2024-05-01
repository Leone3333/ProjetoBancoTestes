package application;

import java.util.Scanner;

import services.email.*;
import controllers.*;
import entities.*;

public class Principal {

    public static void main(String[] args) {

        //Variáveis constantes para as opções.
        final int LOGIN = 1;
        final int CADASTRAR = 2;
        final int SAIR = 3;

        //Inicio das instâncias
        Scanner entrada = new Scanner(System.in);

        // Criando o Servidor de email que irá guardar todos os emails cadastrados.
        Servidor servidorEmail = new Servidor(); 

        // Criando o banco com o nome e o e-mail do banco para o envio de mensagens de verificação e transações. 
        Banco bancoCriado = new Banco("Banco do Brasil","suporte@bb.com.br");

        //Fim das instâncias

        //Cadastrando o email do Banco no servidor. 
        servidorEmail.cadastrarEmail(bancoCriado.getEmailDoBanco());


        //Instâncias dos usuários cadastrados
        Conta conta1 = new ContaPF("12345678", "111.222.333-44", "Pedro Silva dos Santos", "14/01/2005", 
        new Email("pedro@gmail.com"));
        bancoCriado.adicionarConta(conta1);

        Conta conta2 = new ContaPJ("12345678", "Aliexpress", "11.222.333/0001-44", "12/04/2002", 
        new Email("aliexpress@gmail.com"));
        bancoCriado.adicionarConta(conta2);

        //Flag para controlar o fluxo do programa.
        boolean running = true;
        do {

            try {

            //Menu do programa
            System.out.println();
            System.out.println("=========== Banco do Brasil ===========");
            System.out.printf("[%d] Login%n", LOGIN);  
            System.out.printf("[%d] Cadastrar%n", CADASTRAR);
            System.out.printf("[%d] Sair%n", SAIR);
            System.out.println("=======================================");

            System.out.println();
            System.out.println("Digite uma opção: ");
            String opcao = entrada.next().trim();
            System.out.print("\033[H\033[2J");

            //Transformar entrada String em Integer. Se não funciona ocorrerá um tratamento de erro.
            Integer opcaoValida = Integer.parseInt(opcao);   

            //Verificar qual opcão foi digitada.
            switch (opcaoValida) {

                case LOGIN:
                
                    //Chamar o método para logar no banco.
                    LogarNoBanco.logarNoBanco(entrada, servidorEmail, bancoCriado);
                    break;
                
                case CADASTRAR:
                
                    //Chamar o método para cadastrar uma conta PF(pessoa física) ou PJ (pessoa jurírica).
                    CadastrarNoBanco.cadastrarNoBanco(entrada, bancoCriado, servidorEmail);
                    break;
                
                case SAIR:
                    
                    //Encerrar o programa trocando o valor da flag.
                    running = false;
                    break;
                
                default:

                    //Exibir mensagem se a opção for válida.
                    System.out.print("\033[H\033[2J");
                    System.out.println("Digite alguma das opções abaixo: ");
                    break;
            }
            
            //Tratar o erro da conversão de String x Integer
            } catch (NumberFormatException e) {

                    System.out.print("\033[H\033[2J");
                    System.out.println("============ ATENÇÃO! ===========");
                    System.out.println("      Digite apenas números!     ");
                    System.out.println("=================================");
                    System.out.println("Pressione ENTER pra continuar.");
                    entrada.nextLine();
                    entrada.nextLine();
            }
        
        } while (running);

        //Fechando o Scanner
        entrada.close();
        
    }
}

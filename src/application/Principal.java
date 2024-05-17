package application;

import java.util.Scanner;

import services.email.*;
import controllers.*;
import entities.*;

/**
 * Classe principal que contém o método main para execução do programa.
 */
public class Principal {

    /**
     * Método main para iniciar a execução do programa.
     * 
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {

        // Variáveis constantes para as opções do menu.
        final int LOGIN = 1;
        final int CADASTRAR = 2;
        final int SAIR = 3;

        // Inicialização do Scanner para entrada do usuário.
        Scanner entrada = new Scanner(System.in);

        // Criando o Servidor de email que irá armazenar todos os emails cadastrados.
        Servidor servidorEmail = new Servidor(); 

        // Criando o banco com o nome e o e-mail do banco para envio de mensagens de verificação e transações. 
        Banco bancoCriado = new Banco("Banco do Brasil","suporte@bb.com.br", "admin@bb.com.br", "12345678");
       
        // Cadastrando o email do Banco no servidor. 
        servidorEmail.cadastrarEmail(bancoCriado.getEmailDoBanco());

        // Instâncias de algumas contas de exemplo
        // Contas Pessoa Física
        Conta conta1 = new ContaPF("12345678", "Pedro Silva dos Santos", "000.111.222-33", "14/01/2005", new Email("pedro@gmail.com"));
        conta1.depositar(1500D);

        Conta conta2 = new ContaPF("87654321", "Lucas Barbosa Rodrigues", "111.222.333-44", "16/11/2003", new Email("mariasantana@outlook.com"));
        conta2.depositar(12.87D);

        Conta conta3 = new ContaPF("07052003", "Mariá Santana da Cruz", "222.333.444-55", "07/05/2003", new Email("mateusoliveira@gmail.com"));
        conta3.depositar(453.21D);

        // Contas Pessoa Jurídica
        Conta conta4 = new ContaPJ("11223344", "Aliexpress", "13.236.697/0001-46", "12/04/2002", new Email("aliexpress@aliexpress.com.br"));
        conta4.depositar(1000D);
        
        Conta conta5 = new ContaPJ("00001111", "Shopee", "54.289.446/0001-07", "12/04/2002", new Email("shopee@shopee.com.br"));
        conta5.depositar(2392545.93D);

         Conta conta6 = new ContaPJ("13572468", "Netshoes", "09.339.936/0001-16", "14/02/2000", new Email("netshoes@netshoes.com.br"));
        conta6.depositar(1022022.12D);
        
        Conta conta7 = new ContaPJ("22221111", "Adidas", "42.274.696/0001-94", "18/07/1924", new Email("adidas@adidas.com.br"));
        conta7.depositar(9832545.93D);

        
        // Cadastrando as contas no banco
        bancoCriado.cadastrarConta(conta1);
        bancoCriado.cadastrarConta(conta2);
        bancoCriado.cadastrarConta(conta3);
        bancoCriado.cadastrarConta(conta4);
        bancoCriado.cadastrarConta(conta5);
        bancoCriado.cadastrarConta(conta6);
        bancoCriado.cadastrarConta(conta7);

        // Flag para controlar o fluxo do programa.
        boolean running = true;
        
        // Loop principal para o menu
        do {
            try {
                // Menu do programa
                System.out.println();
                System.out.printf("=========== %s =========== %n", bancoCriado.getNomeDoBanco());
                System.out.printf("[%d] Login%n", LOGIN);  
                System.out.printf("[%d] Cadastrar%n", CADASTRAR);
                System.out.printf("[%d] Sair%n", SAIR);

                System.out.println();
                System.out.println("Digite uma opção: ");
                String opcao = entrada.next().trim();
                System.out.print("\033[H\033[2J");

                // Transformando a entrada String em Integer. Tratando exceções.
                Integer opcaoValida = Integer.parseInt(opcao);   

                // Verificando qual opção foi digitada.
                switch (opcaoValida) {
                    case LOGIN:
                        // Chamando o método para realizar o login no banco.
                        LogarNoBanco.logarNoBanco(entrada, servidorEmail, bancoCriado);
                        break;
                    
                    case CADASTRAR:
                        // Chamando o método para cadastrar uma conta PF(pessoa física) ou PJ (pessoa jurídica).
                        CadastrarNoBanco.cadastrarNoBanco(entrada, bancoCriado, servidorEmail);
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

        // Fechando o Scanner.
        entrada.close();        
    }
}

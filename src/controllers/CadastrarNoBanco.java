package controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.*;
import services.email.*;
import validation.*;

/**
 * Classe responsável por fornecer métodos para cadastrar uma conta no banco.
 */
public class CadastrarNoBanco {

    /**
     * Método para iniciar o processo de cadastro de conta no banco.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta será cadastrada.
     * @param servidorEmail Servidor de e-mail para notificações.
     */
    public static void cadastrarNoBanco(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {

        // Definição das opções do menu
        final int CONTAPF = 1;
        final int CONTAPJ = 2;
        final int VOLTAR = 3;

        boolean running = true;

        // Loop principal para o menu
        do {
            try {
                // Menu de opções
                System.out.println("=========== CADASTRAR CONTA =========");
                System.out.printf("[%d] Conta Pessoa Física %n", CONTAPF);
                System.out.printf("[%d] Conta Pessoa Jurídica %n", CONTAPJ);
                System.out.printf("[%d] Voltar %n", VOLTAR);
                System.out.println("=====================================");
                System.out.println();

                // Solicitação da escolha do usuário
                System.out.println("Digite uma opção: ");
                int opcao = entrada.nextInt();

                // Switch para lidar com as opções do usuário
                switch (opcao) {
                    case CONTAPF:
                        cadastrarPF(entrada, bancoCriado, servidorEmail);
                        running = false;
                        break;

                    case CONTAPJ:
                        cadastrarPJ(entrada, bancoCriado, servidorEmail);
                        running = false;
                        break;

                    case VOLTAR:
                        running = false;
                        System.out.print("\033[H\033[2J"); // Limpa a tela do console
                        break;

                    default:
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite alguma das opções abaixo: ");
                        break;
                }
            } catch(InputMismatchException e) {
                // Lidar com exceção de entrada inválida
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

    /**
     * Função para cadastrar uma conta Pessoa Física.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta será cadastrada.
     * @param servidorEmail Servidor de e-mail para notificações.
     */
    private static void cadastrarPF(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {
        entrada.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("========= CADASTRAR CONTA PESSOA FÍSICA =========");

        // Solicitar e validar os dados da Pessoa Física
        String nome = ValidarPF.solicitarEValidarNome(entrada);
        String cpf = ValidarPF.solicitarEValidarCPF(entrada, bancoCriado);
        String data = ValidarPF.solicitarEValidarDataDeNascimento(entrada);
        Email email = ValidarEmail.solicitarEValidarEmail(entrada, bancoCriado, servidorEmail);
        String senha = ValidarSenha.solicitarEValidarSenha(entrada);

        // Instanciar e cadastrar a conta de Pessoa Física
        Conta contaCriada = new ContaPF(senha, nome, cpf, data, email);
        bancoCriado.cadastrarConta(contaCriada);

        // Exibir dados da conta e enviar para o e-mail
        exibirDados(entrada, bancoCriado, contaCriada, email, servidorEmail);       
    }

    /**
     * Função para cadastrar uma conta Pessoa Jurídica.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta será cadastrada.
     * @param servidorEmail Servidor de e-mail para notificações.
     */
    private static void cadastrarPJ(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {
        entrada.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("========= CADASTRAR CONTA PESSOA JURÍDICA =========");

        // Solicitar e validar os dados da Pessoa Jurídica
        String nome = ValidarPJ.solicitarEValidarNome(entrada);
        String cnpj = ValidarPJ.solicitarEValidarCNPJ(entrada, bancoCriado);
        String data = ValidarPJ.solicitarEValidarDataDeCriacao(entrada);
        Email email = ValidarEmail.solicitarEValidarEmail(entrada, bancoCriado, servidorEmail);
        String senha = ValidarSenha.solicitarEValidarSenha(entrada);

        // Instanciar e cadastrar a conta de Pessoa Jurídica
        Conta contaCriada = new ContaPJ(senha, nome, cnpj, data, email);
        bancoCriado.cadastrarConta(contaCriada);

        // Exibir dados da conta e enviar para o e-mail
        exibirDados(entrada, bancoCriado, contaCriada, email, servidorEmail);
    }

    /**
     * Método para exibir os dados da conta e enviar para o e-mail do usuário.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta foi cadastrada.
     * @param contaCriada   Conta que foi criada.
     * @param emailValido   Objeto de e-mail válido.
     * @param servidorEmail Servidor de e-mail para notificações.
     */
    private static void exibirDados(Scanner entrada, Banco bancoCriado, Conta contaCriada, Email emailValido, Servidor servidorEmail) {
        //Enviar um código de verificação
        servidorEmail.armazenarMensagem(bancoCriado.enviarDados(emailValido.getEmail()));
        emailValido.exibirEmailsRecebidos();

        System.out.print("\033[H\033[2J");
        System.out.println("============= Dados da Conta =============" );
        System.out.println(bancoCriado.exibirDados(contaCriada.getNumeroDaConta()));
        System.out.println("==========================================" );
        System.out.println("Enviamos os dados da sua conta para o seu e-mail.");
        System.out.println();
        System.out.println("Pressione ENTER para continuar");
        entrada.nextLine();
        System.out.print("\033[H\033[2J");
    }
}

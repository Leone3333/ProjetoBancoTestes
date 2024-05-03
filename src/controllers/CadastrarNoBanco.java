package controllers;

import java.util.Scanner;
import java.util.InputMismatchException;

import entities.*;
import services.email.*;

import validation.ValidarPF;
import validation.ValidarPJ;

public class CadastrarNoBanco {
    public static void cadastrarNoBanco(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){

    final int CONTAPF = 1;
    final int CONTAPJ = 2;
    final int VOLTAR = 3;

    boolean running = true;

    do{
       
        try{ 
        System.out.println("=========== CADASTRAR CONTA =========");
        System.out.printf("[%d] Conta Pessoa Física %n", CONTAPF);
        System.out.printf("[%d] Conta Pessoa Jurídica %n", CONTAPJ);
        System.out.printf("[%d] Voltar %n", VOLTAR);
        System.out.println("=====================================");
        System.out.println();


        System.out.println("Digite uma opção: ");
        int opcao = entrada.nextInt();

        
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

    //Função para criar uma conta Pessoa Física.
    public static void cadastrarPF(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){
        
        entrada.nextLine();
        System.out.print("\033[H\033[2J");

        System.out.println("========= CADASTRAR CONTA PESSOA FÍSICA =========");

        String nome = ValidarPF.solicitarEValidarNome(entrada);

        String cpf = ValidarPF.solicitarEValidarCPF(entrada, bancoCriado);

        String data = ValidarPF.solicitarEValidarDataDeNascimento(entrada);

        Email email = ValidarPF.solicitarEValidarEmail(entrada, bancoCriado, servidorEmail);
        
        String senha = ValidarPF.solicitarEValidarSenha(entrada);

        // Instância da Conta Pessoa Física.
        Conta contaCriada = new ContaPF(senha, nome, cpf, data, email);
        bancoCriado.cadastrarConta(contaCriada);

       //Exibir dados da conta e enviar os dados para o e-mail
       exibirDados(entrada, bancoCriado, contaCriada, email, servidorEmail);       
    }

    //Função para criar uma conta Pessoa Jurídica.
    public static void cadastrarPJ(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {

        entrada.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("========= CADASTRAR CONTA PESSOA JURÍDICA =========");

        String nome = ValidarPJ.solicitarEValidarNome(entrada);

        String cnpj = ValidarPJ.solicitarEValidarCNPJ(entrada, bancoCriado);

        String data = ValidarPJ.solicitarEValidarDataDeCriacao(entrada);

        Email email = ValidarPJ.solicitarEValidarEmail(entrada, bancoCriado, servidorEmail);
        
        String senha = ValidarPJ.solicitarEValidarSenha(entrada);

        // Instância da Conta Pessoa Física.
        Conta contaCriada = new ContaPJ(senha, nome, cnpj, data, email);
        bancoCriado.cadastrarConta(contaCriada);

        //Exibir dados da conta e enviar os dados para o e-mail
        exibirDados(entrada, bancoCriado, contaCriada, email, servidorEmail);
    }
    

    public static void exibirDados(Scanner entrada, Banco bancoCriado, Conta contaCriada, Email emailValido, Servidor servidorEmail){

    //Enviar um código de verificação
    servidorEmail.enviarMensagem(bancoCriado.enviarDados(emailValido.getEmail()));
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

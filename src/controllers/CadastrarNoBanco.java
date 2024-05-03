package controllers;


import java.util.Scanner;
import java.util.InputMismatchException;


import entities.*;
import services.email.*;
import validation.ValidacaoPF;
import validation.ValidacaoPJ;

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

        //Se o cadastro for feito, ou desejar sair, o valor muda para true, encerrando o loop.
        boolean cadastroFeito = false;
        
        switch (opcao) {

            case CONTAPF:
                while(!cadastroFeito){
                    cadastroFeito = cadastrarPF(entrada, bancoCriado, servidorEmail);}

                running = false;
                break;

            case CONTAPJ:
                while(!cadastroFeito){
                    cadastroFeito = cadastrarPJ(entrada, bancoCriado, servidorEmail);}

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
    public static boolean cadastrarPF(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){
        
        entrada.nextLine();
        System.out.print("\033[H\033[2J");

        System.out.println("========= CADASTRAR CONTA PESSOA FÍSICA =========");

        String nome = ValidacaoPF.solicitarEValidarNome(entrada);
        if(nome == null) return false;

        String cpf = ValidacaoPF.solicitarEValidarCPF(entrada, bancoCriado);
        if(cpf == null) return false;

        String data = ValidacaoPF.solicitarEValidarDataDeNascimento(entrada);
        if(data == null) return false;

        Email email = ValidacaoPF.solicitarEValidarEmail(entrada, bancoCriado, servidorEmail);
        if(email == null) return false;
        
        String senha = ValidacaoPF.solicitarEValidarSenha(entrada);
        if(senha == null) return false;

        // Instância da Conta Pessoa Física.
        Conta contaCriada = new ContaPF(senha, nome, cpf, data, email);
        bancoCriado.cadastrarConta(contaCriada);

       //Exibir dados da conta e enviar os dados para o e-mail
       exibirDados(entrada, bancoCriado, contaCriada, email, servidorEmail);
       return true;
       
    }

    //Função para criar uma conta Pessoa Jurídica.
    public static boolean cadastrarPJ(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {

        entrada.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("========= CADASTRAR CONTA PESSOA JURÍDICA =========");

        String nome = ValidacaoPJ.solicitarEValidarNome(entrada);
        if(nome == null) return false;

        String cnpj = ValidacaoPJ.solicitarEValidarCNPJ(entrada, bancoCriado);
        if(cnpj == null) return false;

        String data = ValidacaoPJ.solicitarEValidarDataDeCriacao(entrada);
        if(data == null) return false;

        Email email = ValidacaoPJ.solicitarEValidarEmail(entrada, bancoCriado, servidorEmail);
        if(email == null) return false;
        
        String senha = ValidacaoPJ.solicitarEValidarSenha(entrada);
        if(senha == null) return false;

        // Instância da Conta Pessoa Física.
        Conta contaCriada = new ContaPJ(senha, nome, cnpj, data, email);
        bancoCriado.cadastrarConta(contaCriada);

        //Exibir dados da conta e enviar os dados para o e-mail
        exibirDados(entrada, bancoCriado, contaCriada, email, servidorEmail);
        return true;
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

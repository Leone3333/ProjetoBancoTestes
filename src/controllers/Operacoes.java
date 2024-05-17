package controllers;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Locale;
import entities.Banco;
import entities.enums.TipoDeDado;

/**
 * Esta classe contém métodos para realizar operações bancárias, como depósito, saque, transferência e PIX.
 */
public class Operacoes {

    /**
     * Método para realizar um depósito em uma conta bancária.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta está localizada.
     * @param indexDoUsuario    Índice da conta bancária do usuário.
     */
    public static void depositar(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario) throws InputMismatchException{
        
        Locale.setDefault(Locale.US);

        System.out.print("\033[H\033[2J");
        System.out.println("Digite o valor que deseja depositar: ");
        Double valorParaDeposito = entrada.nextDouble();
        
        bancoCriado.getConta(indexDoUsuario).depositar(valorParaDeposito);
        
        valorParaDeposito.toString();
        String valorFormatado = String.format("%.2f", valorParaDeposito);

        System.out.print("\033[H\033[2J");
        System.out.println("Depósito no valor de R$" + valorFormatado + " Realizado com Sucesso!");
        System.out.println("Seu saldo atual é: R$" + bancoCriado.getDados(indexDoUsuario, TipoDeDado.SALDO));
        
    }

    /**
     * Método para realizar um saque em uma conta bancária.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta está localizada.
     * @param indexDoUsuario    Índice da conta bancária do usuário.
     */
    public static void sacar(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        Locale.setDefault(Locale.US);
        try{

            System.out.print("\033[H\033[2J");
            System.out.println("Digite o valor que deseja sacar: ");
            Double valorParaSaque = entrada.nextDouble();

            System.out.print("\033[H\033[2J");
            if (bancoCriado.getConta(indexDoUsuario).sacar(valorParaSaque)){
            System.out.println("Saque no valor de R$" + valorParaSaque + " Realizado com Sucesso!");
            }
            System.out.println("Seu saldo atual é: R$" + bancoCriado.getDados(indexDoUsuario, TipoDeDado.SALDO));
            
        } catch(InputMismatchException e){

              // Tratando erro de conversão de String para Integer.
              System.out.print("\033[H\033[2J");
              System.out.println("============ ATENÇÃO! ===========");
              System.out.println("      Digite apenas números!     ");
              System.out.println("=================================");
              System.out.println("Pressione ENTER pra continuar.");
              entrada.nextLine();
              entrada.nextLine();
              System.out.print("\033[H\033[2J");

        }

    }

    /**
     * Método para realizar uma transferência entre contas bancárias.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta está localizada.
     * @param indexDoUsuario    Índice da conta bancária do usuário.
     */
    public static void transferir(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){


        
        Locale.setDefault(Locale.US);

        System.out.print("\033[H\033[2J");
        System.out.println("Digite o valor que deseja transferir: ");
        Double valorParaTransferencia = entrada.nextDouble();
        System.out.println();
        System.out.println("Digite o número da conta que irá receber a transferência: ");

        entrada.nextLine();
        String numeroDaContaRecebedora = entrada.nextLine();

        // Pegando o index da conta que irá receber e guardando em indexDestino
        Integer indexDestino = bancoCriado.getIndexDestinatario(numeroDaContaRecebedora);

        // Pegando o nome de quem irá receber o dinheiro
        String nomeDoRecebedor = bancoCriado.getDados(indexDestino, TipoDeDado.NOME);

        // Sacando da conta que está enviando o dinheiro e depositando na conta recebedora
        if (bancoCriado.getConta(indexDoUsuario).sacar(valorParaTransferencia) && bancoCriado.getConta(indexDestino).depositar(valorParaTransferencia)) {
            System.out.println("Transferência no valor de R$ " + valorParaTransferencia + " Realizada com Sucesso para " + nomeDoRecebedor);
        }else{
            System.out.println("Impossível realizar a transferência!");
        }

    }

    /**
     * Método para realizar uma transferência PIX entre contas bancárias.
     * 
     * @param entrada       Scanner para entrada de dados do usuário.
     * @param bancoCriado   Banco onde a conta está localizada.
     * @param indexDoUsuario    Índice da conta bancária do usuário.
     */
    public static void 
    pix(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        Locale.setDefault(Locale.US);

        System.out.print("\033[H\033[2J");
        System.out.println("Digite o valor que deseja transferir: ");
        Double valorParaPix = entrada.nextDouble();
        System.out.println();
        System.out.println("Digite a chave PIX da conta que irá receber a transferência (CPF/CNPJ ou EMAIL): ");

        entrada.nextLine();
        String chavePixRecebedora = entrada.nextLine();

        // Pegando o index da conta que irá receber e guardando em indexDestino
        Integer indexDestinoPix = bancoCriado.getIndexDestinatarioPix(chavePixRecebedora);

        // Pegando o nome de quem irá receber o dinheiro
        String nomeDoRecebedorPix = bancoCriado.getDados(indexDestinoPix, TipoDeDado.NOME);

         // Sacando da conta que está enviando o dinheiro e depositando na conta recebedora
         if (bancoCriado.getConta(indexDoUsuario).sacar(valorParaPix) && bancoCriado.getConta(indexDestinoPix).depositar(valorParaPix)) {
            System.out.println("Pix no valor de R$ " + valorParaPix + " Realizado com Sucesso para " + nomeDoRecebedorPix);
        }else{
            System.out.println("Impossível realizar a transferência!");
        }
    }
}

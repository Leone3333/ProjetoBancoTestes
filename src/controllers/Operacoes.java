package controllers;

import java.util.Scanner;
import java.util.Locale;
import entities.Banco;
import entities.enums.TipoDeDado;

public class Operacoes {
    public static void depositar(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){
        
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

    public static void sacar(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        Locale.setDefault(Locale.US);

        System.out.print("\033[H\033[2J");
        System.out.println("Digite o valor que deseja sacar: ");
        Double valorParaSaque = entrada.nextDouble();

        System.out.print("\033[H\033[2J");
        if (bancoCriado.getConta(indexDoUsuario).sacar(valorParaSaque)){
        System.out.println("Saque no valor de R$" + valorParaSaque + " Realizado com Sucesso!");
        }
        System.out.println("Seu saldo atual é: R$" + bancoCriado.getDados(indexDoUsuario, TipoDeDado.SALDO));
        
    }

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

    public static void pix(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        Locale.setDefault(Locale.US);

        System.out.print("\033[H\033[2J");
        System.out.println("Digite o valor que deseja transferir: ");
        Double valorParaPix = entrada.nextDouble();
        System.out.println();
        System.out.println("Digite a chave PIX da conta que irá receber a transferência (CPF ou EMAIL): ");

        entrada.nextLine();
        String chavePixRecebedora = entrada.nextLine();

        // Pegando o index da conta que irá receber e guardando em indexDestino
        Integer indexDestinoPix = bancoCriado.getIndexDestinatario(chavePixRecebedora);

        // Pegando o nome de quem irá receber o dinheiro
        String nomeDoRecebedorPix = bancoCriado.getDados(indexDestinoPix, TipoDeDado.NOME);

         // Sacando da conta que está enviando o dinheiro e depositando na conta recebedora
         if (bancoCriado.getConta(indexDoUsuario).sacar(valorParaPix) && bancoCriado.getConta(indexDestinoPix).depositar(valorParaPix)) {
            System.out.println("Pix no valor de R$ " + valorParaPix + " Realizado com Sucesso para " + nomeDoRecebedorPix);
        }else{
            System.out.println("Impossível realizar a transferência!");
        }
    }

    public static void extrato(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getDados(indexDoUsuario, TipoDeDado.EMAIL)); 
        System.out.println();
        System.out.println("Estamos na tela de extrato, deu certo");

    }
}
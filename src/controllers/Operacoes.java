package controllers;

import java.util.Scanner;
import java.util.Locale;
import entities.Banco;
import entities.enums.TipoDeDado;
import entities.Conta;

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
        System.out.println("Digite a conta que irá receber a transferência: ");
        String numeroDaContaRecebedora = entrada.nextLine();

        bancoCriado.getConta(indexDoUsuario).sacar(valorParaTransferencia);


        //bancoCriado.getConta(indexDoUsuario).depositar(valorParaTransferencia);
        

    }

    public static void extrato(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getDados(indexDoUsuario, TipoDeDado.EMAIL)); 
        System.out.println();
        System.out.println("Estamos na tela de extrato, deu certo");

    }
}
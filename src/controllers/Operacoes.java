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
        System.out.println("Seu saldo atual é: R$" + bancoCriado.getConta(indexDoUsuario).getSaldo());
        
    }

    public static void sacar(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getContasNoBanco().get(indexDoUsuario).getEnderecoEmail()); 
        System.out.println();
        System.out.println("Estamos na tela de sacar, deu certo");
    }

    public static void transferir(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getDados(indexDoUsuario, TipoDeDado.EMAIL)); 
        System.out.println();
        System.out.println("Estamos na tela de transferir, deu certo");
        
    }

    public static void extrato(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getContasNoBanco().get(indexDoUsuario).getEnderecoEmail()); 
        System.out.println();
        System.out.println("Estamos na tela de extrato, deu certo");

    }
}
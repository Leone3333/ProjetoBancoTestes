package controllers;

import java.util.Scanner;

import entities.Banco;

public class Operacoes {

    public static void depositar(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println("Digite o valor que deseja depositar: ");
        double valorParaDeposito = entrada.nextDouble();

        bancoCriado.getContasNoBanco().get(indexDoUsuario).depositar(valorParaDeposito);
        System.out.println("Depósito no valor de R$ " + valorParaDeposito + " Realizado com Sucesso!");
        System.out.println("Seu saldo atual é: " + bancoCriado.getContasNoBanco().get(indexDoUsuario).getSaldo());
        
    }

    public static void sacar(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getContasNoBanco().get(indexDoUsuario).getEnderecoEmail()); 
        System.out.println();
        System.out.println("Estamos na tela de sacar, deu certo");
    }

    public static void transferir(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getContasNoBanco().get(indexDoUsuario).getEnderecoEmail()); 
        System.out.println();
        System.out.println("Estamos na tela de transferir, deu certo");
        
    }

    public static void extrato(Scanner entrada, Banco bancoCriado, Integer indexDoUsuario){

        System.out.println(bancoCriado.getContasNoBanco().get(indexDoUsuario).getEnderecoEmail()); 
        System.out.println();
        System.out.println("Estamos na tela de extrato, deu certo");

    }
}
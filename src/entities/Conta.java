package entities;

import java.util.Random;
import java.util.Scanner;

import services.email.Email;
import services.email.Servidor;
import validation.ValidarEmail;
import validation.ValidarSenha;

// Classe abstrata Conta que serve como modelo para ContaPF e ContaPJ  
public abstract class Conta{

    // Atributos protegidos para serem acessados pelas subclasses.
    protected String numeroDaConta;
    protected String tipoDaConta;
    protected String senhaDaConta;
    protected double saldoDaConta;
    protected Email enderecoEmail;
    
    // Método construtor da classe Conta
    public Conta(String senhaDaConta, Email enderecoEmail){
        this.senhaDaConta = senhaDaConta;
        this.enderecoEmail = enderecoEmail;
        this.numeroDaConta = gerarNumeroDaConta(); // Gera um número de conta
    }

    // Métodos abstratos a serem implementados pelas subclasses
    public abstract String getIdentificacao(); // Retorna a identificação (CPF ou CNPJ)
    public abstract String getNome(); // Retorna o nome da empresa ou da pessoa
    public abstract String getData(); // Retorna a data de criação da empresa ou data de nascimento da pessoa física

    public abstract void setNome(Scanner entrada); // Define o nome da empresa ou da pessoa
    public abstract void setData(Scanner entrada); // Define a data de criação da empresa ou data de nascimento da pessoa física

    // Retorna o endereço de e-mail
    public String getEnderecoEmail(){
        return this.enderecoEmail.getEmail();
    }

    //Mudar o endereço de e-mail
    public void setEnderecoEmail(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){
        enderecoEmail.deletarCaixaDeEntrada();
        enderecoEmail.setEmail(ValidarEmail.atualizarEValidarEmail(entrada, bancoCriado, servidorEmail));
    }

    // Retorna o tipo da conta
    public String getTipoDaConta(){
        return this.tipoDaConta;
    }

    // Retorna o número da conta
    public String getNumeroDaConta(){
        return this.numeroDaConta;
    }

    // Retorna o saldo da conta
    public String getSaldo(){
        return String.format("%.2f", saldoDaConta);
    }

    //Retorna a senha da conta
    public String getSenha(){
        return this.senhaDaConta;
    }

    //Atualizar a senha da conta.
    public void setSenha(Scanner entrada){
        this.senhaDaConta = ValidarSenha.solicitarEValidarSenha(entrada); 
    }

    // Método para gerar um número de conta
    public String gerarNumeroDaConta(){
        Random range = new Random();
        Integer codigo = range.nextInt(99999 - 10000 + 1) + 10000; //entre 10000 e 99999  
        Integer digito = range.nextInt(9 - 1 + 1) + 1; //entre 1 e 9 
        String idGerado = codigo.toString() + "-" + digito.toString(); 
        return idGerado;
    }

    // Método para depositar dinheiro na conta
    public void depositar(double valor){
        if(valor >= 1){
        this.saldoDaConta += valor;
        } else {
            System.out.println("o Valor mínimo para depósito é de R$ 1,00!");
        }
    }
    
    public boolean sacar(double valor){
        boolean sacou = false;
        if(valor <= saldoDaConta && valor > 0){
            
            this.saldoDaConta -= valor;
            return sacou = true;

        } else{
            System.out.println("Impossível sacar este valor!");
        }
        return sacou;
    }
}

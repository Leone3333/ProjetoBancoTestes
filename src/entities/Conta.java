package entities;

import java.util.Random;

import services.email.Email;

public abstract class Conta{

    protected String numeroDaConta;
    protected String tipoDaConta;
    protected String senhaDaConta;
    protected double saldoDaConta;
    protected Email enderecoEmail;
    
    //Método construtor
    public Conta(String senhaDaConta){
        this.senhaDaConta = senhaDaConta;
        this.numeroDaConta = gerarNumeroDaConta();
    }

    //Getters abstratos.
    public abstract String getIdentificacao(); //Pegar a indentificacao sendo CPF ou CNPJ.
    public abstract String getNome(); //Pegar o nome da empresa ou da pessoa.
    public abstract String getData(); //Pegar a data de criação da empresa ou data de nascimento da pessoa física.

    //Setters abstratos.
    public abstract void setEnderecoEmail(Email email); //Mudar o endereço de e-mail
    public abstract void setNome(String nome); //Mudar o nome da empresa ou da pessoa
    public abstract void setData(String data); //Mudar a data de criação da empresa ou data de nascimento da pessoa física.

    //Pegar e-mail.
    public String getEnderecoEmail(){
        return this.enderecoEmail.getEmail();
    }

    //Pegar o tipo da conta.
    public String getTipoDaConta(){
        return this.tipoDaConta;
    }

    //Pegar o numero da conta.
    public String getNumeroDaConta(){
        return this.numeroDaConta;
    }

    //pegar o saldo da conta.
    public double getSaldo(){
        return this.saldoDaConta;
    }

    //Gerar uma conta.
    public String gerarNumeroDaConta(){
        Random range = new Random();
        Integer codigo = range.nextInt(99999 - 10000 + 1) + 10000;
        Integer digito = range.nextInt(9 - 1 + 1) + 1;
        String idGerado = codigo.toString() + "-" + digito.toString();
        return idGerado;
    }

    public void depositar(double valor){
        this.saldoDaConta += valor;
    }
    
}
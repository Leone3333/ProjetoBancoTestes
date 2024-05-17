package entities;

import java.util.Random;
import java.util.Scanner;

import services.email.Email;
import services.email.Servidor;
import validation.ValidarEmail;
import validation.ValidarSenha;

/**
 * Classe abstrata Conta que serve como modelo para ContaPF e ContaPJ.
 */
public abstract class Conta {

    protected String numeroDaConta; // Número da conta
    protected String tipoDaConta; // Tipo da conta
    protected String senhaDaConta; // Senha da conta
    protected double saldoDaConta; // Saldo da conta
    protected Email enderecoEmail; // Endereço de email associado à conta
    
    /**
     * Construtor da classe Conta.
     * @param senhaDaConta a senha da conta
     * @param enderecoEmail o endereço de email associado à conta
     */
    public Conta(String senhaDaConta, Email enderecoEmail){
        this.senhaDaConta = senhaDaConta;
        this.enderecoEmail = enderecoEmail;
        this.numeroDaConta = gerarNumeroDaConta(); // Gera um número de conta
    }

    /**
     * Método abstrato para obter a identificação (CPF ou CNPJ) da conta.
     * @return a identificação (CPF ou CNPJ) da conta
     */
    public abstract String getIdentificacao();

    /**
     * Método abstrato para obter o nome do titular da conta.
     * @return o nome do titular da conta
     */
    public abstract String getNome();

    /**
     * Método abstrato para obter a data associada à conta (data de nascimento ou data de criação).
     * @return a data associada à conta
     */
    public abstract String getData();

    /**
     * Método abstrato para definir o nome do titular da conta.
     * @param entrada scanner para entrada de dados
     */
    public abstract void setNome(Scanner entrada);

    /**
     * Método abstrato para definir a data associada à conta (data de nascimento ou data de criação).
     * @param entrada scanner para entrada de dados
     */
    public abstract void setData(Scanner entrada);

    /**
     * Obtém o endereço de email associado à conta.
     * @return o endereço de email associado à conta
     */
    public String getEnderecoEmail(){
        return this.enderecoEmail.getEmail();
    }

    /**
     * Define o endereço de e-mail da conta.
     * @param enderecoEmail O novo endereço de e-mail a ser definido.
     */
    public void setEnderecoEmail(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {
        this.enderecoEmail = ValidarEmail.solicitarEValidarEmail(entrada, bancoCriado, servidorEmail); 
    }

    /**
     * Obtém o tipo da conta.
     * @return o tipo da conta
     */
    public String getTipoDaConta(){
        return this.tipoDaConta;
    }

    /**
     * Obtém o número da conta.
     * @return o número da conta
     */
    public String getNumeroDaConta(){
        return this.numeroDaConta;
    }

    /**
     * Obtém o saldo da conta.
     * @return o saldo da conta
     */
    public String getSaldo(){
        return String.format("%.2f", saldoDaConta);
    }

    /**
     * Obtém a senha da conta.
     * @return a senha da conta
     */
    public String getSenha(){
        return this.senhaDaConta;
    }

  
    public void setSenha(Scanner entrada){
        this.senhaDaConta = ValidarSenha.solicitarEValidarSenha(entrada); 
    }

    /**
     * Método para gerar um número de conta.
     * @return o número de conta gerado
     */
    public String gerarNumeroDaConta(){
        Random range = new Random();
        Integer codigo = range.nextInt(99999 - 10000 + 1) + 10000; // entre 10000 e 99999  
        Integer digito = range.nextInt(9 - 1 + 1) + 1; // entre 1 e 9 
        return codigo.toString() + "-" + digito.toString(); 
    }

    /**
     * Método para depositar dinheiro na conta.
     * @param valor o valor a ser depositado
     * @return true se o depósito for bem-sucedido, false caso contrário
     */
    public Boolean depositar(double valor){
        boolean depositou = false;
        if(valor >= 0.01){
            this.saldoDaConta += valor;
            depositou = true;
        } else {
            System.out.println("o Valor mínimo para depósito é de R$ 0,01!");
        }
        return depositou;
    }
    
    /**
     * Método para sacar dinheiro da conta.
     * @param valor o valor a ser sacado
     * @return true se o saque for bem-sucedido, false caso contrário
     */
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

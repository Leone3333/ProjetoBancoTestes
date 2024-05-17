package entities;

import java.util.Scanner;

import services.email.Email;
import validation.ValidarPJ;

/**
 * Classe que representa uma conta de Pessoa Jurídica (ContaPJ), que é uma subclasse de Conta.
 */
public class ContaPJ extends Conta {
    
    private String nomeDaEmpresa; // Nome da empresa
    private String cnpj; // CNPJ da empresa
    private String dataDeCriacao; // Data de criação da empresa
    private final Double limiteCredito = 15000D; // Limite do crédito da empresa
    private final Double valorTaxaSaque = 0.02D; // Valor da taxa para saque em porcentagem. Atual: 2%
    
    /**
     * Construtor da classe ContaPJ.
     * 
     * @param senhaDaConta  Senha da conta.
     * @param nomeDaEmpresa Nome da empresa.
     * @param cnpj          CNPJ da empresa.
     * @param dataDeCriacao Data de criação da empresa.
     * @param enderecoEmail Endereço de e-mail associado à conta.
     */
    public ContaPJ(String senhaDaConta, String nomeDaEmpresa, String cnpj, String dataDeCriacao, Email enderecoEmail) {
        super(senhaDaConta, enderecoEmail); // Chama o construtor da classe pai passando a senha e o email
        this.nomeDaEmpresa = nomeDaEmpresa; // Inicializa o nome da empresa
        this.cnpj = cnpj; // Inicializa o CNPJ
        this.dataDeCriacao = dataDeCriacao; // Inicializa a data de criação
        this.tipoDaConta = "Pessoa Jurídica"; // Define o tipo da conta como Pessoa Jurídica
    }

    /**
     * Método para obter a identificação (CNPJ) da conta.
     * 
     * @return O CNPJ da empresa.
     */
    @Override
    public String getIdentificacao() {
        return cnpj;
    }

    /**
     * Método para obter o nome da empresa.
     * 
     * @return O nome da empresa.
     */
    @Override
    public String getNome() {
        return nomeDaEmpresa;
    }

    /**
     * Método para obter a data de criação da empresa.
     * 
     * @return A data de criação da empresa.
     */
    @Override
    public String getData() {
        return dataDeCriacao;
    }

    /**
     * Método para atualizar o nome da empresa.
     * 
     * @param entrada Scanner para entrada de dados.
     */
    @Override
    public void setNome(Scanner entrada) {
        this.nomeDaEmpresa = ValidarPJ.solicitarEValidarNome(entrada);
    }

    /**
     * Método para atualizar a data de criação da empresa.
     * 
     * @param entrada Scanner para entrada de dados.
     */
    @Override
    public void setData(Scanner entrada) {
        this.dataDeCriacao = ValidarPJ.solicitarEValidarNome(entrada);
    }

    /**
     * Método para sacar um valor da conta.
     * 
     * @param valor Valor a ser sacado.
     * @return true se o saque for bem-sucedido, false caso contrário.
     */
    @Override
    public boolean sacar(double valor){ 
        boolean sacou = false;
        if(valor <= saldoDaConta + limiteCredito && valor > 0.01){
            this.saldoDaConta -= valor + valorTaxaSaque * valor;
            return sacou = true;
        } else{
            System.out.println("Impossível sacar este valor!");
        }
        return sacou;
    }

}

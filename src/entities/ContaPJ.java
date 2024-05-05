package entities;

import java.util.Scanner;

import services.email.Email;
import validation.ValidarPJ;

// Classe que representa uma conta de Pessoa Jurídica (ContaPJ), que é uma subclasse de Conta.
public class ContaPJ extends Conta {
    
    private String nomeDaEmpresa; // Nome da empresa
    private String cnpj; // CNPJ da empresa
    private String dataDeCriacao; // Data de criação da empresa
    
    // Construtor da classe ContaPJ
    public ContaPJ(String senhaDaConta, String nomeDaEmpresa, String cnpj, String dataDeCriacao, Email enderecoEmail) {
        super(senhaDaConta, enderecoEmail); // Chama o construtor da classe pai passando a senha e o email
        this.nomeDaEmpresa = nomeDaEmpresa; // Inicializa o nome da empresa
        this.cnpj = cnpj; // Inicializa o CNPJ
        this.dataDeCriacao = dataDeCriacao; // Inicializa a data de criação
        this.tipoDaConta = "Pessoa Jurídica"; // Define o tipo da conta como Pessoa Jurídica
    }

    // Método para obter a identificação (CNPJ) da conta.
    @Override
    public String getIdentificacao() {
        return cnpj;
    }

    // Método para obter o nome da empresa.
    @Override
    public String getNome() {
        return nomeDaEmpresa;
    }

    // Método para obter a data de criação da empresa.
    @Override
    public String getData() {
        return dataDeCriacao;
    }

    // Método para atualiazar o nome da empresa.
    @Override
    public void setNome(Scanner entrada) {
    
        this.nomeDaEmpresa = ValidarPJ.solicitarEValidarNome(entrada);

    }

    // Método para atualizar a data de criação da empresa.
    @Override
    public void setData(Scanner entrada) {
        this.dataDeCriacao = ValidarPJ.solicitarEValidarNome(entrada);
    }

}

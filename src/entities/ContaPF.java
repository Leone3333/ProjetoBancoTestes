package entities;

import java.util.Scanner;

import services.email.Email;
import validation.ValidarPF;

/**
 * Classe que representa uma conta de Pessoa Física (ContaPF), que é uma subclasse de Conta.
 */
public class ContaPF extends Conta {
    
    private String nomeDoTitular; // Nome do titular da conta
    private String cpf; // CPF do titular
    private String dataDeNascimento; // Data de nascimento do titular
    
    /**
     * Construtor da classe ContaPF.
     * @param senhaDaConta a senha da conta
     * @param nomeDoTitular o nome do titular da conta
     * @param cpf o CPF do titular da conta
     * @param dataDeNascimento a data de nascimento do titular da conta
     * @param enderecoEmail o endereço de email associado à conta
     */
    public ContaPF(String senhaDaConta, String nomeDoTitular, String cpf, String dataDeNascimento, Email enderecoEmail) {
        super(senhaDaConta, enderecoEmail); // Chama o construtor da classe pai passando a senha e o email
        this.cpf = cpf; // Inicializa o CPF
        this.nomeDoTitular = nomeDoTitular; // Inicializa o nome do titular
        this.dataDeNascimento = dataDeNascimento; // Inicializa a data de nascimento
        this.tipoDaConta = "Pessoa Física"; // Define o tipo da conta como Pessoa Física
    }

    /**
     * Obtém o CPF do titular da conta.
     * @return o CPF do titular da conta
     */
    @Override
    public String getIdentificacao() {
        return cpf;
    }

    /**
     * Obtém o nome do titular da conta.
     * @return o nome do titular da conta
     */
    @Override
    public String getNome() {
        return nomeDoTitular;
    }

    /**
     * Obtém a data de nascimento do titular da conta.
     * @return a data de nascimento do titular da conta
     */
    @Override
    public String getData() {
        return dataDeNascimento;
    }

    /**
     * Atualiza o nome do titular.
     * @param entrada scanner para entrada de dados
     */
    @Override
    public void setNome(Scanner entrada) {
        this.nomeDoTitular = ValidarPF.solicitarEValidarNome(entrada);
    }

    /**
     * Atualiza a data de nascimento do titular.
     * @param entrada scanner para entrada de dados
     */
    @Override
    public void setData(Scanner entrada) {
        this.dataDeNascimento = ValidarPF.solicitarEValidarDataDeNascimento(entrada);
    }

}

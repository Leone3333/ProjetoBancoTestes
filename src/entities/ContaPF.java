package entities;

import services.email.Email;

// Classe que representa uma conta de Pessoa Física (ContaPF), que é uma subclasse de Conta.
public class ContaPF extends Conta {
    
    private String nomeDoTitular; // Nome do titular da conta
    private String cpf; // CPF do titular
    private String dataDeNascimento; // Data de nascimento do titular
    
    // Construtor da classe ContaPF
    public ContaPF(String senhaDaConta, String nomeDoTitular, String cpf, String dataDeNascimento, Email enderecoEmail) {
        super(senhaDaConta, enderecoEmail); // Chama o construtor da classe pai passando a senha e o email
        this.cpf = cpf; // Inicializa o CPF
        this.nomeDoTitular = nomeDoTitular; // Inicializa o nome do titular
        this.dataDeNascimento = dataDeNascimento; // Inicializa a data de nascimento
        this.tipoDaConta = "Pessoa Física"; // Define o tipo da conta como Pessoa Física
    }

    // Método para obter a identificação (CPF) da conta
    @Override
    public String getIdentificacao() {
        return cpf;
    }

    // Método para obter o nome do titular da conta
    @Override
    public String getNome() {
        return nomeDoTitular;
    }

    // Método para obter a data de nascimento do titular da conta
    @Override
    public String getData() {
        return dataDeNascimento;
    }

    // Método para definir o nome do titular da conta
    @Override
    public void setNome(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
    }

    // Método para definir a data de nascimento do titular da conta
    @Override
    public void setData(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    
}

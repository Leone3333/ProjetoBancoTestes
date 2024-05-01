package entities;

import services.email.Email;

public class ContaPF extends Conta {
    
    private String nomeDoTitular;
    private String cpf;
    private String dataDeNascimento;

    
    public ContaPF(String senhaDaConta, String cpf, String nomeDoTitular, String dataDeNascimento, Email enderecoEmail) {
        super(senhaDaConta);
        this.cpf = cpf;
        this.nomeDoTitular = nomeDoTitular;
        this.dataDeNascimento = dataDeNascimento;
        this.enderecoEmail = enderecoEmail;
        this.tipoDaConta = "Pessoa Física";
    }

    @Override
    public String getIdentificacao() {
        return cpf;
    }

    @Override
    public String getNome() {
        return nomeDoTitular;
    }

    @Override
    public String getData() {
        return dataDeNascimento;
    }

    @Override
    public void setNome(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
    }

    @Override
    public void setData(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public void setEnderecoEmail(Email enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }

    

    


}

package entities;

import services.email.Email;

public class ContaPJ extends Conta {
    
    private String nomeDaEmpresa;
    private String cnpj;
    private String dataDeCriacao;
    
    public ContaPJ(String senhaDaConta, String nomeDaEmpresa, String cnpj, String dataDeCriacao, Email enderecoEmail) {
        super(senhaDaConta);
        this.nomeDaEmpresa = nomeDaEmpresa;
        this.cnpj = cnpj;
        this.dataDeCriacao = dataDeCriacao;
        this.enderecoEmail = enderecoEmail;
        this.tipoDaConta = "Pessoa Jurídica";
    }

    @Override
    public String getIdentificacao() {
        return cnpj;
    }

    @Override
    public String getNome() {
        return nomeDaEmpresa;
    }

    @Override
    public String getData() {
        return dataDeCriacao;
    }

    @Override
    public void setNome(String nomeDaEmpresa) {
        this.nomeDaEmpresa = nomeDaEmpresa;
    }

    @Override
    public void setData(String dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    @Override
    public void setEnderecoEmail(Email enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }

    
}

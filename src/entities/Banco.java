package entities;

import java.util.ArrayList;
import java.util.Random;

import services.email.Email;
import services.email.Mensagem;
import entities.enums.TipoDeDado;

// Classe que representa um Banco, responsável por gerenciar contas bancárias.
public class Banco {
    
    // Atributos
    private String nomeDoBanco; // Nome do banco
    private Email emailDoBanco; // Endereço de e-mail do banco
    private String numeroDaAgencia; // Número da agência bancária
    private Integer codigoEnviado; // Código enviado para verificação

    private ArrayList<Conta> contasNoBanco; // Lista de contas no banco

    // Método construtor para criar o banco
    public Banco(String nomeDoBanco, String emailDoBanco) {
        this.nomeDoBanco = nomeDoBanco; // Inicializa o nome do banco
        this.numeroDaAgencia = gerarNumeroDaAgencia(); // Gera o número da agência
        this.emailDoBanco = new Email(emailDoBanco); // Inicializa o endereço de e-mail do banco
        this.contasNoBanco = new ArrayList<>(); // Inicializa a lista de contas
    }

    // Método para obter o nome do banco
    public String getNomeDoBanco() {
        return this.nomeDoBanco;
    }

    // Método para obter a lista de contas no banco
    public ArrayList<Conta> getContasNoBanco() {
        return this.contasNoBanco;
    }

    // Método para obter os dados do destinatário
    public Integer getIndexDestinatario(String contaDestino){

        for(int i = 0; i < contasNoBanco.size(); i++){
            if(contasNoBanco.get(i).getNumeroDaConta().equals(contaDestino)){
                return i;
            }
        }
        return null;
    }


    // Método para obter o endereço de e-mail do banco
    public String getEmailDoBanco() {
        return this.emailDoBanco.getEmail();
    }

    // Método para obter o número da agência bancária
    public String getNumeroDaAgencia() {
        return this.numeroDaAgencia;
    }



    // Método para obter dados específicos de uma conta com base no índice e no tipo de dado
    public String getDados(int index, TipoDeDado dado){

        switch (dado) {
            // Retorna o atributo correspondente ao tipo de dado solicitado
            case SENHA:
                return contasNoBanco.get(index).getSenha();
            case EMAIL:
                return contasNoBanco.get(index).getEnderecoEmail();
            case ENDERECODEEMAIL:
                return contasNoBanco.get(index).getEnderecoEmail();
            case NOME:
                return contasNoBanco.get(index).getNome();
            case IDENTIFICACAO:
                return contasNoBanco.get(index).getIdentificacao();
            case DATA:
                return contasNoBanco.get(index).getData();
            case NUMERODACONTA:
                return contasNoBanco.get(index).getNumeroDaConta();
            case TIPODACONTA:
                return contasNoBanco.get(index).getTipoDaConta();
            case SALDO:
                return contasNoBanco.get(index).getSaldo();
            default:
                return "Erro!";
        }
    }

    // Métodos para manipular a lista de contas no banco

    // Método para obter uma conta pelo índice na lista
    public Conta getConta(int index){
        return contasNoBanco.get(index);
    }

    // Método para adicionar uma conta à lista de contas do banco
    public void cadastrarConta(Conta conta){
        this.contasNoBanco.add(conta);
    }

    // Método para remover uma conta da lista de contas do banco
    public void removerConta(int indexDoUsuario){
        contasNoBanco.remove(indexDoUsuario);
    }
    // Outros métodos

    // Método para gerar um número da agência
    public String gerarNumeroDaAgencia(){
        Random range = new Random();
        Integer codigo = range.nextInt(9999 - 1000 + 1) + 1000;
        Integer digito = range.nextInt(9 - 1 + 1) + 1;
        String idGerado = codigo.toString() + "-" + digito.toString();
        return idGerado;
    }

    // Método para exibir as informações da conta de acordo com o tipo
    public String exibirDados(String informacaoBusca){

        for(Conta conta : contasNoBanco){
            if(conta.getNumeroDaConta().equals(informacaoBusca) || 
            conta.getEnderecoEmail().equals(informacaoBusca)){

                // Verificar se a conta é uma instância de ContaPF ou ContaPJ e formatar os dados
                if (conta instanceof ContaPF){
                    return "Nome do Titular: " + conta.getNome() + "\n"
                    + "Data de nascimento: " + conta.getData() + "\n"
                    + "CPF: " + conta.getIdentificacao() + "\n"
                    + "Tipo da Conta: " + conta.getTipoDaConta() + "\n"
                    + "Número da conta: " + conta.getNumeroDaConta() + "\n"
                    + "Endereço de e-mail " + conta.getEnderecoEmail();
                } else {
                    return "Nome da Empresa: " + conta.getNome() + "\n"
                    + "Data de criação: " + conta.getData() + "\n"
                    + "CNPJ: " + conta.getIdentificacao() + "\n"
                    + "Tipo da Conta: " + conta.getTipoDaConta() + "\n"
                    + "Número da conta: " + conta.getNumeroDaConta() + "\n"
                    + "Endereço de e-mail " + conta.getEnderecoEmail();
                }
            }
        }

        return "Não foi possível exibir os dados da conta";
    }

    // Método para enviar um código de verificação para um destinatário por e-mail
    public Mensagem enviarCodigo(String emailDoDestinatario){

        Random range = new Random();
        Integer codigo = range.nextInt(99999 - 10000 + 1) + 10000;
        this.codigoEnviado = codigo;
        Mensagem mensagem = new Mensagem
        (emailDoBanco.getEmail(), emailDoDestinatario, "Seu código de verificação é: " + codigo);
        return mensagem;

    }

    // Método para verificar se um código digitado corresponde ao código enviado anteriormente
    public boolean verificarCodigo(Integer codigoDigitado){
        if(codigoDigitado.equals(codigoEnviado)){
            return true;
        } else {
            return false;
        }
    }

    // Método para enviar os dados da conta criada para um destinatário por e-mail
    public Mensagem enviarDados(String emailDoDestinatario){
        String contaCriada = 
          "Boas notícias! Sua conta foi criada com sucesso." + "\n" 
        + "Estamos felizes por ter você aqui!" + "\n" + "\n"
        + "Agência: " + getNumeroDaAgencia() + "\n";

        String dados = contaCriada + exibirDados(emailDoDestinatario);
        Mensagem mensagem = new Mensagem(emailDoBanco.getEmail(), emailDoDestinatario, dados);
        return mensagem;
    }
}

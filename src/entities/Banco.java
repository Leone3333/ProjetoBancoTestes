package entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import services.email.Email;
import services.email.Mensagem;
import validation.ValidarSenha;
import entities.enums.TipoDeDado;

/**
 * Classe que representa um Banco, responsável por gerenciar contas bancárias.
 */
public class Banco {
    
    // Atributos
    private String nomeDoBanco; // Nome do banco
    private Email emailDoBanco; // Endereço de e-mail do banco
    private String numeroDaAgencia; // Número da agência bancária
    private Integer codigoEnviado; // Código enviado para verificação
    
    private Email emailDoAdm; // Endereço de e-mail do admin;
    private String senhaDoAdm; // Senha do admin;

    private ArrayList<Conta> contasNoBanco; // Lista de contas no banco

    /**
     * Método construtor para criar o banco.
     * @param nomeDoBanco O nome do banco.
     * @param emailDoBanco O endereço de e-mail do banco.
     * @param emailDoAdm O endereço de e-mail do administrador do banco.
     * @param senhaDoAdm A senha do administrador do banco.
     */
    public Banco(String nomeDoBanco, String emailDoBanco, String emailDoAdm, String senhaDoAdm) {
        this.nomeDoBanco = nomeDoBanco; // Inicializa o nome do banco
        this.numeroDaAgencia = gerarNumeroDaAgencia(); // Gera o número da agência
        this.emailDoBanco = new Email(emailDoBanco); // Inicializa o endereço de e-mail do banco
        this.emailDoAdm = new Email(emailDoAdm);
        this.senhaDoAdm = senhaDoAdm;
        this.contasNoBanco = new ArrayList<>(); // Inicializa a lista de contas
    }

    /**
     * Obtém o nome do banco.
     * @return O nome do banco.
     */
    public String getNomeDoBanco() {
        return this.nomeDoBanco;
    }

    /**
     * Obtém o endereço de e-mail do administrador do banco.
     * @return O endereço de e-mail do administrador do banco.
     */
    public String getEnderecoADM(){
        return this.emailDoAdm.getEmail();
    }

    /**
     * Obtém a senha do administrador do banco.
     * @return A senha do administrador do banco.
     */
    public String getSenhaADM(){
        return this.senhaDoAdm;
    }

    /**
     * Obtém a lista de contas no banco.
     * @return A lista de contas no banco.
     */
    public ArrayList<Conta> getContasNoBanco() {
        return this.contasNoBanco;
    }

    /**
     * Obtém o índice do destinatário com base no número da conta.
     * @param contaDestino O número da conta do destinatário.
     * @return O índice do destinatário na lista de contas, ou null se não encontrado.
     */
    public Integer getIndexDestinatario(String contaDestino){

        for(int i = 0; i < contasNoBanco.size(); i++){
            if(contasNoBanco.get(i).getNumeroDaConta().equals(contaDestino)){
                return i;
            }
        }
        return null;
    }

    /**
     * Obtém o índice do destinatário com base no número da conta PIX ou endereço de e-mail.
     * @param contaDestinoPix O número da conta PIX ou endereço de e-mail do destinatário.
     * @return O índice do destinatário na lista de contas, ou null se não encontrado.
     */
    public Integer getIndexDestinatarioPix(String contaDestinoPix){

        for(int i = 0; i < contasNoBanco.size(); i++){
            if (contasNoBanco.get(i).getEnderecoEmail().equals(contaDestinoPix) || contasNoBanco.get(i).getIdentificacao().equals(contaDestinoPix)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Define a senha do administrador do banco.
     * @param entrada O objeto Scanner para entrada do usuário.
     */
    public void setSenhaADM(Scanner entrada) {
        this.senhaDoAdm = ValidarSenha.solicitarEValidarSenha(entrada);
    }

    /**
     * Obtém o endereço de e-mail do banco.
     * @return O endereço de e-mail do banco.
     */
    public String getEmailDoBanco() {
        return this.emailDoBanco.getEmail();
    }

    /**
     * Obtém o número da agência bancária.
     * @return O número da agência bancária.
     */
    public String getNumeroDaAgencia() {
        return this.numeroDaAgencia;
    }

    /**
     * Obtém dados específicos de uma conta com base no índice e no tipo de dado.
     * @param index O índice da conta na lista de contas.
     * @param dado O tipo de dado a ser obtido.
     * @return O dado específico da conta.
     */
    public String getDados(int index, TipoDeDado dado){

        switch (dado) {
            // Retorna o atributo correspondente ao tipo de dado solicitado
            case SENHA:
                return contasNoBanco.get(index).getSenha();
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

    /**
     * Obtém uma conta pelo índice na lista de contas.
     * @param index O índice da conta na lista de contas.
     * @return A conta correspondente ao índice.
     */
    public Conta getConta(int index){
        return contasNoBanco.get(index);
    }

    /**
     * Adiciona uma conta à lista de contas do banco.
     * @param conta A conta a ser adicionada.
     */
    public void cadastrarConta(Conta conta){
        this.contasNoBanco.add(conta);
    }

       /**
     * Remove uma conta da lista de contas do banco.
     * @param indexDoUsuario O índice da conta a ser removida na lista de contas.
     */
    public void removerConta(int indexDoUsuario){
        contasNoBanco.remove(indexDoUsuario);
    }

    // Outros métodos

    /**
     * Gera um número da agência aleatório.
     * @return O número da agência gerado.
     */
    public String gerarNumeroDaAgencia(){
        Random range = new Random();
        Integer codigo = range.nextInt(9999 - 1000 + 1) + 1000;
        Integer digito = range.nextInt(9 - 1 + 1) + 1;
        String idGerado = codigo.toString() + "-" + digito.toString();
        return idGerado;
    }

    /**
     * Exibe as informações da conta de acordo com o tipo de busca.
     * @param informacaoBusca O número da conta ou endereço de e-mail do titular da conta.
     * @return As informações da conta formatadas.
     */
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

    /**
     * Exibe as informações da conta com base no índice na lista de contas.
     * @param index O índice da conta na lista de contas.
     * @return As informações da conta formatadas.
     */
    public String exibirDados(int index){
        Conta conta = contasNoBanco.get(index);

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

    /**
     * Envia um código de verificação para um destinatário por e-mail.
     * @param emailDoDestinatario O endereço de e-mail do destinatário.
     * @return A mensagem contendo o código de verificação.
     */
    public Mensagem enviarCodigo(String emailDoDestinatario){

        Random range = new Random();
        Integer codigo = range.nextInt(99999 - 10000 + 1) + 10000;
        this.codigoEnviado = codigo;
        Mensagem mensagem = new Mensagem
        (emailDoBanco.getEmail(), emailDoDestinatario, "Seu código de verificação é: " + codigo);
        return mensagem;

    }

    /**
     * Verifica se um código digitado corresponde ao código enviado anteriormente.
     * @param codigoDigitado O código digitado pelo usuário.
     * @return true se o código corresponde, false caso contrário.
     */
    public boolean verificarCodigo(Integer codigoDigitado){
        if(codigoDigitado.equals(codigoEnviado)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Envia os dados da conta criada para um destinatário por e-mail.
     * @param emailDoDestinatario O endereço de e-mail do destinatário.
     * @return A mensagem contendo os dados da conta.
     */
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


     
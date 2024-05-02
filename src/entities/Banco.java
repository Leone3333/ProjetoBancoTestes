package entities;

import java.util.ArrayList;
import java.util.Random;

import services.email.Email;
import services.email.Mensagem;

public class Banco {
    
    private String nomeDoBanco;
    private Email emailDoBanco;
    private String numeroDaAgencia;
    private Integer codigoEnviado;

    private ArrayList<Conta> contasNoBanco;


    //Método construtor para criar o banco
    public Banco(String nomeDoBanco, String emailDoBanco) {
        this.nomeDoBanco = nomeDoBanco;
        this.numeroDaAgencia = gerarNumeroDaAgencia();

        this.emailDoBanco = new Email(emailDoBanco);
        this.contasNoBanco = new ArrayList<>();
    }

    public String getNomeDoBanco() {
        return this.nomeDoBanco;
    }

    public ArrayList<Conta> getContasNoBanco() {
        return this.contasNoBanco;
    }

    public String getEmailDoBanco() {
        return this.emailDoBanco.getEmail();
    }

    public String getNumeroDaAgencia() {
        return this.numeroDaAgencia;
    }

    public Conta getConta(int index){
        return contasNoBanco.get(index);
    }

    public void adicionarConta(Conta conta){
        this.contasNoBanco.add(conta);
    }

    //Método para gerar um numero da agência. Ex: 1234-5
    public String gerarNumeroDaAgencia(){
        Random range = new Random();
        Integer codigo = range.nextInt(9999 - 1000 + 1) + 1000;
        Integer digito = range.nextInt(9 - 1 + 1) + 1;
        String idGerado = codigo.toString() + "-" + digito.toString();
        return idGerado;
    }
    

    //Adicionar a conta no banco(na lista das contasNoBanco)
    public void cadastrarConta(Conta contaNova){
        contasNoBanco.add(contaNova);
    }


    //Exibir as informações da conta de acordo com o tipo
    public String exibirDados(String numeroDaConta){

        for(Conta conta : contasNoBanco){
            if(conta.getNumeroDaConta().equals(numeroDaConta)){

                //Verificar se a conta é uma instância de ContaPF
                if (conta instanceof ContaPF){
                    return "Nome da Empresa: " + conta.getNome() + "\n"
                    + "Data de criação: " + conta.getData() + "\n"
                    + "CNPJ: " + conta.getIdentificacao() + "\n"
                    + "Tipo da Conta: " + conta.getTipoDaConta() + "\n"
                    + "Número da conta: " + conta.getNumeroDaConta() + "\n"
                    + "Endereço de e-mail " + conta.getEnderecoEmail();
                }

                else{

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

    public Integer contaExiste(String dado){
        for(int index = 0; index < contasNoBanco.size(); index++){
            if(contasNoBanco.get(index).getIdentificacao().equals(dado)){
                return index;
            }

            if(contasNoBanco.get(index).getEnderecoEmail().equals(dado)){
                return index;
            }
        }
        return null;
    }

    public Mensagem enviarCodigo(String emailDoDestinatario){

        Random range = new Random();
        Integer codigo = range.nextInt(99999 - 10000 + 1) + 10000;
        this.codigoEnviado = codigo;
        Mensagem mensagem = new Mensagem
        (emailDoBanco.getEmail(), emailDoDestinatario, "Seu código de verificação é: " + codigo);
        return mensagem;

    }

    public boolean verificarCodigo(Integer codigoDigitado){
        if(codigoDigitado.equals(codigoEnviado)){
            return true;
        } else
        return false; 
    }

    public Mensagem enviarDados(String emailDoDestinatario){
        String contaCriada = "Boas notícias! Sua conta foi criada com sucesso. Estamos felizes por ter vc aqui" + "\n"
        + "Agência: " + getNumeroDaAgencia() + "\n";

        String dados = contaCriada + exibirDados(emailDoDestinatario);
        Mensagem mensagem = new Mensagem(emailDoBanco.getEmail(), emailDoDestinatario, dados);
        return mensagem;
    }
}

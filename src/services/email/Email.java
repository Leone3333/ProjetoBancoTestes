package services.email;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Email {

    private String email;

    // Cada email irá ter sua "caixa" de mensagens;
    private ArrayList<Mensagem> mensagensRecebidas;

    // Construtor da classe Email
    public Email(String email) {
        this.email = email;
        this.mensagensRecebidas = new ArrayList<>();
    }


    public ArrayList<Mensagem> getMensagensRecebidas(){
     
        return this.mensagensRecebidas;
    }

    public String getEmail(){
        return this.email;
    }

    // Método para adicionar uma mensagem recebida
    public void adicionarMensagem(Mensagem mensagem) {
        this.mensagensRecebidas.add(mensagem);
    }


    public void exibirEmailsRecebidos(){

        //Deve-se verificar se está correto
         String caminhoDoArquivo = "servidorDeEmail\\" + "caixaDeEntrada-" + getEmail() +".txt";

        try(BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoDoArquivo, true))){

            for(Mensagem mensagem : mensagensRecebidas){
        
                arquivo.write(mensagem.exibirMensagem());
                arquivo.newLine();
            }

        } catch(IOException e){
            System.out.println("Erro ao criar o arquivo!");
        }
    }


}

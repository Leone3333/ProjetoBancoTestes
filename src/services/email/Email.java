package services.email;

import java.io.BufferedWriter;
import java.io.File;
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

    public void setEmail(String email){
        this.email = email;
    }

    // Método para adicionar uma mensagem recebida
    public void adicionarMensagem(Mensagem mensagem) {
        this.mensagensRecebidas.add(mensagem);
    }

    public void deletarMensagem(int indexMensagem){
        this.mensagensRecebidas.remove(indexMensagem);
    }

    public void deletarCaixaDeEntrada(){
        String caminhoDoArquivo = "servidorDeEmail\\" + "caixaDeEntrada-" + getEmail() +".txt";
        File arquivo = new File(caminhoDoArquivo);  
        arquivo.delete();

    }

    public void exibirEmailsRecebidos(){

        //Deve-se verificar se está correto
         String caminhoDoArquivo = "servidorDeEmail\\" + "caixaDeEntrada-" + getEmail() +".txt";

        try(BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoDoArquivo, true))){

            for(int index = 0; index < mensagensRecebidas.size(); index++){

                arquivo.write(mensagensRecebidas.get(index).exibirMensagem());
                deletarMensagem(index);
                arquivo.newLine();
            }


        } catch(IOException e){
            System.out.println("Erro ao criar o arquivo!");
        }
    }


}

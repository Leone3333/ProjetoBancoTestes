package services.email;

import java.util.ArrayList;

public class Servidor {

    private ArrayList<Email> emailsCadastrados;

    // Construtor da classe Servidor
    public Servidor() {
        emailsCadastrados = new ArrayList<>();
    }

    // Método para cadastrar um novo email no servidor
    public Email cadastrarEmail(String email) {

        Email novoEmail = new Email(email);
        emailsCadastrados.add(novoEmail);
        return novoEmail;
    }

    //Enviar mensagem para um destinátario
    public void armazenarMensagem(Mensagem mensagem){

        //Guardar o destinatario da mensagem
        String destinatario = mensagem.getDestinatario();

        //Pecorrer a lista
        for(Email email : emailsCadastrados){

            //Verificar se o destinatario existe no servidor
            if(email.getEmail().equals(destinatario)){

                //Se existir adicionar a mensagem na Caixa de Entrada do destinatário.
                email.adicionarMensagem(mensagem);
            }
        }
    }

    //Pegar a ArrayList "emailsCadastrados".
    public ArrayList<Email> getEmailsCadastrados() {
        return this.emailsCadastrados;
    }

    
}


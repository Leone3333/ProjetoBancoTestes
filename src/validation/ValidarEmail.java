package validation;

import java.util.Scanner;

import entities.Banco;
import services.email.Email;
import services.email.Servidor;

public class ValidarEmail {
   public static Email verificacaoDeEmail(Scanner entrada, Servidor servidorEmail, Banco bancoCriado, String emailDigitado){
    

        // Instância do Email.
        Email emailValido = servidorEmail.cadastrarEmail(emailDigitado);

        //Enviar um código de verificação
        servidorEmail.enviarMensagem(bancoCriado.enviarCodigo(emailDigitado));
        emailValido.exibirEmailsRecebidos();
    
        System.out.println();
        System.out.println("================ Verificação ================");
        System.out.println("Verifique o código enviado para o seu e-mail");
        System.out.println("Digite o código: ");
       

        boolean codigoCorreto = false;

        do {
            try {

                String codigoDigitado = entrada.nextLine();

                if(codigoDigitado.isEmpty()){
                    System.out.println("O campo não pode estar vazio");
                    System.out.println("Digite o código: ");
                }

                else{

                Integer codigoValido = Integer.parseInt(codigoDigitado);
                
                if(!(bancoCriado.verificarCodigo(codigoValido))){
                    System.out.println();
                    System.out.println("Código de verificação incorreto!");
                    System.out.println("Digite o código novamente: ");

                } else {
                    codigoCorreto = true;
                    return emailValido;
                }

                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Apenas números!");
                System.out.println("Digite o código novamente: ");
            }
        } while(!codigoCorreto);
        

    return null;
}

}

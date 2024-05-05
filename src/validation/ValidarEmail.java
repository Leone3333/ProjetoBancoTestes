package validation;

import java.util.Scanner;

import entities.Banco;
import services.email.Email;
import services.email.Servidor;

public class ValidarEmail {

     //Solicitar e Validar o E-mail 
     public static Email solicitarEValidarEmail(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){

        boolean emailValido = false;
        Email emailVerificado;
        String emailDigitado;

        do{
            System.out.println("E-mail: ");
            emailDigitado = entrada.nextLine().trim();

            if(emailDigitado.isEmpty()){
                System.out.println("O campo não pode estar vazio");
            }

            //Se o emailDigitado contém @.
            else if(!(emailDigitado.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
                System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
                System.out.println();
            }

             else if(ValidarDadosExistentes.validar(bancoCriado, emailDigitado, entrada)){
                System.out.println("O E-mail " + emailDigitado + " já está cadastrado." );   
                System.out.println();
            }

            else {
                emailVerificado = ValidarEmail.verificacaoDeEmail(entrada, servidorEmail, bancoCriado, emailDigitado);
                if (emailVerificado != null) {
                    return emailVerificado;
                }
            }

        }while(!emailValido);
    
        return null;
    }


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

    public static String atualizarEValidarEmail(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){

        boolean emailValido = false;
        Email emailVerificado;
        String emailDigitado;

        do{ 
            System.out.println("E-mail: ");
            emailDigitado = entrada.nextLine().trim();

            //Se o emailDigitado contém @.
            if(emailDigitado.isEmpty()){
                System.out.println("O campo não pode estar vazio");
            }

            //Se o emailDigitado contém @.
            else if(!(emailDigitado.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
                System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
                System.out.println();
            }

            else if(ValidarDadosExistentes.validar(bancoCriado, emailDigitado, entrada)){
                System.out.println("O E-mail " + emailDigitado + " já está cadastrado." );   
                System.out.println();
            }

            else {
                emailVerificado = ValidarEmail.verificacaoDeEmail(entrada, servidorEmail, bancoCriado, emailDigitado);
                if (emailVerificado != null) {
                     return emailDigitado;
                }
            }

        }while(!emailValido);

        return null;

    }

}

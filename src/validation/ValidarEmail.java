package validation;

import java.util.Scanner;

import entities.Banco;
import services.email.Email;
import services.email.Servidor;

/**
 * A classe ValidarEmail fornece métodos para solicitar, validar e atualizar endereços de e-mail.
 */
public class ValidarEmail {

    /**
     * Solicita e valida um endereço de e-mail.
     * @param entrada O Scanner para entrada do usuário.
     * @param bancoCriado O banco de dados onde serão verificados os e-mails existentes.
     * @param servidorEmail O servidor de e-mail utilizado para enviar e armazenar mensagens.
     * @return O objeto Email verificado ou null se não for possível validar o e-mail.
     */
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
            else if(!(emailDigitado.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
                System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
                System.out.println();
            }
            else if(ValidarDadosExistentes.verificarExistencia(bancoCriado, emailDigitado)){
                System.out.println("O E-mail " + emailDigitado + " já está cadastrado." );   
                System.out.println();
            }
            else {
                Email email = servidorEmail.cadastrarEmail(emailDigitado);
                emailVerificado = ValidarEmail.verificacaoDeEmail(entrada, servidorEmail, bancoCriado, email);
                if (emailVerificado != null) {
                    return emailVerificado;
                }
            }

        } while(!emailValido);
    
        return null;
    }

    /**
     * Realiza a verificação do e-mail através de um código enviado.
     * @param entrada O Scanner para entrada do usuário.
     * @param servidorEmail O servidor de e-mail utilizado para enviar e armazenar mensagens.
     * @param bancoCriado O banco de dados onde serão verificados os e-mails existentes.
     * @param emailDoUsuario O objeto Email do usuário para verificação.
     * @return O objeto Email verificado ou null se a verificação falhar.
     */
    public static Email verificacaoDeEmail(Scanner entrada, Servidor servidorEmail, Banco bancoCriado, Email emailDoUsuario){
        Email emailValido = emailDoUsuario;

        servidorEmail.armazenarMensagem(bancoCriado.enviarCodigo(emailDoUsuario.getEmail()));
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
                else {
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

    /**
     * Atualiza e valida um endereço de e-mail.
     * @param entrada O Scanner para entrada do usuário.
     * @param bancoCriado O banco de dados onde serão verificados os e-mails existentes.
     * @param servidorEmail O servidor de e-mail utilizado para enviar e armazenar mensagens.
     * @return O endereço de e-mail atualizado e validado ou null se não for possível validar o e-mail.
     */
    public static String atualizarEValidarEmail(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){

        boolean emailValido = false;
        Email emailVerificado;
        String emailDigitado;

        do{ 
            System.out.println("E-mail: ");
            emailDigitado = entrada.nextLine().trim();

            if(emailDigitado.isEmpty()){
                System.out.println("O campo não pode estar vazio");
            }
            else if(!(emailDigitado.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
                System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
                System.out.println();
            }
            else if(ValidarDadosExistentes.verificarExistencia(bancoCriado, emailDigitado)){
                System.out.println("O E-mail " + emailDigitado + " já está cadastrado." );   
                System.out.println();
            }
            else {
                Email email = servidorEmail.cadastrarEmail(emailDigitado);
                emailVerificado = ValidarEmail.verificacaoDeEmail(entrada, servidorEmail, bancoCriado, email);
                if (emailVerificado != null) {
                    return emailDigitado;
                }
            }

        } while(!emailValido);

        return null;
    }
}

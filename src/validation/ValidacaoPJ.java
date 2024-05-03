package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import entities.*;
import services.email.Email;
import services.email.Servidor;

public class ValidacaoPJ{

   //Solicitar e Validar o Nome da empresa. 
   public static String solicitarEValidarNome(Scanner entrada){

    boolean nomeValido = false;
    String nomeDigitado;

    do {
        System.out.println("Nome Completo da empresa: ");
        nomeDigitado  = entrada.nextLine().trim();

        //Se o nomeDigitado estiver vazio.
        if(nomeDigitado.isEmpty()){
            System.out.println("O campo não pode estar vazio!");
        }        

        //Se o nomeDigitado segue a regra de ter de a-z e A-Z.
        else if (!(nomeDigitado.matches("^[a-zA-Z\\s]*$"))){
            System.out.println("Digite apenas letras!");
        }

        else{
            nomeValido = true;
        }

    } while(!nomeValido);

    return nomeDigitado;
}

    //Solicitar e Validar o CNPJ. 
    public static String solicitarEValidarCNPJ(Scanner entrada, Banco bancoCriado){

        boolean cnpjValido = false;
        String cnpjDigitado;

        do{
            System.out.println("CNPJ da Empresa: ");
            cnpjDigitado = entrada.nextLine().trim();

            //Se o cpfDigitado estiver vazio.
            if(cnpjDigitado.isEmpty()){
                System.out.println("O campo não pode estar vazio");

            } else if(!cnpjDigitado.matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0-9]{4}[-][0-9]{2}")){ 
                System.out.println("O CNPJ deve ser válido! No formato 00.000.000/0001-00"); 

            }  else if(ValidarDadosExistentes.validar(bancoCriado, cnpjDigitado, entrada)){
                System.out.println("O CNPJ " + cnpjDigitado + " já está cadastrado." );

            } else{
                cnpjValido = true;
            }
            
        } while(!cnpjValido);

        return cnpjDigitado;
    }

    //Solicitar e Validar a data de criação da empresa. 
    public static String solicitarEValidarDataDeCriacao(Scanner entrada){

        boolean dataValida = false;
        String dataDigitada;

        do{
            System.out.println("Data de criação da empresa (dd/mm/yyyy): ");
            dataDigitada = entrada.nextLine().trim(); 

            //Se a dataDigitada estiver vazio.
            if(dataDigitada.isEmpty()){
                System.out.println("O campo não pode estar vazio.");
            }   
            
            try{
                // Formatar a data
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                // Tentar analisar a data
                LocalDate data = LocalDate.parse(dataDigitada, formatar);
            
                // Verificar se o dia e o mês são os mesmos que os digitados
                if (data.getDayOfMonth() != Integer.parseInt(dataDigitada.substring(0, 2)) || 
                    data.getMonthValue() != Integer.parseInt(dataDigitada.substring(3, 5))) {
                    throw new DateTimeParseException("Data inválida", dataDigitada, 0);
                }

                else {
                    dataValida = true;
                }
                
            } catch(DateTimeParseException e){
                // Se a data for inválida, este bloco será executado
                System.out.println("A data deve ser válida! No formato dd/mm/yyyy");
            }

        } while(!dataValida);

        return dataDigitada;
    }
        
    //Solicitar e Validar o E-mail 
    public static Email solicitarEValidarEmail(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){

        boolean emailValido = false;
        Email emailVerificado;
        String emailDigitado;

        do{
            System.out.println("E-mail do Titular: ");
            emailDigitado = entrada.nextLine().trim();

            //Se o emailDigitado contém @.
            if(emailDigitado.isEmpty()){
                System.out.println("O campo não pode estar vazio");
            }

            //Se o emailDigitado contém @.
            else if(!(emailDigitado.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
                System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
            }

             else if(ValidarDadosExistentes.validar(bancoCriado, emailDigitado, entrada)){
                System.out.println("O E-mail " + emailDigitado + " já está cadastrado." );        
            }

            else {
                emailVerificado = ValidacaoEmail.verificacaoDeEmail(entrada, servidorEmail, bancoCriado, emailDigitado);
                if (emailVerificado != null) {
                    return emailVerificado;
                }
            }

        } while(!emailValido);
    
        return null;
    }

    //Solicitar e validar a senha da conta.
    public static String solicitarEValidarSenha(Scanner entrada){

        boolean senhaValida = false;
        String senhaConfirmada = null;

        entrada.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("============== SENHA DA CONTA ============== ");
        System.out.println();
        System.out.println("Obs: A senha deve conter 8 dígitos. Ex:12345678");
        
        do {
            System.out.println("Senha: ");
            String senhaDigitada = entrada.nextLine();

            if(senhaDigitada.isEmpty()){
                System.out.println("O campo não pode estar vazio.");  
                senhaDigitada = null;
            }

            // Verifica se a senha contém apenas números
            else if (!senhaDigitada.matches("\\d+")) {
                System.out.println("A senha deve conter somente números!");
                senhaDigitada = null;
            }

            // Verifica se a senha tem 8 dígitos
            else if (senhaDigitada.length() != 8) {
                System.out.println("A senha deve conter 8 dígitos!");
                senhaDigitada = null;
            }


            if(senhaDigitada != null){  
                // Confirmação da senha
                System.out.println("Confirme a senha:");
                senhaConfirmada = entrada.nextLine();
        
                // Verifica se as senhas são iguais
                if (senhaDigitada.equals(senhaConfirmada)) {
                    senhaValida = true; // Define senhaValida como true para sair do loop

                } else {
                    System.out.println("As senhas não são iguais!");
                }
            }   
        
        } while (!senhaValida);

        return senhaConfirmada; // Retorna a senha confirmada
    }
}


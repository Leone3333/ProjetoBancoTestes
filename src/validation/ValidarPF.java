package validation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import entities.*;

public class ValidarPF{

    //Solicitar e Validar o Nome. 
    public static String solicitarEValidarNome(Scanner entrada){

        boolean nomeValido = false;
        String nomeDigitado;

        do {
            System.out.println("Nome Completo do titular: ");
            nomeDigitado  = entrada.nextLine().trim();

            //Se o nomeDigitado estiver vazio.
            if(nomeDigitado.isEmpty()){
                System.out.println("O campo não pode estar vazio!");
            }        

            //Se o nomeDigitado segue a regra de ter de a-z e A-Z.
            else if (!(nomeDigitado.matches("^[a-zA-Z\\s]*$"))){
                System.out.println("Digite apenas letras!");
                System.out.println();
            }

            else{
                nomeValido = true;
            }

        } while(!nomeValido);

        return nomeDigitado;
    }

    //Solicitar e Validar o CPF. 
    public static String solicitarEValidarCPF(Scanner entrada, Banco bancoCriado){

        boolean cpfValido = false;
        String cpfDigitado;

        do{
            System.out.println("CPF do titular: ");
            cpfDigitado = entrada.nextLine().trim();

            //Se o cpfDigitado estiver vazio.
            if(cpfDigitado.isEmpty()){
                System.out.println("O campo não pode estar vazio");
                
            } else if(!cpfDigitado.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}")){ 
                System.out.println("O CPF deve ser válido! No formato 111.222.333-44"); 
                System.out.println();
                
            } else if(ValidarDadosExistentes.validar(bancoCriado, cpfDigitado, entrada)){
                System.out.println("O CPF " + cpfDigitado + " já está cadastrado." );
                System.out.println();

            } else{
                cpfValido = true;
            }
            
        } while(!cpfValido);

        return cpfDigitado;
    }

    //Solicitar e Validar a data de nascimento. 
    public static String solicitarEValidarDataDeNascimento(Scanner entrada){

        boolean dataValida = false;
        String dataDigitada;

        do{
            System.out.println("Data de nascimento (dd/mm/yyyy): ");
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
            
                LocalDate atual = LocalDate.now();
                Period periodo = Period.between(data, atual);
                
                // Verificar se a pessoa tem menos de 18 anos
                if(periodo.getYears() < 18){
                    System.out.println("Menor de idade não pode abrir uma conta!");
                    System.out.println("Pressione ENTER pra continuar.");
                    entrada.nextLine();
                    System.out.println();
                    return null;
                }

                else{
                    dataValida = true;
                }
                
            } catch(DateTimeParseException e){
                // Se a data for inválida, este bloco será executado
                System.out.println("A data deve ser válida! No formato dd/mm/yyyy");
                System.out.println();
            }

        } while(!dataValida);

        return dataDigitada;
    }
        
   

    
}


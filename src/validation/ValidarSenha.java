package validation;

import java.util.Scanner;

public class ValidarSenha {
    //Solicitar e validar a senha da conta.
    public static String solicitarEValidarSenha(Scanner entrada){

        boolean senhaValida = false;
        String senhaConfirmada = null;

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
                System.out.println();
                senhaDigitada = null;
            }

            // Verifica se a senha tem 8 dígitos
            else if (senhaDigitada.length() != 8) {
                System.out.println("A senha deve conter 8 dígitos!");
                System.out.println();
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
                    System.out.println();
                }
            }   
        
        } while (!senhaValida);

        return senhaConfirmada; // Retorna a senha confirmada
    }
}

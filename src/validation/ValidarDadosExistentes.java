package validation;

import java.util.Scanner;

import entities.*;

public class ValidarDadosExistentes {
    public static boolean validar(Banco bancoCriado, String dadoDigitado, Scanner entrada){

        //Verificar se o CPF, CNPJ ou email já existe.
        for(Conta conta : bancoCriado.getContasNoBanco()){
            if(conta.getIdentificacao().equals(dadoDigitado) && conta instanceof ContaPF){
                return true;

            } else if(conta.getIdentificacao().equals(dadoDigitado) && conta instanceof ContaPJ){
                return true;
                
            } else if(conta.getEnderecoEmail().equals(dadoDigitado)){
                return true;
            }
        } 
        return false;
    }        
}

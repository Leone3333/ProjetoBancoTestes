package validation;


import entities.*;

public class ValidarDadosExistentes {
    public static boolean validar(Banco bancoCriado, String dadoDigitado){

        //Verificar se o CPF, CNPJ ou email já existe.
        for(Conta conta : bancoCriado.getContasNoBanco()){
            if(conta.getIdentificacao().equals(dadoDigitado)){
                return true;

            } else if(conta.getEnderecoEmail().equals(dadoDigitado)){
                return true;
            }
        } 
        return false;
    }        
}

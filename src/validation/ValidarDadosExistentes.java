package validation;

import entities.Banco;
import entities.Conta;

public class ValidarDadosExistentes {

    /**
     * Verifica se um dado existe no banco.
     * @param banco o banco a ser verificado
     * @param dadoDigitado o dado a ser verificado
     * @return true se o dado existir no banco, false caso contrário
     */
    public static boolean verificarExistencia(Banco banco, String dadoDigitado) {
        if (verificarEmailAdmin(banco, dadoDigitado) || verificarContas(banco, dadoDigitado)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o email do administrador existe no banco.
     * @param banco o banco a ser verificado
     * @param email o email a ser verificado
     * @return true se o email existir no banco, false caso contrário
     */
    private static boolean verificarEmailAdmin(Banco banco, String email) {
        return banco.getEmailDoBanco().equals(email) || banco.getEnderecoADM().equals(email);
    }

    /**
     * Verifica se as contas existem no banco.
     * @param banco o banco a ser verificado
     * @param dadoDigitado o dado a ser verificado
     * @return true se a conta existir no banco, false caso contrário
     */
    private static boolean verificarContas(Banco banco, String dadoDigitado) {
        for (Conta conta : banco.getContasNoBanco()) {
            if (conta.getIdentificacao().equals(dadoDigitado) || conta.getEnderecoEmail().equals(dadoDigitado)) {
                return true;
            }
        }
        return false;
    }
}

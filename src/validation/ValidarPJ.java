
package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import entities.*;

/**
 * Classe ValidarPJ
 * Esta classe contém métodos para solicitar e validar informações de pessoa jurídica (empresa).
 */
public class ValidarPJ {

    /**
     * Solicita e valida o nome da empresa.
     * @param entrada o Scanner para entrada de dados do usuário
     * @return o nome da empresa validado
     */
    public static String solicitarEValidarNome(Scanner entrada) {
        boolean nomeValido = false;
        String nomeDigitado;

        do {
            System.out.println("Nome Completo da empresa: ");
            nomeDigitado = entrada.nextLine().trim();

            // Se o nomeDigitado estiver vazio
            if (nomeDigitado.isEmpty()) {
                System.out.println("O campo não pode estar vazio!");
            }
            // Se o nomeDigitado segue a regra de ter de a-z e A-Z
            else if (!nomeDigitado.matches("^[a-zA-Z'~çÇáàâãéèêíïóôõöúçüñ\\s]*$")) {
                System.out.println("Digite apenas letras!");
                System.out.println();
            } else {
                nomeValido = true;
            }

        } while (!nomeValido);

        return nomeDigitado;
    }

    /**
     * Solicita e valida o CNPJ da empresa.
     * @param entrada o Scanner para entrada de dados do usuário
     * @param bancoCriado o objeto Banco para verificar duplicidade de CNPJ
     * @return o CNPJ validado
     */
    public static String solicitarEValidarCNPJ(Scanner entrada, Banco bancoCriado) {
        boolean cnpjValido = false;
        String cnpjDigitado;

        do {
            System.out.println("CNPJ da Empresa: ");
            cnpjDigitado = entrada.nextLine().trim();

            // Se o cnpjDigitado estiver vazio
            if (cnpjDigitado.isEmpty()) {
                System.out.println("O campo não pode estar vazio");
            }
            // Verifica se o CNPJ está no formato correto
            else if (!cnpjDigitado.matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0-9]{4}[-][0-9]{2}")) {
                System.out.println("O CNPJ deve ser válido! No formato 00.000.000/0001-00");
                System.out.println();
            }
            // Verifica se o CNPJ já está cadastrado
            else if (ValidarDadosExistentes.verificarExistencia(bancoCriado, cnpjDigitado)) {
                System.out.println("O CNPJ " + cnpjDigitado + " já está cadastrado.");
                System.out.println();
            } else {
                cnpjValido = true;
            }

        } while (!cnpjValido);

        return cnpjDigitado;
    }

    /**
     * Solicita e valida a data de criação da empresa.
     * @param entrada o Scanner para entrada de dados do usuário
     * @return a data de criação da empresa validada
     */
    public static String solicitarEValidarDataDeCriacao(Scanner entrada) {
        boolean dataValida = false;
        String dataDigitada;

        do {
            System.out.println("Data de criação da empresa (dd/mm/yyyy): ");
            dataDigitada = entrada.nextLine().trim();

            // Se a dataDigitada estiver vazia
            if (dataDigitada.isEmpty()) {
                System.out.println("O campo não pode estar vazio.");
            }

            try {
                // Formatar a data
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                // Tentar analisar a data
                LocalDate data = LocalDate.parse(dataDigitada, formatar);

                // Verificar se o dia e o mês são os mesmos que os digitados
                if (data.getDayOfMonth() != Integer.parseInt(dataDigitada.substring(0, 2)) ||
                    data.getMonthValue() != Integer.parseInt(dataDigitada.substring(3, 5))) {
                    throw new DateTimeParseException("Data inválida", dataDigitada, 0);
                } else {
                    dataValida = true;
                }

            } catch (DateTimeParseException e) {
                // Se a data for inválida, este bloco será executado
                System.out.println("A data deve ser válida! No formato dd/mm/yyyy");
                System.out.println();
            }

        } while (!dataValida);

        return dataDigitada;
    }
}

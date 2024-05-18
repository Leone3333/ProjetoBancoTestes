/**
 * Classe de validação para Pessoa Física.
 * Esta classe contém métodos para solicitar e validar o nome, CPF e data de nascimento de uma pessoa física.
 */
package validation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import entities.Banco;

public class ValidarPF {

    /**
     * Método para solicitar e validar o nome completo do titular.
     * @param entrada Scanner para entrada de dados pelo usuário.
     * @return O nome completo válido digitado pelo usuário.
     */
    public static String solicitarEValidarNome(Scanner entrada) {
        boolean nomeValido = false;
        String nomeDigitado;

        do {
            System.out.println("Nome Completo do titular: ");
            nomeDigitado = entrada.nextLine().trim();

            // Verifica se o nome está vazio.
            if (nomeDigitado.isEmpty()) {
                System.out.println("O campo não pode estar vazio!");
            } else if (nomeDigitado.matches("[0-9][@]")) { // Verifica se o nome contém apenas letras.
                System.out.println("Digite apenas letras!");
                System.out.println();
            } else {
                nomeValido = true;
            }
        } while (!nomeValido);

        return nomeDigitado;
    }

    /**
     * Método para solicitar e validar o CPF do titular.
     * @param entrada Scanner para entrada de dados pelo usuário.
     * @param bancoCriado Objeto Banco para verificar se o CPF já está cadastrado.
     * @return O CPF válido digitado pelo usuário.
     */
    public static String solicitarEValidarCPF(Scanner entrada, Banco bancoCriado) {
        boolean cpfValido = false;
        String cpfDigitado;

        do {
            System.out.println("CPF do titular: ");
            cpfDigitado = entrada.nextLine().trim();

            // Verifica se o CPF está vazio.
            if (cpfDigitado.isEmpty()) {
                System.out.println("O campo não pode estar vazio");
            } else if (!cpfDigitado.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}")) { // Verifica o formato do CPF.
                System.out.println("O CPF deve ser válido! No formato 111.222.333-44");
                System.out.println();
            } else if (ValidarDadosExistentes.verificarExistencia(bancoCriado, cpfDigitado)) { // Verifica se o CPF já está cadastrado.
                System.out.println("O CPF " + cpfDigitado + " já está cadastrado.");
                System.out.println();
            } else {
                cpfValido = true;
            }

        } while (!cpfValido);

        return cpfDigitado;
    }

    /**
     * Método para solicitar e validar a data de nascimento do titular.
     * @param entrada Scanner para entrada de dados pelo usuário.
     * @return A data de nascimento válida digitada pelo usuário.
     */
    public static String solicitarEValidarDataDeNascimento(Scanner entrada) {
        boolean dataValida = false;
        String dataDigitada;

        do {
            System.out.println("Data de nascimento (dd/mm/yyyy): ");
            dataDigitada = entrada.nextLine().trim();

            // Verifica se a data está vazia.
            if (dataDigitada.isEmpty()) {
                System.out.println("O campo não pode estar vazio.");
            }

            try {
                // Formata a data.
                DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                // Tenta analisar a data.
                LocalDate data = LocalDate.parse(dataDigitada, formatar);

                // Verifica se o dia e o mês são os mesmos que os digitados.
                if (data.getDayOfMonth() != Integer.parseInt(dataDigitada.substring(0, 2)) ||
                        data.getMonthValue() != Integer.parseInt(dataDigitada.substring(3, 5))) {
                    throw new DateTimeParseException("Data inválida", dataDigitada, 0);
                }

                LocalDate atual = LocalDate.now();
                Period periodo = Period.between(data, atual);

                // Verifica se a pessoa tem menos de 18 anos.
                if (periodo.getYears() < 18) {
                    System.out.println("Menor de idade não pode abrir uma conta!");
                    System.out.println("Pressione ENTER pra continuar.");
                    entrada.nextLine();
                    System.out.println();
                    return null;
                } else {
                    dataValida = true;
                }

            } catch (DateTimeParseException e) {
                // Se a data for inválida, este bloco será executado.
                System.out.println("A data deve ser válida! No formato dd/mm/yyyy");
                System.out.println();
            }

        } while (!dataValida);

        return dataDigitada;
    }

}

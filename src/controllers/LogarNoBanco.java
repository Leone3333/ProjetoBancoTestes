package controllers;

import java.util.Scanner;

import entities.Banco;
import services.email.Servidor;
import entities.enums.TipoDeDado;

public class LogarNoBanco {

    public static void logarNoBanco(Scanner entrada, Servidor servidorEmail, Banco bancoCriado){

        // Constantes para as opções do menu após o login
        final int DEPOSITAR = 1;
        final int SACAR = 2;
        final int TRANSFERIR = 3;
        final int EXTRATO = 4;
        final int ATUALIZARDADOS = 5;
        final int EXCLUIRCONTA = 6;
        final int SAIR = 7;

        // Flags para verificar se o e-mail e a senha são válidos
        boolean emailValido = false;
        boolean senhaValida = false;

        String emailParaLogar;
        String senhaParaLogar;

        entrada.nextLine();
        System.out.println("=============== FAZER LOGIN  ===============");

        // Validação do e-mail
        do {
            System.out.println("E-mail: ");
            emailParaLogar = entrada.nextLine();

            if (emailParaLogar.isEmpty()) {
                System.out.println("O campo não pode estar vazio!");
            } else if (!(emailParaLogar.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))) {
                System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
                System.out.println();
            } else {
                emailValido = true;
            }
        } while (!emailValido);

        // Validação da senha
        do {
            System.out.println("Senha: ");
            senhaParaLogar = entrada.nextLine();

            if (senhaParaLogar.isEmpty()) {
                System.out.println("O campo não pode estar vazio!");
            } else if (!senhaParaLogar.matches("\\d+")) {
                System.out.println("A senha deve conter somente números!");
                System.out.println();
            } else if (senhaParaLogar.length() != 8) {
                System.out.println("A senha deve conter 8 dígitos!");
                System.out.println();
            } else {
                senhaValida = true;
                System.out.println("============================================");
            }
        } while (!senhaValida);

        // Verificação do usuário no banco de dados
        Integer indexDoUsuario = null;
        boolean usuarioEncontrado = false;


        for (int i = 0; i < bancoCriado.getContasNoBanco().size(); i++) {

            if (bancoCriado.getEnderecoADM().equals(emailParaLogar) && bancoCriado.getSenhaADM().equals(senhaParaLogar))
            {
                AdministradorBanco.administradorBanco(entrada, bancoCriado, servidorEmail);
                break;
            }


            else if (bancoCriado.getDados(i, TipoDeDado.ENDERECODEEMAIL).equals(emailParaLogar) && bancoCriado.getDados(i, TipoDeDado.SENHA).equals(senhaParaLogar)) 
            {
                usuarioEncontrado = true;
                indexDoUsuario = i;
                break;
            }
        }

        if (usuarioEncontrado) {
            System.out.print("\033[H\033[2J");
            System.out.println("Seja Bem-Vindo(a) " + bancoCriado.getDados(indexDoUsuario, TipoDeDado.NOME));

            // Menu de operações após o login
            boolean running = true;

            do {
                System.out.println("=========== Selecione a opção desejada: ===========");
                System.out.printf("[%d] Depositar%n", DEPOSITAR);
                System.out.printf("[%d] Sacar%n", SACAR);
                System.out.printf("[%d] Transferir%n", TRANSFERIR);
                System.out.printf("[%d] Extrato%n", EXTRATO);
                System.out.printf("[%d] Atualizar Dados%n", ATUALIZARDADOS);
                System.out.printf("[%d] Deletar Conta%n", EXCLUIRCONTA);
                System.out.printf("[%d] Sair%n", SAIR);
                System.out.println("===================================================");

                System.out.println();
                System.out.println("Digite uma opção: ");
                int opcaoDigitada = entrada.nextInt();

                switch (opcaoDigitada) {
                    case DEPOSITAR:
                        // Chama o método para depositar
                        Operacoes.depositar(entrada, bancoCriado, indexDoUsuario);
                        break;

                    case SACAR:
                        // Chama o método para sacar
                        Operacoes.sacar(entrada, bancoCriado, indexDoUsuario);
                        break;

                    case TRANSFERIR:
                        Operacoes.transferir(entrada, bancoCriado, indexDoUsuario);
                        break;
                    
                    case EXTRATO:
                        // Chama o método para exibir o extrato
                        // Implemente a funcionalidade do extrato aqui
                        break;

                    case ATUALIZARDADOS:
                        // Chama o método para atualizar dados da conta
                        AtualizarDadosConta.atualizarDadosConta(entrada, bancoCriado, indexDoUsuario, servidorEmail);
                        break;

                    case EXCLUIRCONTA:
                        // Chama o método para excluir conta
                        DeletarConta.deletarConta(entrada, bancoCriado, indexDoUsuario, servidorEmail);
                        running = false;
                        break;

                    case SAIR:
                        // Sai do programa
                        running = false;
                        break;

                    default:
                        System.out.println("Digite uma opção válida!");
                        break;
                }

            } while (running);
        } else {
            // Exibe mensagem se o usuário não for encontrado
            System.out.println("Dados inválidos! Verifique o e-mail ou a senha!");
        }
    }
}
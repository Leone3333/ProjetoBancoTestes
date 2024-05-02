package controllers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import entities.*;
import services.email.*;

public class CadastrarNoBanco {
    public static void cadastrarNoBanco(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){

    final int CONTAPF = 1;
    final int CONTAPJ = 2;
    final int VOLTAR = 3;

    boolean running = true;

    do{
       
        try{ 
        System.out.println("=========== Cadastrar Conta =========");
        System.out.printf("[%d] Conta Pessoa Física %n", CONTAPF);
        System.out.printf("[%d] Conta Pessoa Jurídica %n", CONTAPJ);
        System.out.printf("[%d] Voltar %n", VOLTAR);
        System.out.println("=====================================");
        System.out.println();


        System.out.println("Digite uma opção: ");
        int opcao = entrada.nextInt();

        //Se o cadastro for feito, ou desejar sair, o valor muda para true, encerrando o loop.
        boolean cadastroFeito = false;
        
        switch (opcao) {

            case CONTAPF:
                while(!cadastroFeito){
                    cadastroFeito = cadastrarPF(entrada, bancoCriado, servidorEmail);}

                running = false;
                break;

            case CONTAPJ:
                while(!cadastroFeito){
                    cadastroFeito = cadastrarPJ(entrada, bancoCriado, servidorEmail);}

                running = false;
                break;

            case VOLTAR:
                running = false;
                System.out.print("\033[H\033[2J");
                break;

            default:
                //Exibir mensagem se a opção for válida.
                System.out.print("\033[H\033[2J");
                System.out.println("Digite alguma das opções abaixo: ");
                break;
        }
            } catch(InputMismatchException e){

                System.out.print("\033[H\033[2J");
                System.out.println("============ ATENÇÃO! ===========");
                System.out.println("      Digite apenas números!     ");
                System.out.println("=================================");
                System.out.println("Pressione ENTER pra continuar.");
                entrada.nextLine();
                entrada.nextLine();

            }
        } while(running);
    } 

    //Função para criar uma conta Pessoa Física.
    public static boolean cadastrarPF(Scanner entrada, Banco bancoCriado, Servidor servidorEmail){
        
        entrada.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("========= CADASTRAR CONTA PESSOA FÍSICA =========");

        System.out.println("Nome Completo do titular: ");
        String nomeDigitado = entrada.nextLine().trim();

        //Se o nomeDigitado estiver vazio.
        if(nomeDigitado.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }        

        //Se o nomeDigitado segue a regra de ter de a-z e A-Z.
        else if (!(nomeDigitado.matches("^[a-zA-Z\\s]*$"))){
            System.out.println("Digite apenas letras!");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }

        
        System.out.println("CPF do titular: ");
        String cpfDigitado = entrada.nextLine().trim();

        //Validação do CPF

        //Se o cpfDigitado estiver vazio.
        if(cpfDigitado.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
                return false;

        } else if(!cpfDigitado.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}")){ 
            System.out.println("O CPF deve ser válido! No formato 111.222.333-44"); 
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;

        } else if(dadosExistem(bancoCriado, cpfDigitado, entrada)){
            if(campoVazioOuInvalido(entrada)){
                return true;
    
            }            
                return false;
        }



        System.out.println("Data de nascimento dd/mm/yyyy: ");
        String dataDigitada = entrada.next().trim(); 

        //Validação da Data de Nascimento

        //Se a dataDigitada estiver vazio.
        if(dataDigitada.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;

        }   
        
        try{
            // Formatar a data
            DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy").withResolverStyle(ResolverStyle.SMART);
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
                campoVazioOuInvalido(entrada);
                return false;
            }
            
        } catch(DateTimeParseException e){
            // Se a data for inválida, este bloco será executado
            System.out.println("A data de nascimento deve ser válida! No formato dd/mm/yyyy");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }
        
                 
        System.out.println("E-mail do Titular: ");
        String emailDigitado = entrada.next().trim();

        //Se o emailDigitado contém @.
        if(emailDigitado.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
                return false;
    
        }
        
        //Se o emailDigitado contém @.
        else if(!(emailDigitado.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
            System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }

         else if(dadosExistem(bancoCriado, emailDigitado, entrada)){
            if(campoVazioOuInvalido(entrada)){
                return true;

            }            
                return false;
        }

        //Criar e verificar o e-mail
        Email emailCriado = verificacaoDeEmail(entrada, servidorEmail, bancoCriado, emailDigitado);

        // Guardar a senha criada através da função na variável.
        String senhaConfirmada = cadastrarSenha(entrada);

        // Instância da Conta Pessoa Física.
        Conta contaCriada = new ContaPF(senhaConfirmada, cpfDigitado, nomeDigitado, dataDigitada, emailCriado);
           
        bancoCriado.cadastrarConta(contaCriada);

        //Mostrar os dados da conta criada;
        System.out.print("\033[H\033[2J");
        System.out.println("============= Dados da Conta =============" );
        System.out.println(bancoCriado.exibirDados(contaCriada.getNumeroDaConta()));
        System.out.println("==========================================" );

        System.out.println();
        System.out.println("Pressione ENTER para continuar");
        entrada.nextLine();
        System.out.print("\033[H\033[2J");

        return true;

    }

    //Função para criar uma conta Pessoa Jurídica.
    public static boolean cadastrarPJ(Scanner entrada, Banco bancoCriado, Servidor servidorEmail) {

    entrada.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.println("========= CADASTRAR CONTA PESSOA JURÍDICA =========");

    System.out.println("Nome da Empresa: ");
        String nomeDigitado = entrada.nextLine().trim();

        //Se o nomeDigitado estiver vazio.
        if(nomeDigitado.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }        

        //Se o nomeDigitado segue a regra de ter de a-z e A-Z.
        else if (!(nomeDigitado.matches("^[a-zA-Z\\s]*$"))){
            System.out.println("Digite apenas letras!");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }        

        System.out.println("CNPJ da Empresa: ");
        String cnpjDigitado = entrada.nextLine().trim();

        if(cnpjDigitado.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
        }
                return false;

    } else if(!cnpjDigitado.matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[/][0-9]{4}[-][0-9]{2}")){ 
        System.out.println("O CNPJ deve ser válido! No formato 00.000.000/0001-00"); 
        if(campoVazioOuInvalido(entrada)){
            return true;
        }
        return false;

    }   else if(dadosExistem(bancoCriado, cnpjDigitado, entrada)){
        if(campoVazioOuInvalido(entrada)){
            return true;

        }            
            return false;
    }


    System.out.println("Data de criação dd/mm/yyyy: ");
        String dataDigitada = entrada.next().trim(); 

        //Se a data de criação estiver vazio.
        if(dataDigitada.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;

        }   

        try{
            // Formatar a data
            DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy").withResolverStyle(ResolverStyle.SMART);

            // Tentar analisar a data
            LocalDate data = LocalDate.parse(dataDigitada, formatar);
            
            // Verificar se o dia e o mês são os mesmos que os digitados
            if (data.getDayOfMonth() != Integer.parseInt(dataDigitada.substring(0, 2)) || 
                data.getMonthValue() != Integer.parseInt(dataDigitada.substring(3, 5))) {
                throw new DateTimeParseException("Data inválida", dataDigitada, 0);
            }
              
        } catch(DateTimeParseException e){
            // Se a data for inválida, este bloco será executado
            System.out.println("A data de criação deve ser válida! No formato dd/mm/yyyy");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }

        System.out.println("E-mail da Empresa: ");
        String emailDigitado = entrada.next().trim();

        if(emailDigitado.isEmpty()){
            System.out.println("O campo não pode estar vazio");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
                return false;
    
        }

        //Se o emailDigitado contém @.
        else if(!(emailDigitado.matches("^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))){
            System.out.println("Digite um e-mail válido! Ex: nome@seudominio.com.br");
            if(campoVazioOuInvalido(entrada)){
                return true;
            }
            return false;
        }

         else if(dadosExistem(bancoCriado, emailDigitado, entrada)){
            if(campoVazioOuInvalido(entrada)){
                return true;

            }            
                return false;
        }

        //Criar e verificar o e-mail
        Email emailCriado = verificacaoDeEmail(entrada, servidorEmail, bancoCriado, emailDigitado);
        

        // Guardar a senha criada através da função na variável.
        String senhaConfirmada = cadastrarSenha(entrada);

        // Instância da Conta Pessoa Física.
        Conta contaCriada = new ContaPJ(senhaConfirmada, nomeDigitado, cnpjDigitado, dataDigitada, emailCriado);
        bancoCriado.cadastrarConta(contaCriada);

        //Exibir dados da conta e enviar os dados para o e-mail
        exibirDados(entrada, bancoCriado, contaCriada, emailCriado, servidorEmail);
        return true;
}


    public static String cadastrarSenha(Scanner entrada) {

            boolean senhaValida = false;
            String senhaConfirmada = null;
        
            while (!senhaValida) {

                System.out.print("\033[H\033[2J");
                System.out.println("============== SENHA DA CONTA ============== ");
                System.out.println();
                System.out.println("Obs: A senha deve conter 8 dígitos. Ex:12345678");
                System.out.println("Senha: ");
                String senhaDigitada = entrada.nextLine();

                if(senhaDigitada.isEmpty()){
                    senhaDigitada = null;
                    campoVazioOuInvalido(entrada);
                    continue;
                }

                // Verifica se a senha contém apenas números
                else if (!senhaDigitada.matches("\\d+")) {
                    senhaDigitada = null;
                    System.out.println("A senha deve conter somente números!");
                    System.out.println("=============================================");
                    System.out.println("Pressione ENTER para continuar");
                    continue;
                }

                // Verifica se a senha tem 8 dígitos
                else if (senhaDigitada.length() != 8 && senhaDigitada != null) {
                    senhaDigitada = null;
                    System.out.println("A senha deve conter 8 dígitos!");
                    System.out.println("=============================================");
                    System.out.println("Pressione ENTER para continuar");
                    continue;
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
                        System.out.println("Pressione ENTER para continuar");
                        System.out.println("=============================================");
                    }
            }   }
        
            return senhaConfirmada; // Retorna a senha confirmada
        }
        
        
        //Exibir mensagem se o campo estiver vazio ou inválido.
        public static boolean campoVazioOuInvalido(Scanner entrada){
            
            System.out.println("=================================================");
            System.out.println();
            System.out.println("Deseja sair do cadastramento? [S]im ou [N]ão");
            String opcao = entrada.next().toLowerCase().substring(0);

            if(opcao.equals("s")){
                System.out.print("\033[H\033[2J");
                return true;
            }
            
            return false;

        }

    public static boolean dadosExistem(Banco bancoCriado, String dadoDigitado, Scanner entrada){

        //Verificar se o CPF, CNPJ ou email já existe.
        for(Conta conta : bancoCriado.getContasNoBanco()){
            if(conta.getIdentificacao().equals(dadoDigitado) && conta instanceof ContaPF){
                System.out.println("O CPF " + dadoDigitado + " já está cadastrado");
                return true;


            } else if(conta.getIdentificacao().equals(dadoDigitado) && conta instanceof ContaPJ){
                System.out.println("O CNPJ " + dadoDigitado + " já está cadastrado");
                return true;
                


            } else if(conta.getEnderecoEmail().equals(dadoDigitado)){
                System.out.println("O e-mail " + dadoDigitado + " já está cadastrado");
                return true;


            }
        } 
        return false;
    }        


    public static Email verificacaoDeEmail(Scanner entrada, Servidor servidorEmail, Banco bancoCriado, String emailDigitado){
     

        // Instância do Email.
        Email emailValido = servidorEmail.cadastrarEmail(emailDigitado);

        //Enviar um código de verificação
        servidorEmail.enviarMensagem(bancoCriado.enviarCodigo(emailDigitado));
        emailValido.exibirEmailsRecebidos();
    
        System.out.println();
        System.out.println("========== Verificação ==========");
        System.out.println("Verifique o código enviado para o seu e-mail");
        System.out.println("Digite o código: ");
        entrada.nextLine();
        String codigoDigitado = entrada.nextLine();

        boolean codigoCorreto = false;

        do{
            try {

                Integer codigoValido = Integer.parseInt(codigoDigitado);
        
                if(!(bancoCriado.verificarCodigo(codigoValido))){
                    System.out.println();
                    System.out.println("Código de verificação incorreto!");
                    System.out.println("Digite o código novamente: ");
                    codigoDigitado = entrada.nextLine();
                }

                else{
                    codigoCorreto = true;
                    return emailValido;
                }
       

            } catch (NumberFormatException e) {
                System.out.print("\033[H\033[2J");
                System.out.println("============ ATENÇÃO! ===========");
                System.out.println("      Digite apenas números!     ");
                System.out.println("=================================");
                System.out.println("Pressione ENTER pra continuar.");
                entrada.nextLine();
                System.out.println("Digite o código novamente: ");
                codigoDigitado = entrada.nextLine();
            }
            
        } while(!codigoCorreto);

    return null;
}



    public static void exibirDados(Scanner entrada, Banco bancoCriado, Conta contaCriada, Email emailValido, Servidor servidorEmail){

    //Enviar um código de verificação
    servidorEmail.enviarMensagem(bancoCriado.enviarCodigo(emailValido.getEmail()));
    emailValido.exibirEmailsRecebidos();

    System.out.print("\033[H\033[2J");
    System.out.println("============= Dados da Conta =============" );
    System.out.println(bancoCriado.exibirDados(contaCriada.getNumeroDaConta()));
    System.out.println("==========================================" );
    System.out.println("Enviamos os dados da sua conta para o seu e-mail.");
    System.out.println();
    System.out.println("Pressione ENTER para continuar");
    entrada.nextLine();
    System.out.print("\033[H\033[2J");
    }
}

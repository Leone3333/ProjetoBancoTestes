package services.email;

import java.io.File;
import java.util.ArrayList;

/**
 * Classe Servidor
 * Esta classe representa um servidor de email que gerencia o cadastro de emails e o armazenamento de mensagens.
 */
public class Servidor {

    // Lista para armazenar os emails cadastrados no servidor
    private ArrayList<Email> emailsCadastrados;

    /**
     * Construtor da classe Servidor
     * Inicializa a lista de emails cadastrados.
     */
    public Servidor() {
        emailsCadastrados = new ArrayList<>();
    }

    /**
     * Método para cadastrar um novo email no servidor
     * @param email String com o endereço de email a ser cadastrado
     * @return o objeto Email que foi cadastrado
     */
    public Email cadastrarEmail(String email) {
        Email novoEmail = new Email(email);
        emailsCadastrados.add(novoEmail);
        return novoEmail;
    }

/**
 * Método para armazenar uma mensagem para um destinatário
 * @param mensagem a mensagem a ser armazenada
 */
public void armazenarMensagem(Mensagem mensagem) {
    // Guardar o destinatário da mensagem
    String destinatario = mensagem.getDestinatario();

    // Percorrer a lista de emails cadastrados
    boolean encontrado = false;
    for (Email email : emailsCadastrados) {
        // Verificar se o destinatário existe no servidor
        if (email.getEmail().equals(destinatario)) {
            // Se existir, adicionar a mensagem na caixa de entrada do destinatário
            email.adicionarMensagem(mensagem);
            encontrado = true;
            break;
        }
    }
    // Se não encontrado, registrar uma mensagem de erro
    if (!encontrado) {
        System.err.println("Erro ao armazenar mensagem: destinatário não encontrado - " + destinatario);
    }
}

    /**
     * Método para obter a lista de emails cadastrados
     * @return a lista de emails cadastrados
     */
    public ArrayList<Email> getEmailsCadastrados() {
        return this.emailsCadastrados;
    }


      public void limparPastaDeEmails() {
        // Diretório da pasta de e-mails
        String diretorio = "servidorDeEmail\\";

        // Criando o objeto File para representar o diretório
        File pastaEmails = new File(diretorio);

        // Verificando se o diretório existe
        if (pastaEmails.exists() && pastaEmails.isDirectory()) {
            // Listando os arquivos na pasta de e-mails
            File[] arquivos = pastaEmails.listFiles();

            // Verificando se há arquivos na pasta
            if (arquivos != null) {
                // Iterando sobre os arquivos
                for (File arquivo : arquivos) {
                    // Verificando se o arquivo não é o "CaixaDeEntrada"
                    if (!arquivo.getName().equals("CaixaDeEntrada")) {
                        // Deletando o arquivo
                        arquivo.delete();
                    }
                }
            }
        }
    }

    public void criarPasta(){
        
        // Caminho da pasta a ser criada
        String nomePasta = "servidorDeEmail";

        // Criação do objeto File com o caminho da pasta
        File pasta = new File(nomePasta);

        // Verifica se a pasta já existe
        if (!pasta.exists()) {
            // Cria a pasta
            if (pasta.mkdir()) {
                System.out.println("Pasta criada com sucesso!");
            } else {
                System.out.println("Falha ao criar a pasta.");
            }
        } else {
            System.out.println("A pasta já existe.");
        }
    }
}



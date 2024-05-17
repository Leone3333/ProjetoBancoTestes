package services.email;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe Email
 * Esta classe representa um endereço de email com funcionalidades para gerenciar mensagens recebidas.
 */
public class Email {

    private String email;
    // Cada email irá ter sua "caixa" de mensagens
    private ArrayList<Mensagem> mensagensRecebidas;

    /**
     * Construtor da classe Email
     * @param email o endereço de email
     */
    public Email(String email) {
        this.email = email;
        this.mensagensRecebidas = new ArrayList<>();
    }

    /**
     * Obtém as mensagens recebidas.
     * @return a lista de mensagens recebidas
     */
    public ArrayList<Mensagem> getMensagensRecebidas() {
        return this.mensagensRecebidas;
    }

    /**
     * Obtém o endereço de email.
     * @return o endereço de email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Define o endereço de email.
     * @param email o novo endereço de email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Adiciona uma mensagem recebida.
     * @param mensagem a mensagem a ser adicionada
     */
    public void adicionarMensagem(Mensagem mensagem) {
        this.mensagensRecebidas.add(mensagem);
    }

    /**
     * Deleta uma mensagem recebida pelo índice.
     * @param indexMensagem o índice da mensagem a ser deletada
     */
    public void deletarMensagem(int indexMensagem) {
        this.mensagensRecebidas.remove(indexMensagem);
    }

    /**
     * Exclui todas as mensagens da caixa de entrada.
     */
    public void limparCaixaDeEntrada() {
        this.mensagensRecebidas.clear();
    }

    /**
     * Exibe e salva as mensagens recebidas em um arquivo de texto.
     */
    public void exibirEmailsRecebidos() {
        String caminhoDoArquivo = "servidorDeEmail\\" + "caixaDeEntrada-" + getEmail() + ".txt";

        try (BufferedWriter arquivo = new BufferedWriter(new FileWriter(caminhoDoArquivo))) {
            for (Mensagem mensagem : mensagensRecebidas) {
                arquivo.write(mensagem.exibirMensagem());
                arquivo.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo!");
            e.printStackTrace(); //Imprimir rastreamento de pilha para diagnóstico completo
        }
    }

    /**
     * Obtém o número de mensagens na caixa de entrada.
     * @return o número de mensagens na caixa de entrada
     */
    public int getNumeroDeMensagens() {
        return this.mensagensRecebidas.size();
    }

    /**
     * Verifica se a caixa de entrada está vazia.
     * @return true se a caixa de entrada estiver vazia, false caso contrário
     */
    public boolean isCaixaDeEntradaVazia() {
        return this.mensagensRecebidas.isEmpty();
    }
}

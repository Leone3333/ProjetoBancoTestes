package services.email;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe Mensagem
 * Esta classe representa uma mensagem de email com remetente, destinatário, conteúdo da mensagem,
 * data e hora de envio.
 */
public class Mensagem {

    private String remetente;
    private String destinatario;
    private String conteudo;
    private LocalDate dataEnvio;
    private LocalTime horaEnvio;

    /**
     * Construtor da classe Mensagem
     * @param remetente o endereço de email do remetente
     * @param destinatario o endereço de email do destinatário
     * @param conteudo o conteúdo da mensagem
     * @param dataEnvio a data de envio da mensagem
     * @param horaEnvio a hora de envio da mensagem
     */
    public Mensagem(String remetente, String destinatario, String conteudo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
    }

    /**
     * Obtém o destinatário da mensagem.
     * @return o endereço de email do destinatário
     */
    public String getDestinatario() {
        return this.destinatario;
    }

    /**
     * Obtém o remetente da mensagem.
     * @return o endereço de email do remetente
     */
    public String getRemetente() {
        return this.remetente;
    }

    /**
     * Obtém o conteúdo da mensagem.
     * @return o conteúdo da mensagem
     */
    public String getConteudo() {
        return this.conteudo;
    }

    /**
     * Obtém a data de envio da mensagem.
     * @return a data de envio da mensagem
     */
    public LocalDate getDataEnvio() {
        return this.dataEnvio;
    }

    /**
     * Obtém a hora de envio da mensagem.
     * @return a hora de envio da mensagem
     */
    public LocalTime getHoraEnvio() {
        return this.horaEnvio;
    }

    /**
     * Exibe os detalhes da mensagem.
     * @return uma string com os detalhes da mensagem formatados
     */
    public String exibirMensagem() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        return "==============================================" + "\n"
                + "Enviado em " + this.dataEnvio.format(formatoData) + " às "
                + this.horaEnvio.format(formatoHora) + "\n"
                + "Remetente: " + this.remetente + "\n" + "\n"
                + this.conteudo + "\n";
    }

}

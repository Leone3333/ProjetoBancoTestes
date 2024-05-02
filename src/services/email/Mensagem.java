package services.email;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Mensagem {

    private String remetente;
    private String destinatario;
    private String mensagem;

    // Construtor da classe Mensagem
    public Mensagem(String remetente, String destinatario, String mensagem) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }

    public String getDestinatario(){
        return this.destinatario;
    }

    public String getRemetente(){
        return this.remetente;
    }

    public String getMensagem(){
        return this.mensagem;
    }
    // Método para exibir os detalhes da mensagem
    public String exibirMensagem() {
        
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter horasFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDate dataHojeISO = LocalDate.now();
        LocalTime horasISO = LocalTime.now(); 

        return "=============================================="  + "\n"
                + "Enviado em " + dataHojeISO.format(dataFormatada) + " ás " 
                + horasISO.format(horasFormatada) + "\n" 
                + "Remetente: " + remetente + "\n" + "\n"
                + mensagem + "\n";
    }

}

package tests.servicesEmailTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entities.Banco;
import entities.Conta;
import entities.ContaPF;
import services.email.*;

public class EmailTest {
    // Criando o Servidor de email que irá guardar todos os emails cadastrados.
    Servidor servidorEmail = new Servidor();

    // Criando o banco com o nome e o e-mail do banco para o envio de mensagens de
    // verificação e transações.
    Banco bancoCriado = new Banco("Banco do Brasil", "suporte@bb.com.br", "admin@bb.com.br", "12345678");

    // Fim das instâncias

    // Instâncias dos usuários cadastrados
    Conta conta1 = new ContaPF("12345678", "Pedro Silva dos Santos", "111.222.333-44", "14/01/2005",
            new Email("pedro@gmail.com"));

    Email emailTest = new Email("leonelMessi32@mail.com");
    Mensagem mensagemTest = new Mensagem("destinatario@gmail.com", "remetente@gmail.com", "mensagem top");
    Mensagem mensagemTest2 = new Mensagem("destinatario2@gmail.com", "remetente2@gmail.com", "mensagem top2");

    List<Mensagem> listaMensagem = emailTest.getMensagensRecebidas();;

    @Test
    public void EmailTests() {

        // Cadastrando o email do Banco no servidor.
        servidorEmail.cadastrarEmail(bancoCriado.getEmailDoBanco());
        bancoCriado.cadastrarConta(conta1);

        assertNotEquals(emailTest.getEmail(), conta1.getEnderecoEmail());
    }

    @Test
    public void getMensagensRecebidasTest() {
        assertEquals(listaMensagem, emailTest.getMensagensRecebidas());
    }

    @Test
    public void adicionarMensagemTest() {
        emailTest.adicionarMensagem(mensagemTest);

        assertTrue(listaMensagem.contains(mensagemTest));
    }

    @Test
    public void deletarMensagemTest() {
        emailTest.adicionarMensagem(mensagemTest);
        emailTest.adicionarMensagem(mensagemTest2);

        emailTest.deletarMensagem(0);
        assertFalse(listaMensagem.contains(mensagemTest));
    }

    @Test
    public void limparCaixaDeEntradaTest(){
        emailTest.adicionarMensagem(mensagemTest);
        emailTest.adicionarMensagem(mensagemTest2);

        emailTest.limparCaixaDeEntrada();
        assertFalse(listaMensagem.contains(mensagemTest));
        assertFalse(listaMensagem.contains(mensagemTest2));
    }
    
    @Test
    public void isCaixaDeEntradaVaziaTest(){
        emailTest.adicionarMensagem(mensagemTest);
        emailTest.adicionarMensagem(mensagemTest2);

        emailTest.limparCaixaDeEntrada();

        assertEquals(true, emailTest.isCaixaDeEntradaVazia());
    }

    @Test
    public void getNumeroDeMensagensTest(){
        emailTest.adicionarMensagem(mensagemTest);
        emailTest.adicionarMensagem(mensagemTest2);

        assertEquals(2, emailTest.getNumeroDeMensagens());
    }
}

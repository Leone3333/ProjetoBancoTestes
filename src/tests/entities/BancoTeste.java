package tests.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import entities.*;
import services.email.Email;
import services.email.Mensagem;

public class BancoTeste {
    
    private Banco banco1Test = new Banco("Banco do Brasil","suporte@bb.com.br", "admin@bb.com.br", "12345678");
    
    private Conta conta1Test = new ContaPF("87654321", "111.222.333-44", "Pedro Silva dos Santos", "14/01/2005",
    new Email("pedro@gmail.com"));


    @Test
    public void BancoTest() {
        // Teste 1 do construtor da classe banco
        assertEquals("Bradesco", banco1Test.getNomeDoBanco());
        assertEquals("bradesco@gmail.com.br", banco1Test.getEmailDoBanco());
    }

    @Test
    public void gerarNumeroDaAgenciaTest() {
        String numeroGerado = banco1Test.gerarNumeroDaAgencia();
        // Verificar se o número gerado tem o formato esperado
        assertTrue(numeroGerado.matches("\\d{4}-\\d{1}"));
    }

    @Test
    public void cadastrarContaTest() {
        banco1Test.cadastrarConta(conta1Test);
        // Verifica se a conta foi adicionada com sucesso à lista de contas do banco
        assertTrue(banco1Test.getContasNoBanco().contains(conta1Test));
    }

    @Test
    public void exibirDadosContaTest() {
        // Redireciona a saída padrão para um ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Adiciona a conta ao banco
        banco1Test.cadastrarConta(conta1Test);

        // Chama o método exibirDados com o número da conta
        banco1Test.exibirDados("87654321");

        // Flushing e fechando o fluxo de saída
        System.out.flush();
        System.setOut(System.out);

        // Obtém a saída produzida pelo método
        String output = outputStream.toString();

        // Verifica se a saída contém os dados esperados da conta
        assertTrue(output.contains("Nome do Titular: Pedro Silva dos Santos"));
        assertTrue(output.contains("Data de Nascimento: 14/01/2005"));
        assertTrue(output.contains("CPF: 111.222.333-44"));
        assertTrue(output.contains("Tipo da Conta: Pessoa Física"));
        assertTrue(output.contains("Número da conta: 87654321"));
        assertTrue(output.contains("Endereço de e-mail: pedro@gmail.com"));
        assertTrue(output.contains("Saldo: R$0.00"));
    }

    @Test
    public void enviarCodigoTest(){
        Mensagem mensagemEnvioCodigo = banco1Test.enviarCodigo("pedro@gmail.com");
        Mensagem mensagemTest = new Mensagem("bradesco@gmail.com.br", "pedro@gmail.com", "Seu código de verificação é: 111");
        assertEquals(mensagemTest, mensagemEnvioCodigo);
    }

    @Test
    public void verificarCodigoTest(){
        boolean verificarReturn = banco1Test.verificarCodigo(111);
        assertEquals(false, verificarReturn);
    }     
}
   
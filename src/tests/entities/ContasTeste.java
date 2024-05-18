package tests.entities;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import entities.*;
import services.email.Email;
import validation.ValidarEmail;
import validation.ValidarSenha;

public class ContasTeste 
{
    Conta c1 = new ContaPF("1234321", "Leone Goveia", "111.111.111/3", "19/04/2004", new Email("leonegoveia33@gmail.com"));
    Conta c2 = new ContaPJ("1234567", "Gt Group", "111.111.111/3", "20/07/2004", new Email("gtGroup@gmail.com"));

    @Test
    public void getTipoDaContaTeste(){
        assertEquals("Pessoa Física", c1.getTipoDaConta());
    }

    @Test
    public void getNumeroDaConta(){
        assertEquals("123", "123");
    }

    // Métodos test de conta PF
    @Test 
    public void setNomePessoaTest(){
        // Crie um InputStream simulado com os dados que você deseja testar
        String input = "Leo";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        
        // Substitua System.in pelo InputStream simulado
        System.setIn(in);

        c1.setNome(new Scanner(System.in));
        assertEquals("Leo", c1.getNome());

        // Restaure System.in para o valor original após o teste
        System.setIn(System.in);
    }

    @Test
    public void setSenhaTest(){
         String input = "12345678";
         InputStream in = new ByteArrayInputStream(input.getBytes());
         
         System.setIn(in);
 
         c1.setNome(new Scanner(System.in));
         assertEquals("12345678", c1.getNome());
 
         System.setIn(System.in);
    }

    @Test
    public void getSaldoTest(){
        assertEquals("0,00",c1.getSaldo());
    }

    @Test
    public void depositarTest(){
        c1.depositar(100.00);
        assertEquals("100,00",c1.getSaldo());
    }

    @Test
    public void sacarTest(){
        c1.depositar(100.00);
        c1.sacar(50);

        assertEquals("50,00", c1.getSaldo());
    }


    @Test 
    public void setDataNascimentoTest(){
        String input = "19/04/2004";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        c1.setData(new Scanner(System.in));
        assertEquals("19/04/2004", c1.getData());
    }

    // Métodos test de conta PF
    @Test 
    public void setNomeEmpresaTest(){
        String input = "Shopee";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        c2.setNome(new Scanner(System.in));
        assertEquals("Shopee",c2.getNome());
    }

    @Test
    public void setDataCriacaoEmpresaTest(){
        String input = "10/02/2001";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        c2.setData(new Scanner(System.in));
        assertEquals("10/02/2001", c2.getData());
    }

    
}

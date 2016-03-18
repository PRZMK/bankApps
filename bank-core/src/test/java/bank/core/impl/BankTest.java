/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.impl;

import bank.core.impl.BankImpl;
import bank.core.BankInterface.*;
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import bank.core.BankInterface;

/**
 *
 * @author Przemek DELL
 */
public class BankTest {

    private BankInterface bank;

    public BankTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bank = new BankImpl();
    }

    @Test
    public void createAccountTest() {
        Integer id = bank.createAccount("A", "B");
        assert id != null;
    }

    @Test
    public void createAccountTest2() {
        Integer id = bank.createAccount("Zawadzki", "Jakubowskie");
        Integer id2 = bank.createAccount("Zawadzki", "Bialystok");
        assert !Objects.equals(id, id2);
    }

    @Test
    public void depositTest() {
        Integer id = bank.createAccount("A", "B");
        bank.deposit(id, 53.4);

    }

    @Test(expected = AccountIdException.class)
    public void depositFailTest() {
        bank.createAccount("A", "B");
        bank.deposit(999, 53.4);
    }

    @Test
    public void getBalanceTest() {
        Integer id = bank.createAccount("A", "B");
        bank.deposit(id, 53.4);
        bank.deposit(id, 33.0);
        double balance = bank.getBalance(id);
        assert balance == 86.4;
    }

    @Test
    public void withdrawTest() {
        Integer id = bank.createAccount("A", "B");
        bank.deposit(id, 65.7);
        bank.withdraw(id, 50.1);
        double balance = bank.getBalance(id);
        
        assert balance == 15.6; 
    }

    @Test(expected = AccountIdException.class)
    public void withdrawFailTest1() {
        Integer id = bank.createAccount("A", "B");
        bank.deposit(id, 65.0);
        bank.withdraw(999, 50.0);
    }

    @Test(expected = InsufficientFundsException.class)
    public void withdrawFailTest2() {
        Integer id = bank.createAccount("A", "B");
        bank.deposit(id, 30.0);
        bank.withdraw(id, 50.0);
    }

    @Test
    public void transferTest() {
        Integer id = bank.createAccount("A", "B");
        Integer id2 = bank.createAccount("S", "C");
        bank.deposit(id, 65.0);
        bank.deposit(id2, 200.0);
        bank.transfer(id, id2, 10.0);
        assert bank.getBalance(id) == 55.0 && bank.getBalance(id2) == 210.0;
    }

    @Test(expected = InsufficientFundsException.class)
    public void transferFailTest1() {
        Integer id = bank.createAccount("A", "B");
        Integer id2 = bank.createAccount("S", "C");
        bank.deposit(id, 65.0);
        bank.deposit(id2, 200.0);
        bank.transfer(id, id2, 100.0);  //brak srodkow
    }

    @Test(expected = AccountIdException.class)
    public void transferFailTest2() {
        Integer id = bank.createAccount("A", "B");
        Integer id2 = bank.createAccount("S", "C");
        bank.deposit(id, 65.0);
        bank.deposit(id2, 200.0);
        bank.transfer(999, id2, 10.0);  //zle id konta
    }

    @Test(expected = AccountIdException.class)
    public void transferFailTest3() {
        Integer id = bank.createAccount("A", "B");
        Integer id2 = bank.createAccount("S", "C");
        bank.deposit(id, 65.0);
        bank.deposit(id2, 200.0);
        bank.transfer(id, 999, 10.0);   //zle id konta
    }

    @After
    public void tearDown() {
        bank = null;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

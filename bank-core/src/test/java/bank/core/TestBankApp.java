/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core;

import bank.core.actual.ActualAccount;
import bank.core.actual.ActualBank;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Przemek DELL
 */
public class TestBankApp {
    
    private static Bank bank;
    
    public TestBankApp() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        bank = new ActualBank();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bank.loadAllCustomers();
        bank.loadAllAccounts();
        
    }
    
    
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

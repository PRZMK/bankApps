/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.actual;

import bank.core.Account;
import bank.core.Bank;
import bank.core.Customer;
import bank.core.dao.DataAccess;
import bank.core.dao.actual.ActualDataAccess;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Przemek DELL
 */
public class ActualBank implements Bank {

    private List<Customer> allCustomers = new ArrayList<>();
    private List<Account> allAccounts = new ArrayList<>();
    private final DataAccess database = new ActualDataAccess();

    @Override
    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void setAllCustomers(List<Customer> allCustomers) {
        this.allCustomers = allCustomers;
    }

    @Override
    public List<Account> getAllAccounts() {
        return allAccounts;
    }

    public void setAllAccounts(List<Account> allAccounts) {
        this.allAccounts = allAccounts;
    }

    @Override
    public void loadAllAccounts() {
        setAllAccounts(database.findAllAccounts());
    }
    
    @Override
    public void loadAllCustomers(){
        setAllCustomers(database.findAllCustomers());
    }
}

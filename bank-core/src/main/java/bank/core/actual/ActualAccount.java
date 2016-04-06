/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.actual;

import bank.core.Account;
import java.util.List;
import bank.core.Customer;
import bank.core.dao.DataAccess;
import bank.core.dao.actual.ActualDataAccess;
import java.util.ArrayList;
import bank.core.Bank2;

/**
 *
 * @author Przemek DELL
 */
public class ActualAccount implements Account {

    private Integer id;
    private String notes;
    private double balance;
    private int customerId;
    private final DataAccess database = new ActualDataAccess();
    private List<Account> allAccounts = new ArrayList<>();

    public ActualAccount(String notes, double balance, int customerId) {
        this.notes = notes;
        this.balance = balance;
        this.customerId = customerId;
    }

    public ActualAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void clone(Account account) {
        this.balance = account.getBalance();
        this.customerId = account.getCustomerId();
        this.id = account.getId();
        this.notes = account.getNotes();
    }

    @Override
    public String toString() {
        return "ActualAccount{" + "id=" + id + ", notes=" + notes + ", balance=" + balance + ", customerId=" + customerId + '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int getCustomerId() {
        return customerId;
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public void save() {
        database.saveAccount(this);
    }

    @Override
    public void loadAccountDataWithId(Integer id) {
        clone(database.findAccount(id));
    }

    @Override
    public void remove() {
        database.removeAccount(this);
    }

    @Override
    public void updateInDatabase() {
        database.updateAccount(this);
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (this.balance > amount) {
            this.balance = this.balance - amount;
            return;
        }else{
            throw new Bank2.InsufficientFundsException();
        }
    }

    @Override
    public void transferMoneyToAccount(double amount, Account destination) {
        this.withdraw(amount);
        destination.deposit(amount);
    }

    @Override
    public void loadAccountDataWithNotes(String notes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

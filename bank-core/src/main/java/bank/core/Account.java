/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core;

import java.util.List;

/**
 *
 * @author Przemek DELL
 */
public interface Account {

    void save();

    void loadAccountDataWithId(Integer id);

    void loadAccountDataWithNotes(String notes);
    
    void remove();
    
    void updateInDatabase();

    void deposit(double amount);

    void withdraw(double amount);

    void transferMoneyToAccount(double amount, Account destination);

    public String getNotes();

    public void setId(Integer id);

    public Integer getId();

    public double getBalance();

    public int getCustomerId();

    public void setNotes(String string);

    public void setBalance(double aDouble);

    public void setCustomerId(int aInt);

    /**
     * Gdy gdy srodki na koncie nie sa wystarczajace do wykonania operacji
     *
     */
    class InsufficientFundsException extends RuntimeException {
    };

    /**
     * Gdy id konta jest nieprawidlowe
     *
     */
    class AccountIdException extends RuntimeException {
    };
}

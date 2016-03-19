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

    /**
     *
     * @param costumerId
     * @param notes informacje dodatkowe o koncie
     * @param balance srodki na koncie
     * @return id utworzonego lub istniejacego konta.
     */
    Integer createAccount(Integer costumerId, String notes, double balance);
    
    /**
     *
     * @param costumerId
     * @param notes
     * @return
     */
    Integer findAccount(Integer costumerId, String notes);
    
    /**
     *
     * @param costumerId
     * @return
     */
    List<Account> findAllAccount(Integer costumerId);
    
    /**
     *
     * @param costumer
     * @return
     */
    List<Account> findAllAccount(Customer costumer);
    
    /**
     *
     * @param id
     * @param amount
     */
    void deposit(Integer id, double amount);

    /**
     *
     * @param id
     * @return
     */
    double getBalance(Integer id);

    /**
     *
     * @param id
     * @param amount
     */
    void withdraw(Integer id, double amount);

    /**
     *
     * @param idSource
     * @param idDestination
     * @param amount
     */
    void transfer(Integer idSource, Integer idDestination, double amount);
    
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

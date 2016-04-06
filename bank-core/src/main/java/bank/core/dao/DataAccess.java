/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.dao;

import bank.core.Account;
import java.util.List;
import bank.core.Customer;

/**
 *
 * @author Przemek DELL
 */
public interface DataAccess {

    /**
     *
     * @param costumer
     */
    void saveCustomer(Customer costumer);

    /**
     *
     * @param costumer
     */
    void removeCustomer(Customer costumer);

    /**
     *
     * @param costumer
     */
    void updateCustomer(Customer costumer);

    /**
     *
     * @param id
     * @return
     */
    Customer findCustomer(Integer id);

    /**
     *
     * @param pesel
     * @return
     */
    Customer findCustomer(String pesel);

    /**
     *
     * @return
     */
    List<Customer> findAllCustomers();

    void saveAccount(Account account);

    void removeAccount(Account account);

    void updateAccount(Account account);

    Account findAccount(Integer id);

    List<Account> findAllCustomerAccounts(Customer customer);

    List<Account> findAllAccounts();
}

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

    void saveCustomer(Customer costumer);

    void removeCustomer(Customer costumer);

    void updateCustomer(Customer costumer);

    Customer findCustomer(Integer id);

    Customer findCustomer(String pesel);

    List<Customer> findAllCustomers();

    void saveAccount(Account account);

    void removeAccount(Account account);

    void updateAccount(Account account);

    Account findAccount(Integer id);

    List<Account> findAllCustomerAccounts(Customer customer);

    List<Account> findAllAccounts();
}

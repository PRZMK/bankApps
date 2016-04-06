/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.dao;

import bank.core.Account;
import bank.core.Customer;

/**
 *
 * @author Przemek DELL
 */
public interface AccountDao {
    void saveAccountToCustomer(Account account, Customer customer);
   
}

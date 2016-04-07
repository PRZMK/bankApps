/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Przemek DELL
 */
public interface Bank {

    public void loadAllCustomers();

    public void loadAllAccounts();

    public List<Account> getAllAccounts();

    public List<Customer> getAllCustomers();

}

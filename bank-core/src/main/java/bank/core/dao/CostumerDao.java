/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.dao;

import java.util.List;
import bank.core.Customer;

/**
 *
 * @author Przemek DELL
 */
public interface CostumerDao {

    /**
     *
     * @param costumer
     */
    void save(Customer costumer);

    /**
     *
     * @param costumer
     */
    void remove(Customer costumer);

    /**
     *
     * @param costumer
     */
    void update(Customer costumer);

    /**
     *
     * @param id
     * @return
     */
    Customer findById(Integer id);

    /**
     *
     * @param pesel
     * @return
     */
    Customer findByPesel(String pesel);
    
    /**
     *
     * @return
     */
    List<Customer> findAll();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core;

/**
 *
 * @author Przemek DELL
 */
public interface Costumer {

    /**
     *
     * @param firstName
     * @param lastName
     * @param pesel
     * @param address
     * @param email
     * @return
     */
    Integer create(String firstName, String lastName, String pesel, String address, String email);

    /**
     *
     * @param id
     */
    void remove(Integer id);

    /**
     *
     * @param pesel
     * @return
     */
    Integer findByPesel(String pesel);

    /**
     *
     * @param firstName
     * @param lastname
     * @return
     */
    Integer find(String firstName, String lastname);

    /**
     *
     * @param email
     * @return
     */
    Integer findByEmail(String email);
    
    /**
     *
     */
    class CostumerIdException extends RuntimeException {
    };
    
    
}

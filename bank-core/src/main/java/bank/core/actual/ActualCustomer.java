/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.actual;

import bank.core.dao.CostumerDao;
import bank.core.dao.actual.ActualCostumerDao;
import bank.core.Customer;

/**
 *
 * @author Przemek DELL
 */
public class ActualCustomer implements Customer{

    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String pesel;
    private String email;
    private final CostumerDao dao = new ActualCostumerDao();


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPesel() {
        return pesel;
    }

    @Override
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
    

    @Override
    public String toString() {
        return "ActualCostumer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pesel=" + pesel + ", email=" + email + '}';
    }

    
    
    @Override
    public Integer create(String firstName, String lastName, String pesel, String address, String email) {
        Customer c = new ActualCustomer();
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setAddress(address);
        c.setEmail(email);
        c.setPesel(pesel);
        dao.save(c);
        return c.getId();
    }

    @Override
    public void remove(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer findByPesel(String pesel) {
        return dao.findByPesel(pesel).getId();
        
    }

    @Override
    public Integer find(String firstName, String lastname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}

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
public interface Customer {

    void save();

    void loadCustomerDataWithPesel(String pesel);

    void loadCustomerDataWithId (Integer id);

    void updateInDatabase();
    
    void remove();
    
    void addAccount(Account account);
    
    public String getFirstName();

    public String getLastName();

    public String getPesel();

    public String getEmail();

    public String getAddress();

    public Integer getId();
    
    public void setId(Integer id);
    
    public void setFirstName(String string);

    public void setLastName(String string);

    public void setPesel(String string);

    public void setEmail(String string);

    public void setAddress(String string);

    class CostumerIdException extends RuntimeException {
    };
    
    
}

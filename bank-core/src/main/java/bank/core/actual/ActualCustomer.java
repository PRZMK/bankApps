/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.actual;

import bank.core.Account;
import bank.core.dao.actual.ActualDataAccess;
import bank.core.Customer;
import java.util.Objects;
import bank.core.dao.DataAccess;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Przemek DELL
 */
public class ActualCustomer implements Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String pesel;
    private String email;
    private final DataAccess database = new ActualDataAccess();
    private List<Account> accounts = new ArrayList<>();

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public ActualCustomer(String firstName, String lastName, String address, String pesel, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.pesel = pesel;
        this.email = email;
    }

    public ActualCustomer(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.id = customer.getId();
        this.pesel = customer.getPesel();
    }

    public ActualCustomer(Integer id) {
        this.id = id;
    }

    public ActualCustomer() {
    }

    @Override
    public String toString() {
        return "ActualCostumer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pesel=" + pesel + ", email=" + email + '}';
    }

    private void copy(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.id = customer.getId();
        this.pesel = customer.getPesel();
    }

    @Override
    public void save() {
        database.saveCustomer(this);
    }

    @Override
    public void remove() {
        database.removeCustomer(this);
    }

    @Override
    public void loadCustomerDataWithPesel(String pesel) {
        this.copy(database.findCustomer(pesel));
    }

    @Override
    public void loadCustomerDataWithId(Integer id) {
        this.copy(database.findCustomer(id));
    }

    @Override
    public void updateInDatabase() {
        database.updateCustomer(this);
    }

    @Override
    public void loadAllAccounts() {
        setAccounts(database.findAllCustomerAccounts(this));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.model;

/**
 *
 * @author Przemek DELL
 */
public class ModelAccount {

    private Integer id;
    private String notes;
    private double balance;
    private int customerId;

    public ModelAccount(String notes, double balance, int customerId) {
        this.notes = notes;
        this.balance = balance;
        this.customerId = customerId;
    }

    public ModelAccount() {
    }

    private void clone(ModelAccount account) {
        this.balance = account.getBalance();
        this.customerId = account.getCustomerId();
        this.id = account.getId();
        this.notes = account.getNotes();
    }

    @Override
    public String toString() {
        return "ActualAccount{" + "id=" + id + ", notes=" + notes + ", balance=" + balance + ", customerId=" + customerId + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}

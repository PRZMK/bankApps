/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.impl;

import java.util.HashMap;
import java.util.Map;
import bank.core.BankInterface;

/**
 *
 * @author Przemek DELL
 */
public class BankImpl implements BankInterface {

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static class account {

        private Integer id;
        private String name;
        private String address;
        private double amount;

        public account(Integer id, String name, String address, double amount) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.amount = round(amount, 2);
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {

            this.amount = round(amount, 2);
        }
    }

    private final Map<Integer, account> map = new HashMap<>();

    private static int idGen = 0;

    private int nextId() {
        return ++idGen;
    }

    @Override
    public Integer createAccount(String name, String address) {
        Integer id = findAccount(name, address);
        if (id != null) {
            return id;
        }
        account newAcc = new account(nextId(), name, address, 0.0);
        map.put(newAcc.getId(), newAcc);
        return newAcc.getId();
    }

    @Override
    public Integer findAccount(String name, String address) {
        for (Map.Entry<Integer, account> entry : map.entrySet()) {
            if (entry.getValue().getName().equals(name) && entry.getValue().getAddress().equals(address)) {
                return entry.getValue().getId();
            }
        }
        return null;
    }

    @Override
    public void deposit(Integer id, double amount) {
        for (Map.Entry<Integer, account> entry : map.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                entry.getValue().setAmount(entry.getValue().getAmount() + amount);
                return;
            }
        }
        throw new BankInterface.AccountIdException();
    }

    @Override
    public double getBalance(Integer id) {
        for (Map.Entry<Integer, account> entry : map.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                return entry.getValue().getAmount();
            }
        }
        throw new BankInterface.AccountIdException();
    }

    @Override
    public void withdraw(Integer id, double amount) {

        for (Map.Entry<Integer, account> entry : map.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                if (entry.getValue().getAmount() > amount) {
                    entry.getValue().setAmount(entry.getValue().getAmount() - amount);
                    return;
                } else {
                    throw new BankInterface.InsufficientFundsException();
                }
            }
        }
        throw new BankInterface.AccountIdException();
    }

    @Override
    public void transfer(Integer idSource, Integer idDestination, double amount) {
        for (Map.Entry<Integer, account> entry : map.entrySet()) {
            if (entry.getValue().getId().equals(idSource)) {
                if (entry.getValue().getAmount() > amount) {
                    entry.getValue().setAmount(entry.getValue().getAmount() - amount);
                    deposit(idDestination, amount);
                    return;
                } else {
                    throw new BankInterface.InsufficientFundsException();
                }
            }
        }
        throw new BankInterface.AccountIdException();
    }
}

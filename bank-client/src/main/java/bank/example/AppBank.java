/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.example;

import bank.core.actual.ActualBank;
import bank.core.Bank.*;
import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bank.core.Bank;
import bank.core.actual.ActualCustomer;
import bank.core.Customer;
import bank.core.exception.DataAccessException;

/**
 *
 * @author Przemek DELL
 */
public class AppBank {

    private static final Logger logger = LoggerFactory.getLogger(AppBank.class);
    private static Bank bank;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Integer id = null, idSource, idDestination;
        double amount, balance;
        bank = new ActualBank();
        String name;
        logger.debug("--Application started");
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("--Application Bank started--");
            System.out.println("1: Create account");
            System.out.println("2: Add funds");
            System.out.println("3: Get balance");
            System.out.println("4: Withdraw funds");
            System.out.println("5: Transfer");
            System.out.println("0: Exit application");
            switch (inputScanner.nextInt()) {
                case 1: {
                    String firstName, lastName, pesel, email, address;
                    Customer c = new ActualCustomer();
                    System.out.print("Enter first name: ");
                    inputScanner.nextLine();
                    firstName = inputScanner.nextLine();
                    name = firstName;
                    System.out.print("Enter last name: ");
                    lastName = inputScanner.nextLine();
                    System.out.print("Enter pesel: ");
                    pesel = inputScanner.nextLine();
                    System.out.print("Enter email: ");
                    email = inputScanner.nextLine();
                    System.out.print("Enter address: ");
                    address = inputScanner.nextLine();
                    try {
                        id = c.create(firstName, lastName, pesel, address, email);
                        logger.info("Account created - id: " + id);
                    } catch (DataAccessException e) {
                        id = c.findByPesel(pesel);
                        logger.info("Account exist - id: " + id);
                        logger.error("" + e);
                    }
//                    id = bank.findAccount(name, address);
//                    if (id != null) {
//                        logger.info("Account exist - id: " + id);
//                    } else {
//                        id = c.create(firstName, lastName, pesel, address, email);
//                        bank.createAccount(name, address);
//                        logger.info("Account created - id: " + id);
//                    }
                }
                break;
                case 2: {
                    String firstName;
                    System.out.print("Enter id: ");
                    id = inputScanner.nextInt();
                    System.out.print("Enter amount: ");
                    amount = inputScanner.nextDouble();
                    try {
                        bank.deposit(id, amount);
                        logger.info("Deposit " + amount + " PLN on account id: " + id);
                    } catch (AccountIdException e) {
                        logger.error("Account not exist " + e);
                    }
                }
                break;
                case 3:
                    System.out.print("Enter id: ");
                    id = inputScanner.nextInt();
                    try {
                        balance = bank.getBalance(id);
                        logger.trace("Account id: " + id + " balance is: " + balance + " PLN");
                    } catch (AccountIdException e) {
                        logger.error("Account not exist " + e);
                    }
                    break;
                case 4:
                    System.out.print("Enter id: ");
                    id = inputScanner.nextInt();
                    System.out.print("Enter amount: ");
                    amount = inputScanner.nextDouble();
                    try {
                        bank.withdraw(id, amount);
                        logger.info("Withdraw " + amount + " PLN from account id: " + id);
                    } catch (AccountIdException e) {
                        logger.error("Account not exist " + e);
                    } catch (InsufficientFundsException e) {
                        logger.error("Lack of funds " + e);
                    }
                    break;
                case 5:
                    System.out.print("Enter source id: ");
                    idSource = inputScanner.nextInt();
                    System.out.print("Enter destination id: ");
                    idDestination = inputScanner.nextInt();
                    System.out.print("Enter amount: ");
                    amount = inputScanner.nextDouble();
                    try {
                        bank.transfer(idSource, idDestination, amount);
                        logger.info("Transfer " + amount + " PLN from account id: " + idSource + " to account id:" + idDestination);
                    } catch (AccountIdException e) {
                        logger.error("Account not exist " + e);
                    } catch (InsufficientFundsException e) {
                        logger.error("Lack of funds " + e);
                    }
                    break;
                case 0:
                    logger.debug("--Application stops");
                    return;
                default:
                    logger.warn("Wrong case");
            }
        }
    }
}

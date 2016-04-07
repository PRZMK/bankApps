/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.example;

import bank.core.Account;
import bank.core.Bank;
import bank.core.Customer;
import bank.core.actual.ActualAccount;
import bank.core.actual.ActualBank;
import bank.core.actual.ActualCustomer;
import bank.core.exception.DataAccessException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Przemek DELL
 */
public class Menu {

    private static final Logger logger = LoggerFactory.getLogger(AppBank.class);
    private Scanner inputScanner = new Scanner(System.in);

    public void showMainMenu() {
        System.out.println("(1) - Customer");
        System.out.println("(2) - Account");
        System.out.println("(0) - Exit");
    }

    public void showCustomerMenu() {
        System.out.println("-- Customer --");
        System.out.println("(1) - Create customer");
        System.out.println("(2) - Show all customers");
        System.out.println("(3) - Show all customer accounts");
        System.out.println("(4) - Remove customer");
        System.out.println("(5) - Edit customer");
        System.out.println("(9) - Main menu");
        System.out.println("(0) - Exit");
    }

    public void showAccountMenu() {
        System.out.println("-- Account --");
        System.out.println("(1) - Create account");
        System.out.println("(2) - Show all accounts");
        System.out.println("(3) - Deposit");
        System.out.println("(4) - Withdraw");
        System.out.println("(5) - Transfer");
        System.out.println("(6) - Remove account");
        System.out.println("(7) - Edit account");
        System.out.println("(9) - Main menu");
        System.out.println("(0) - Exit");
    }

    public void createCustomer() {
        String firstName, lastName, pesel, email, address;
        Customer customer = new ActualCustomer();
        System.out.print("Enter pesel: ");
        pesel = inputScanner.nextLine();

        try {
            customer.loadCustomerDataWithPesel(pesel);
        } catch (Customer.CostumerIdException e) {
            System.out.print("Enter first name: ");
            firstName = inputScanner.nextLine();
            System.out.print("Enter last name: ");
            lastName = inputScanner.nextLine();
            System.out.print("Enter email: ");
            email = inputScanner.nextLine();
            System.out.print("Enter address: ");
            address = inputScanner.nextLine();
            customer = new ActualCustomer(firstName, lastName, address, pesel, email);
            customer.save();
            logger.info("Customer created - id: " + customer.getId());
            return;
        } catch (DataAccessException e) {
            logger.error("" + e);
        }
        logger.info("Customer with pesel " + customer.getPesel() + " exist - id: " + customer.getId());

    }

    public void showAllCustomers() {
        Bank bank = new ActualBank();
        bank.loadAllCustomers();
        bank.getAllCustomers().stream().forEach((customer) -> {
            System.out.println(customer.toString());
        });
    }

    public void showAllCustomerAccounts() {
        Integer id;
        Customer customer = new ActualCustomer();
        List<Account> customerAccounts = new ArrayList<>();
        System.out.print("Enter customer id: ");
        id = inputScanner.nextInt();
        System.out.println(id);
        try {
            customer.loadCustomerDataWithId(id);
        } catch (Customer.CostumerIdException e) {
            logger.error("Customer not exist " + e);
            return;
        }
        customer.loadAllAccounts();
        customerAccounts = customer.getAccounts();
        if (!customerAccounts.isEmpty()) {
            customerAccounts.stream().forEach((account) -> {
                System.out.println(account.toString());
            });
        } else {
            logger.info("Customer with id: " + customer.getId() + " don't have any accounts");
        }
    }

    public void removeCustomer() {
        Integer id;
        Customer customer = new ActualCustomer();
        System.out.print("Enter customer id: ");
        id = inputScanner.nextInt();
        try {
            customer.loadCustomerDataWithId(id);
        } catch (DataAccessException e) {
            logger.error("" + e);
        } catch (Customer.CostumerIdException e) {
            logger.error("Customer not exist " + e);
            return;
        }
        customer.remove();
    }

    public void editCustomer() {
        Integer id;
        String firstName, lastName, pesel, email, address;
        Customer customer = new ActualCustomer();
        System.out.print("Enter customer id: ");
        id = inputScanner.nextInt();
        try {
            customer.loadCustomerDataWithId(id);
        } catch (Customer.CostumerIdException e) {
            logger.error("Customer not exist " + e);
            return;
        }
        System.out.print("Actual first name: " + customer.getFirstName());
        System.out.print("Enter new first name: ");
        firstName = inputScanner.nextLine();
        System.out.print("Actual last name: " + customer.getLastName());
        System.out.print("Enter new last name: ");
        lastName = inputScanner.nextLine();
        System.out.print("Actual pesel: " + customer.getPesel());
        System.out.print("Enter new pesel: ");
        pesel = inputScanner.nextLine();
        System.out.print("Actual email: " + customer.getEmail());
        System.out.print("Enter new email: ");
        email = inputScanner.nextLine();
        System.out.print("Actual address: " + customer.getAddress());
        System.out.print("Enter new address: ");
        address = inputScanner.nextLine();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPesel(pesel);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.updateInDatabase();
    }

    public void createAccount() {
        Integer id;
        String notes;
        Account account = new ActualAccount();
        Customer customer = new ActualCustomer();
        System.out.print("Enter customer id: ");
        id = inputScanner.nextInt();
        inputScanner.nextLine();

        try {
            customer.loadCustomerDataWithId(id);
            System.out.print("Enter account notes: ");
            notes = inputScanner.nextLine();
            account.setCustomerId(id);
            account.setNotes(notes);
            account.save();
        } catch (Customer.CostumerIdException e) {
            logger.error("Customer not exist " + e);
            return;
        }

    }

    public void showAllAccounts() {
        Bank bank = new ActualBank();
        bank.loadAllAccounts();
        bank.getAllAccounts().stream().forEach((customer) -> {
            System.out.println(customer.toString());
        });
    }

    public void deposit() {
        Account account = new ActualAccount();
        Integer id;
        Double amount;
        System.out.print("Enter account id: ");
        id = inputScanner.nextInt();
        System.out.println(id);

        try {
            account.loadAccountDataWithId(id);
            System.out.print("Enter amount: ");
            amount = inputScanner.nextDouble();
            account.deposit(amount);
            account.updateInDatabase();
        } catch (Account.AccountIdException e) {
            logger.error("Account not exist " + e);
            return;
        } catch (InputMismatchException e) {
            logger.error("Invalid input " + e);
        }
    }

    public void withdraw() {
        Account account = new ActualAccount();
        Integer id;
        Double amount;
        System.out.print("Enter account id: ");
        id = inputScanner.nextInt();
        System.out.println(id);

        try {
            account.loadAccountDataWithId(id);
            System.out.print("Enter amount: ");
            amount = inputScanner.nextDouble();
            account.withdraw(amount);
            account.updateInDatabase();
        } catch (Account.AccountIdException e) {
            logger.error("Account not exist " + e);
            return;
        } catch (InputMismatchException e) {
            logger.error("Invalid input " + e);
        } catch (Account.InsufficientFundsException e) {
            logger.error("Lack of funds " + e);
        }
    }

    public void transfer() {
        Account sourceAccount = new ActualAccount();
        Account destinationAccount = new ActualAccount();
        Integer idSource, idDestination;
        Double amount;
        try {
            System.out.print("Enter source account id: ");
            idSource = inputScanner.nextInt();
            sourceAccount.loadAccountDataWithId(idSource);
        } catch (Account.AccountIdException e) {
            logger.error("Account not exist " + e);
            return;
        }
        try {
            System.out.print("Enter destination account id: ");
            idDestination = inputScanner.nextInt();
            destinationAccount.loadAccountDataWithId(idDestination);
            System.out.print("Enter amount: ");
            amount = inputScanner.nextDouble();
            sourceAccount.transferMoneyToAccount(amount, destinationAccount);
            sourceAccount.updateInDatabase();
            destinationAccount.updateInDatabase();
        } catch (Account.AccountIdException e) {
            logger.error("Account not exist " + e);
            return;
        } catch (InputMismatchException e) {
            logger.error("Invalid input " + e);
        } catch (Account.InsufficientFundsException e) {
            logger.error("Lack of funds " + e);
        }

    }

    public void removeAccount() {
        Integer id;
        Account account = new ActualAccount();
        System.out.print("Enter customer id: ");
        id = inputScanner.nextInt();
        inputScanner.nextLine();

        try {
            account.loadAccountDataWithId(id);
            account.remove();

        } catch (Account.AccountIdException e) {
            logger.error("Customer not exist " + e);
            return;
        }
    }

    public void editAccount() {
        Integer id;
        String notes;
        Account account = new ActualAccount();
        System.out.print("Enter account id: ");
        id = inputScanner.nextInt();
        try {
            account.loadAccountDataWithId(id);
        } catch (Account.AccountIdException e) {
            logger.error("Account not exist " + e);
            return;
        }
        inputScanner.nextLine();
        System.out.print("Actual notes: " + account.getNotes());
        System.out.println("Enter new notes: ");
        notes = inputScanner.nextLine();
        account.setNotes(notes);
        account.updateInDatabase();
    }

}

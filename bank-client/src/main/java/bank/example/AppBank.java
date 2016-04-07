/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.example;

import bank.core.actual.ActualBank2;
import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Przemek DELL
 */
public class AppBank {

    private static final Logger logger = LoggerFactory.getLogger(AppBank.class);
    private static Menu menu;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        menu = new Menu();

        logger.debug("--Application started");
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            menu.showMainMenu();
            switch (inputScanner.nextInt()) {
                case 1: {   //create new customer
                    customerMenu();
                }
                break;
                case 2: {
                    accountMenu();
                }
                break;
                case 0: {
                    logger.debug("--Application stops");
                    return;
                }
                default:
                    logger.warn("Wrong case");
            }
        }
    }

    private static void customerMenu() {
        Scanner inputScanner = new Scanner(System.in);
        menu = new Menu();
        while (true) {
            menu.showCustomerMenu();
            switch (inputScanner.nextInt()) {
                case 1: {   //create new customer
                    inputScanner.nextLine();
                    menu.createCustomer();
                }
                break;
                case 2: {
                    menu.showAllCustomers();
                }
                break;
                case 3: {
                    menu.showAllCustomerAccounts();
                }
                break;
                case 4: {
                    menu.removeCustomer();
                }
                break;
                case 5: {
                    menu.editCustomer();
                }
                break;
                case 0: {
                    logger.debug("--Application stops");
                    return;
                }
                default:
                    logger.warn("Wrong case");
            }
        }
    }

    private static void accountMenu() {
        Scanner inputScanner = new Scanner(System.in);
        menu = new Menu();
        
        while (true) {
            menu.showAccountMenu();
            switch (inputScanner.nextInt()) {
                case 1: {   //create new customer
                    inputScanner.nextLine();
                    menu.createAccount();
                }
                break;
                case 2: {
                    menu.showAllAccounts();
                }
                break;
                case 3: {
                    menu.deposit();
                }
                break;
                case 4: {   //withdraw
                    /*System.out.print("Enter id: ");
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
                    }*/
                    menu.withdraw();
                }
                break;
                case 5: {   //transfer
                    /*System.out.print("Enter source id: ");
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
                    }*/
                    menu.transfer();
                }
                break;
                case 6:{
                    menu.removeAccount();
                }
                break;
                case 7:{
                    menu.editAccount();
                }
                break;
                    
                case 0: {
                    logger.debug("--Application stops");
                    return;
                }
                default:
                    logger.warn("Wrong case");
            }
        }
    }
}

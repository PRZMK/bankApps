/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Przemek DELL
 */
public interface bankInterface {

    /**
     * Tworzy nowe lub zwraca id istniejącego konta.
     *
     * @param name imie i nazwisko własciciela
     * @param address adres własciciela
     * @return id utworzonego lub istniejacego konta.
     */
    Integer createAccount(String name, String address);

    /**
     * Znajduje identyfikator konta.
     *
     * @param name imię i nazwisko właściciela
     * @param address adres właściciela
     * @return id konta lub null, gdy brak konta o podanych parametrach
     */
    Integer findAccount(String name, String address);

    /**
     * Dodaje srodki do konta.
     *
     * @param id id konta
     * @param amount srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     */
    void deposit(Integer id, double amount);

    /**
     * Zwraca ilosc srodkow na koncie.
     *
     * @param id id konta
     * @return srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     */
    double getBalance(Integer id);

    /**
     * Pobiera srodki z konta.
     *
     * @param id id konta
     * @param amount srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     * @throws InsufficientFundsException gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    void withdraw(Integer id, double amount);

    /**
     * Przelewa srodki miedzy kontami.
     *
     * @param idSource id konta nadawcy
     * @param idDestination id konta odbiorcy
     * @param amount srodki
     * @throws AccountIdException gdy id konta jest nieprawidlowe
     * @throws InsufficientFundsException gdy srodki na koncie nie sa
     * wystarczajace do wykonania operacji
     */
    void transfer(Integer idSource, Integer idDestination, double amount);
    
    /**
     * Gdy gdy srodki na koncie nie sa wystarczajace do wykonania operacji
     *
     */
    class InsufficientFundsException extends RuntimeException {
    };
    /**
     * Gdy id konta jest nieprawidlowe
     *
     */
    class AccountIdException extends RuntimeException {
    };
}

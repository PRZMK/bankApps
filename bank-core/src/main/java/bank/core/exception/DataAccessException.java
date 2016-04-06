/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.core.exception;

/**
 *
 * @author Przemek DELL
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException() {
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

}

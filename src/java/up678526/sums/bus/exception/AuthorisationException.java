/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up678526.sums.bus.exception;

/**
 *
 * @author up678526
 */
public class AuthorisationException extends Exception {

    /**
     * Creates a new instance of <code>AuthorisationException</code> without
     * detail message.
     */
    public AuthorisationException() {
    }

    /**
     * Constructs an instance of <code>AuthorisationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AuthorisationException(String msg) {
        super(msg);
    }
}

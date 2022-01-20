/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.falabella.boost.codingevaluation.client.exception;

/**
 *
 * @author jsalazar
 */
public class UrlValidException extends Exception{
    private static final long serialVersionUID = -661769207203596752L;

	public UrlValidException(){
        super();
    }

    public UrlValidException(String string) {
        super(string);
    }
}

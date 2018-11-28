/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexer;

/**
 *
 * @author habib
 */
public class Keyword extends Token
{
    public final String lexeme;
    
    public Keyword(String lexeme , int tag)
    {
        super(tag);
        this.lexeme=lexeme;
    }
    
}

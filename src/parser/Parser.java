/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;
import Lexer.Lexer;
import Lexer.Token;
import java.io.IOException;
/**
 *
 * @author habib
 */
public class Parser 
{
    private final Lexer lexer;
    private Token look;
    
    public Parser(Lexer lexer) throws IOException
    {
        this.lexer=lexer;
        move();
    }
    
    private void move() throws IOException
    {
        look=lexer.scan();
    }
    
    private void match(int t) throws IOException
    {
        if(look.tag==t)
        {
            move();
        }
        else
        {
            error("Syntax Error");
        }
    }
    
    private void error(String s)
    {
        throw new Error("new line "+lexer.line+" : "+s);
    }
    
    public void start() throws IOException
    {
        program();
    }
    
    public void program() throws IOException
    {
        block();
    }
}

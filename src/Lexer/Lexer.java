/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexer;
import java.io.IOException;
import java.util.Hashtable;
/**
 *
 * @author habib
 */
public class Lexer 
{
    Hashtable<String,Keyword> words=new Hashtable<>();
    private char peek=' ';
    public static int line = 1;
    
    private void reserve(Keyword kword)
    {
        words.put(kword.lexeme, kword);
    }
    
    private void reserveKeywords()
    {
        reserve(Keyword.FALSE);
        reserve(Keyword.TRUE);
        reserve(Keyword.DO);
        reserve(Keyword.WHILE);
        reserve(Keyword.IF);
        reserve(Keyword.ELSE);
        reserve(Keyword.BREAK);
    }
    
    public Lexer()
    {
        reserveKeywords();
    }
    
    
    
    private void read() throws IOException
    {
        peek = (char) System.in.read();
    }
    
    boolean read(char c) throws IOException
    {
        read();
        if(peek !=c)
        {
            return false;
        }
        peek=' ';
        return true;
    }
    
    public Token scan() throws IOException
    {
        for(;; read())
        {
            if((peek == ' ') || (peek == '\t'))
            {
                
            }
            else
            {
                if(peek == '\n')
                {
                    line++;
                }
                else
                {
                    break;
                }
            }
        }
        switch(peek)
        {
            case '&' :  if(read('&'))
                        {
                           return Keyword.AND;
                        }
                        else
                        {
                            return new Token('&');
                        }
            case '|' :  if(read('|'))
                        {
                            return Keyword.OR;
                        }
                        else
                        {
                            return new Token('|');
                        }
            case '>' :  if(read('='))
                        {
                            return Keyword.G_EQUAL;
                        }
                        else
                        {
                            return new Token('>');
                        }
            case '<' :  if(read('='))
                        {
                            return Keyword.L_EQUAL;
                        }
                        else
                        {
                            return new Token('<');
                        }
            case '='  : if(read('='))
                        {
                            return Keyword.EQUAL;
                        }
                        else
                        {
                            return new Token('=');
                        }
            case '!'  : if(read('='))
                        {
                            return Keyword.N_EQUAL;
                        }
                        else
                        {
                            return new Token('!');
                        }
        }
        
    }
}

    


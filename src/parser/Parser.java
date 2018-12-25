/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;
import Lexer.Lexer;
import Lexer.Tag;
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
    
    public void block() throws IOException
    {
        match('{');
        decls();
        stmts();
        match('}');
    }
    
    //what about int a,b, ... ca,d2
    private void decls() throws IOException
    {
        type();
        match(Tag.ID);
        match(';');
    }
    
    private void type() throws IOException
    {
        match(Tag.BASIC_TYPE);
        if(look.tag=='[')
        {
            dims();
        }
    }
    
    private void dims() throws IOException
    {
        match('[');
        match(Tag.NUM);
        match(']');
        if(look.tag=='[')
        {
            dims();
        }
    }
    
    private void stmts() throws IOException
    {
        if(look.tag=='}')
        {
            
        }
        else
        {
            stmt();
            stmts();
        }
    }
    
    private void stmt() throws IOException
    {
        switch(look.tag)
        {
            case ';'      : move();
                            return ;
            case Tag.IF   : match(Tag.IF);
                            match('(');
                            bool();
                            match(')');
                            stmt();
                            if(look.tag!=Tag.ELSE)
                            {
                                return ;
                            }
                            match(Tag.ELSE);
                            stmt();
                            return;
            
        }
    }
    
    private void bool() throws IOException
    {
        join();
        while(look.tag==Tag.OR)
        {
            move();
            join();
        }
    }
    
    private void join() throws IOException
    {
        equality();
        while(look.tag==Tag.AND)
        {
            move();
            equality();
            //8:45
        }
    }
    
    private void equality() throws IOException
    {
        rel();
        while(look.tag==Tag.EQ || look.tag==Tag.NE)
        {
            move();
            rel();
        }
    }
    
    private void rel() throws IOException
    {
        expr();
        switch(look.tag)
        {
            case '<' :
            case Tag.LE :
            case Tag.GE :
            case '>' :  move();
                        expr();
                        return;
            default : 
        }
    }
    
    private void expr() throws IOException 
    {
        term();
        while(look.tag=='+' || look.tag=='-')
        {
            move();
            term();
        }
    }
    
    private void term() throws IOException
    {
        unary();
        while(look.tag =='*' || look.tag=='/')
        {
            move();
            unary();
        }
    }
    
    private void unary() throws IOException
    {
        if(look.tag=='-')
        {
            move();
            unary();
        }
        else
        {
            if(look.tag=='!')
            {
                move();
                unary();
            }
            else
            {
                factor();
            }
        }
    }
    
    private void factor() throws IOException
    {
        switch(look.tag)
        {
            case '(' : move();
                       bool();
                       match(')');
                       return;
            case Tag.NUM : move();
                           return;
            case Tag.REAL : move();
                            return;
            case Tag.TRUE : move();
                            return;
            case Tag.FALSE : move();
                             return;
            case Tag.ID    : move();
                             if(look.tag !='[')
                             {
                                 
                             }
                             else
                             {
                                 offset();
                             }
                             return;
            default : error("Syntax error");
            
        }
    }
    
    private void offset() throws IOException
    {
        
    }
}

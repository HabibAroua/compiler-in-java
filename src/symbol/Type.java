/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbol;
import Lexer.Keyword;
import Lexer.Tag;
/**
 *
 * @author habib
 */
public class Type extends Keyword
{
    public final int width;
    
    public Type(String lexeme , int tag , int width)
    {
        super(lexeme,tag);
        this.width=width;
    }
    
    public static Type INT =new Type("int",Tag.BASIC_TYPE,4);
    public static Type FLOAT =new Type("int",Tag.BASIC_TYPE,4);
    public static Type INT =new Type("int",Tag.BASIC_TYPE,4);
    public static Type INT =new Type("int",Tag.BASIC_TYPE,4);
       
}

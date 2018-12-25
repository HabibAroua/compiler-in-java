package redcompiler;
import Lexer.Lexer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import parser.Parser;
/**
 *
 * @author Habib Aroua
 */
public class RedCompiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        System.setIn(new FileInputStream(new File(args[0])));
        Lexer lexer=new Lexer();
        Parser parser=new Parser(lexer);
        parser.start();
    }   
}
package main;

import java.io.*;
import lexer.*;
 import parser.*;

public class Main {
    public static void main(String[] args){
        Lexer lex = new Lexer();
        try{
            Parser parse =  new Parser(lex);
            parse.program();
            System.out.write('\n');
        }
        catch(IOException ex){
            System.out.println("Compilation failed!");
        }
    }
}

package lexer;

import java.io.*;
import java.util.*;
import symbols.*;

public class Lexer {
    public static int line=1;
    char peek=' ';
    Hashtable<String,Word> words= new Hashtable<String,Word>();
    void reserve(Word w){
        words.put(w.lexeme, w);
    }
    public Lexer(){
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else",Tag.ELSE));
        reserve(new Word("while",Tag.WHILE));
        reserve(new Word("do",Tag.DO));
        reserve(new Word("break",Tag.BREAK));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);

    }
    void readch() throws IOException{
        peek = (char)System.in.read();
    }

    boolean readch(char c) throws IOException{
        readch();
        if(peek != c)
            return false;
        peek=' ';
        return true;
    }

    public Token scan() throws IOException{
        for(;;readch()){
            if(peek==' '|| peek=='\t')
                continue;
            else if(peek=='\r'){
                /* In windows new line is CRLF
                    next character is to be newline*/
                    readch();
                    line+=1;
            }
            else if(peek=='\n')
                line+=1;
            else
                break;
        }

        switch(peek){
            case '&':
                if(readch('&')){
                    //  System.out.println("and");
                     return Word.and; 
                }
                else{
                    // System.out.println("ampersand");
                    return new Token('&');   
                }
            case '|':
                if(readch('|')){
                    // System.out.println("or");
                    return Word.or;
                }
                else{
                    // System.out.println("bar");
                    return new Token('|');
                }
            case '=':
                if(readch('=')){
                    // System.out.println("equals");
                    return Word.eq;
                }
                else{
                    // System.out.println("equal to");
                    return new Token('=');
                }
            case '!':
                if(readch('=')){
                    // System.out.println("Not equal to");
                    return Word.ne;
                }
                else{
                    // System.out.println("not");
                    return new Token('!');
                }
            case '<':
                if(readch('=')){
                    // System.out.println("less than equal to");
                    return Word.le;
                }else{
                    // System.out.println("less than");
                    return new Token('<');
                }
            case '>':
                if(readch('=')){
                    // System.out.println("greater than equal to");
                    return Word.ge;
                }
                else{
                    // System.out.println("greater than");
                    return new Token('>');
                }
        }
        if(Character.isDigit(peek)){

            int v=0;
            do{
                v=10*v + Character.digit(peek, 10);
                readch();
            }while(Character.isDigit(peek));

            if(peek != '.'){
                // System.out.println(v);
                return new Num(v);
            }
            float x=v,d=10;
            for(;;){
                readch();
                if(!Character.isDigit(peek))
                    break;
                x+=Character.digit(peek, 10)/d;
                d*=10;
            }
            // System.out.println(x);
            return new Real(x);
        }
        if(Character.isLetter(peek)){
            StringBuffer b = new StringBuffer();
            do{
                b.append(peek);
                readch();
            }while(Character.isLetterOrDigit(peek));
            String s = b.toString();
            // System.out.println(s);
            Word w=(Word)words.get(s);
            if(w != null){
                return w;
            }
            w=new Word(s, Tag.ID);
            words.put(s, w);
            return w;
        }
        Token tok= new Token(peek);
        peek=' ';
        // System.out.println(tok.toString());
        return tok;
    }
}

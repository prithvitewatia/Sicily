package inter;

public class Stmt extends Node{
    public Stmt(){};
    // Null represents an empty sequence of statements
    public static Stmt Null = new Stmt();
    // Called with labels begin and after
    public void gen(int a,int b){};
    int after = 0;  // saves label after
    public static Stmt Enclosing = Stmt.Null;   //used in break stmts
}
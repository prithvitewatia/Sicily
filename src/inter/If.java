package inter;

import symbols.*;
public class If extends Stmt{
    Expr expr;
    Stmt stmt;
    public If(Expr x,Stmt st){
        expr = x;
        stmt = st;
        if(expr.type!=Type.Bool){
         expr.error("Boolean required in if");   
        }
    }
    public void gen(int b,int a){
        int label = newlabel();
        // fall through on true
        // Goto a on false
        expr.jumping(0, a);
        emitlabel(a);
        stmt.gen(label, a);
    }
}

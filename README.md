## Sicily
  
Sicily is the **front-end** of my small programming language.  
It generates the intermediate code representation for the source code provided.  

# Grammar
The grammar of the language is  
```
program -> block
block -> { decls stmts }
decls -> decls decl
      -> epsilon
decl -> type id ;
type -> type [ num ]
     -> basic
stmts -> stmts stmt
      -> epsilon

stmt -> loc = bool;
     -> if ( bool ) stmt
     -> if ( bool ) stmt else stmt
     -> while ( bool ) stmt
     -> do stmt while ( bool ) ;
     -> break ;
     -> block
     -> loc [ bool ]
     -> id

bool -> bool || join
     -> join
join -> join && equality
     -> equality
equality -> equality == rel
         -> equality != rel
         -> rel
rel -> expr < expr
    -> expr <= expr
    -> expr >= expr
    -> expr > expr
    -> expr
expr -> expr + term
     -> expr - term
     -> term
term -> term * unary
     -> term / unary
     -> unary
unary -> ! unary
      -> - unary
      -> factor
factor -> ( bool)
       -> loc
       -> num
       -> real
       -> true
       -> false
```

## Installation and usage
1. Clone the repository into your computer.  
```
git clone repo
```
2. In the cloned repo directory create a class directory.  
3. In the src directory compile the files by a java compiler
```
javac -d ../class main/*.java
```
4. In the class directory create a source code file (test.txt) and generate
intermediate code by executing the following command in the class directory.
```
java main.Main < test.txt
```

## Examples
The source file
![Source Code](https://github.com/prithvitewatia/Sicily/blob/main/doc_imgs/sly_test_file.png)
And its output  
![output](https://github.com/prithvitewatia/Sicily/blob/main/doc_imgs/frontendres.png)


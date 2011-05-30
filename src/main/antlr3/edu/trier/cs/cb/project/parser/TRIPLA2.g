grammar TRIPLA2;

options {
  language = Java;
}

@header {
  package edu.trier.cs.cb.project.parser;
}

@lexer::header {
  package edu.trier.cs.cb.project.parser;
}

let returns [IASTNode result]
  :   'let' definitions 'in' expressions  { ASTNode n = new ASTNode("let"); n.addChild($definitions.result); n.addChild($definitions.result); $result = n; }
  ;

definitions returns [IASTNode result]
  :   definition definitions*  { $result = new ASTNode("definitions"); }
  ;

definition returns [IASTNode result]
  :   ID '(' variables ')' '{' expressions '}'  { $result = new ASTNode("definition"); }
  ;

variables returns [IASTNode result]
  :   ID (',' ID)*  { $result = new ASTNode("variables"); }
  ;

expressions returns [IASTNode result]
  :   expression (';' expression)*  { $result = new ASTNode("expressions"); }
  ;

expression returns [Expression result]
  :   relation  { $result = $relation.result; }
  |   assignment  { $result = new Identifier("assignment"); }
  |   'if' expression 'then' expressions 'else' expressions  { $result = new Identifier("if"); }
  ;

assignment returns [IASTNode result]
  :   ID '=' expression  { $result = new ASTNode("assignment"); }
  ;

term returns [Expression result]
  :   ID        { $result = new Identifier($ID.text); }
  |   CONST     { Constant c = new Constant(Integer.parseInt($CONST.text)); $result =  c; }
  |   ID '(' arguments ')'  { $result = new Identifier("function call"); } 
  |   '(' expression ')'    { $result = $expression.result; }
  ;

mult returns [Expression result]
  :   t1=term (op=('*' | '/') t2=term)*  {
        if (t2==null) {
          $result = $t1.result;
        } else {
          $result = new Operation($op.text, $t1.result, $t2.result);
        }
      }
  ;
  
add returns [Expression result]
  :   m1=mult
      (op=('+' | '-') m2=mult)*  {
        if (m2==null) {
          $result = $m1.result;
        } else {
          $result = new Operation($op.text, $m1.result, $m2.result);
        }
      }
  ;

relation returns [Expression result]
  :   a1=add (op=('==' | '!=' | '<' | '>') a2=add)*  {
		    if (a2==null)
		      $result = $a1.result;
		    else
		      $result = new Relation($op.text, $a1.result, $a2.result);
		  }
  ;

arguments returns [IASTNode result]
  :   expression (',' expression)*  { $result = new ASTNode("arguments"); }
  ;

WS : (' ' | '\t' | '\n' | '\r' | '\f' )+ {$channel = HIDDEN;};
ID : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
CONST : '0' | ('1'..'9')('0'..'9')*;

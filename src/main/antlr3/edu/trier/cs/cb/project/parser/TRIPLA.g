grammar TRIPLA;

options {
  language = Java;
}

@header {
  package edu.trier.cs.cb.project.parser;
}

@lexer::header {
  package edu.trier.cs.cb.project.parser;
}

let returns [ASTNode result]
  :   'let' definitions 'in' expressions  { $result = new ASTNode("let"); $result.addChild($definitions.result); $result.addChild($definitions.result); }
  ;

definitions returns [ASTNode result]
  :   definition definitions*  { $result = new ASTNode("definitions"); }
  ;

definition returns [ASTNode result]
  :   ID '(' variables ')' '{' expressions '}'  { $result = new ASTNode("definition"); }
  ;

variables returns [ASTNode result]
  :   ID (',' ID)*  { $result = new ASTNode("variables"); }
  ;

expressions returns [ASTNode result]
  :   expression (';' expression)*  { $result = new ASTNode("expressions"); }
  ;

expression returns [ASTNode result]
  :   relation  { $result = new ASTNode("const"); }
  |   assignment  { $result = new ASTNode("const"); }
  |   'if' expression 'then' expressions 'else' expressions  { $result = new ASTNode("expression"); }
  ;

assignment returns [ASTNode result]
  :   ID '=' expression  { $result = new ASTNode("assignment"); }
  ;

term returns [ASTNode result]
  :   ID        { $result = new ASTNode("id"); }
  |   CONST     { $result = new ASTNode("const"); }
  |   ID '(' arguments ')'  { $result = new ASTNode("function call"); } 
  |   '(' expression ')'    { $result = new ASTNode("parenthesis"); }
  ;

mult returns [ASTNode result]
  :   term (('*' | '/') term)*  { $result = new ASTNode("mult"); }
  ;
  
add returns [ASTNode result]
  :   mult (('+' | '-') mult)*  { $result = new ASTNode("add"); }
  ;

relation returns [ASTNode result]
  :   add (('==' | '!=' | '<' | '>') add)*  { $result = new ASTNode("relation"); }
  ;

arguments returns [ASTNode result]
  :   expression (',' expression)*  { $result = new ASTNode("arguments"); }
  ;

WS : (' ' | '\t' | '\n' | '\r' | '\f' )+ {$channel = HIDDEN;};
ID : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
CONST : '0' | ('1'..'9')('0'..'9')*;

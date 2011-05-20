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

let
  :   'let' definitions 'in' expressions
  ;

definitions
  :   definition definitions*
  ;

definition
  :   ID '(' variables ')' '{' expressions '}'
  ;

variables
  :   ID (',' ID)*
  ;

expressions
  :   expression (';' expression)*
  ;

expression
  :   relation
  |   assignment
  |   'if' expression 'then' expressions 'else' expressions
  ;

assignment
  :   ID '=' expression
  ;

term
  :   ID
  |   CONST
  |   ID '(' arguments ')'
  |   '(' expression ')'
  ;
  
mult
  :   term (('*' | '/') term)*
  ;
  
add
  :   mult (('+' | '-') mult)*
  ;

relation
  :   add (('==' | '!=' | '<' | '>') add)*
  ;

arguments
  :   expression (',' expression)*
  ;

WS : (' ' | '\t' | '\n' | '\r' | '\f' )+ {$channel = HIDDEN;};
ID : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
CONST : '0' | ('1'..'9')('0'..'9')*;

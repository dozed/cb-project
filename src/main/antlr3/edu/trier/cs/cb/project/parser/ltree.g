grammar ltree;

options {
  language = Java;
  output = AST;
}

tokens {
	LET;
  CALL;
  IF;
  IFBRANCH;
  ELSEBRANCH;
  ASSIGNMENT;
  FUNCTION;
}

@header {
  package edu.trier.cs.cb.project.parser;
}

@lexer::header {
  package edu.trier.cs.cb.project.parser;
}

let
	:		'let' definitions 'in' expressions
  				-> ^(LET definitions expressions)							// rewrite rule, uses imaginary token
	;

definitions
	:		definition definition*														// implicit list
	;

definition
  :   ID '(' variables ')' '{' expressions '}'
  				-> ^(FUNCTION ID variables expressions)
  ;

variables
  :   ID (',' ID)* -> ID+																// explicit list, removing split char
  ;

expressions
  :   exprs+=expression (';' exprs+=expression)*
  				-> $exprs+																		// list labels
  ;

expression
  :   relation
  |   assignment
  |		let									// should only be available in definitions
  |   'if' expression 'then' exprs1=expressions 'else' exprs2=expressions
  				-> ^(IF expression ^(IFBRANCH $exprs1) ^(ELSEBRANCH $exprs2))
  ;

assignment
  :   ID '=' expression -> ^(ASSIGNMENT ID expression)
  ;

relation
  :   add (('==' | '!=' | '<' | '>')^ add)*
  ; 

add
  :   mult ((Add | Sub)^ mult)* 
  ;
 
mult
  :   term ((Mul | Div)^ term)*
  ;

term
  :   ID
  |   CONST
  |   ID '(' arguments ')' -> ^(CALL ID arguments)
  |   '(' expression ')' -> expression									// remove braces
  ;

arguments
  :   expression (',' expression)* -> expression+
  ; 

Add    : '+';
Sub    : '-';
Mul    : '*';
Div    : '/';
WS : (' ' | '\t' | '\n' | '\r' | '\f' )+ {$channel = HIDDEN;};
ID : ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*;
CONST : '0' | ('1'..'9')('0'..'9')*;

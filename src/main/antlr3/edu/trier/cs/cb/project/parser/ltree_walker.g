tree grammar ltree_walker;

options {
  language = Java;
  tokenVocab = ltree;
  ASTLabelType = CommonTree;
  output = AST;
}

@header {
  package edu.trier.cs.cb.project.parser;
}

eval returns [Expression e]
	:		a=expression { $e = $a.e; }
	;

let returns [Let l]
	:		^(LET d=definitions e=expressions) { $l = new Let($d.result, $e.result); }
	;

definitions returns [List<FunctionDefinition> result]
@init {
	$result = new ArrayList<FunctionDefinition>();
}
	:		(d=definition { $result.add($d.result); })+
	;
	
definition returns [FunctionDefinition result]
	:		^(FUNCTION ID v=variables e=expressions) { $result = new FunctionDefinition($ID.text, $v.result, $e.result); }
	;

variables returns [List<Identifier> result]
@init {
	$result = new ArrayList<Identifier>();
}
	:		(ID { $result.add(new Identifier($ID.text)); })+
	;
expressions returns [List<Expression> result]
@init {
	$result = new ArrayList<Expression>();
}
	:		(e=expression { $result.add($e.e); })+
	;

expression returns [Expression e]
	:		^(Add a=expression b=expression)	{ $e = new Operation("+", $a.e, $b.e); }
	|		^(Sub a=expression b=expression)	{ $e = new Operation("-", $a.e, $b.e); }
	|		^(Mul a=expression b=expression) 	{ $e = new Operation("*", $a.e, $b.e); }
	|		^(Div a=expression b=expression)	{ $e = new Operation("/", $a.e, $b.e); }
	|		^(op=('==' | '!=' | '<' | '>') a=expression b=expression)
																				{ $e = new Relation($op.text, $a.e, $b.e); }
	|		ID																{ $e = new Identifier($ID.text); }
	|		CONST 														{ $e = new Constant(Integer.parseInt($CONST.text)); }
	|		^(CALL ID args=expressions)				{ $e = new FunctionCall($ID.text, $args.result); }
	|		^(IF eq=expression ^(IFBRANCH e1=expressions) ^(ELSEBRANCH e2=expressions))
																				{ $e = new IfExpression($eq.e, $e1.result, $e2.result); }
	;


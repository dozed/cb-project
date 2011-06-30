package edu.trier.cs.cb.project.parser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.trier.cs.cb.project.parser.util.XmlPrettyPrinter;
import edu.trier.cs.cb.project.parser.visitor.DomBuilderVisitor;

public class ParserTest {

	private DomBuilderVisitor visitor = new DomBuilderVisitor();

	@Test
	public void testSimpleGrammar() throws RecognitionException {
		testSimpleGrammarInternal("let f1(b) { if (b == 0) then 0 else f1(b-1) }\n"
				+ "       f2(a,b) { if (a > b) then f1(a) else f1(b) }\n" + "in f1(10); f2(10,20)");
		testSimpleGrammarInternal("10 + 20 + 40 + 60");
	}

	public void testSimpleGrammarInternal(String str) throws RecognitionException {
		CharStream charStream = new ANTLRStringStream(str);
		TRIPLALexer lexer = new TRIPLALexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		TRIPLAParser parser = new TRIPLAParser(tokenStream);
		parser.let();
	}

	@Test
	public void testLet() throws RecognitionException {
		CharStream charStream = new ANTLRStringStream("10 + 20 * 40 + 60");
		charStream = new ANTLRStringStream("let f1(b) { if (b == 0) then 0 else f1(b-1) }\n"
				+ "       f2(a,b) { if (a > b) then f1(a) else f1(b) }\n" + "in f1(10); f2(10,20)");
		ltreeLexer lexer = new ltreeLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		ltreeParser parser = new ltreeParser(tokenStream);
		ltreeParser.let_return r = parser.let();

		CommonTree tree = (CommonTree) r.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
		ltree_walker walker = new ltree_walker(nodes);
		Let let = walker.let().l;

		visitor.visit(let);
		Document doc = visitor.getDocument();
		XmlPrettyPrinter.serialize(doc, System.out);
	}

	@Test
	public void testLet2() throws RecognitionException {
		CharStream charStream = new ANTLRStringStream("10 + 20 * 40 + 60");
		charStream = new ANTLRStringStream("let\n" + 
				"	mult(a,b) { a*b }\n" + 
				"	add(a,b) {\n" + 
				"		let\n" + 
				"			inc(a) {\n" + 
				"				if (b!=0)\n" + 
				"					inc(a+1)\n" + 
				"				else\n" + 
				"					mult(a,1)\n" + 
				"			}\n" + 
				"		in\n" + 
				"			inc(a)\n" + 
				"	}\n" + 
				"in\n" + 
				"	add(mult(2,3),add(4,5))\n" + 
				"");
		ltreeLexer lexer = new ltreeLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		ltreeParser parser = new ltreeParser(tokenStream);
		ltreeParser.let_return r = parser.let();

		CommonTree tree = (CommonTree) r.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
		ltree_walker walker = new ltree_walker(nodes);
		Let let = walker.let().l;

		visitor.visit(let);
		Document doc = visitor.getDocument();
		XmlPrettyPrinter.serialize(doc, System.out);
	}

	@Test
	public void testDefinition() throws RecognitionException {
		testDefinitionInternal("f1(a, b) { foo(1, 2); bar(b-1) }");
		testDefinitionInternal("f1(a, b) { if (b == 0) then foo(1, 2) else f1(b-1) }");
	}

	public void testDefinitionInternal(String str) throws RecognitionException {
		CharStream charStream = new ANTLRStringStream(str);
		ltreeLexer lexer = new ltreeLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		ltreeParser parser = new ltreeParser(tokenStream);
		ParserRuleReturnScope r = parser.definition();

		CommonTree tree = (CommonTree) r.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
		ltree_walker walker = new ltree_walker(nodes);
		FunctionDefinition def = walker.definition().result;

		visitor.visit(def);
		Document doc = visitor.getDocument();
		XmlPrettyPrinter.serialize(doc, System.out);
	}

	@Test
	public void testExpression() throws RecognitionException {
		testExpressionInternal("10 + 20 * 40 + 60");
		testExpressionInternal("f1(a, b)");
	}

	private void testExpressionInternal(String str) throws RecognitionException {
		CharStream charStream = new ANTLRStringStream(str);
		ltreeLexer lexer = new ltreeLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		ltreeParser parser = new ltreeParser(tokenStream);
		ParserRuleReturnScope r = parser.expression();

		CommonTree tree = (CommonTree) r.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
		ltree_walker walker = new ltree_walker(nodes);
		Expression e = walker.expression().e;

		visitor.visit(e);
		Document doc = visitor.getDocument();
		XmlPrettyPrinter.serialize(doc, System.out);
	}

	public void dump(Tree t) {
		dump(t, 0);
	}

	public void dump(Tree t, int level) {
		for (int i = 0; i < level; i++)
			System.out.print("-");
		System.out.println(t.getText());
		for (int i = 0; i < t.getChildCount(); i++) {
			dump(t.getChild(i), level + 1);
		}
	}
}

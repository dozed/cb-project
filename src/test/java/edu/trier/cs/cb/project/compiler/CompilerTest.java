package edu.trier.cs.cb.project.compiler;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.Test;

import edu.trier.cs.cb.project.machine.AbstractMachine;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.Let;
import edu.trier.cs.cb.project.parser.ltreeLexer;
import edu.trier.cs.cb.project.parser.ltreeParser;
import edu.trier.cs.cb.project.parser.ltree_walker;

public class CompilerTest {

	@Test
	public void test() throws RecognitionException {
		CharStream charStream = new ANTLRStringStream("10 + 20 * 40 + 60");
		charStream = new ANTLRStringStream("let f1(b) { if (b == 0) then 0 else f1(b-1) }\n"
				+ "       f2(a,b) { if (a > b) then f1(a) else f1(b) }\n" + "in f1(10); f2(10,20)");
		charStream = new ANTLRStringStream("let fac(x) { if x == 1 then 1 else fac(x-1)*x }\n"
				+ "in fac(3)\n");
		ltreeLexer lexer = new ltreeLexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		ltreeParser parser = new ltreeParser(tokenStream);
		ltreeParser.let_return r = parser.let();

		CommonTree tree = (CommonTree) r.getTree();
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
		ltree_walker walker = new ltree_walker(nodes);
		Let let = walker.let().l;

		Compiler c = new Compiler();
		List<Instruction> instructions = c.compile(let);

		for (Instruction i : instructions) {
			System.out.println(i.toString());
		}

		AbstractMachine m = new AbstractMachine();
		m.execute(instructions);
		m.printStack();

	}

}

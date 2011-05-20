package edu.trier.cs.cb.project.parser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.junit.Test;

public class ParserTest {

	@Test
	public void test() throws RecognitionException {
		CharStream charStream = new ANTLRStringStream("let f1(b) { if (b == 0) then 0 else f1(b-1) }\n" + 
				"       f2(a,b) { if (a > b) then f1(a) else f1(b) }\n" + 
				"in f1(10); f2(10,20)");
		TRIPLALexer lexer = new TRIPLALexer(charStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		TRIPLAParser parser = new TRIPLAParser(tokenStream );
		parser.let();
	}
	
}

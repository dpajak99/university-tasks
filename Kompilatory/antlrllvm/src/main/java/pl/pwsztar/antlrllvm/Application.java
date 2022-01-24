package pl.pwsztar.antlrllvm;

import org.antlr.v4.gui.Trees;
import pl.pwsztar.antlrllvm.grammar.CPP14Lexer;
import pl.pwsztar.antlrllvm.grammar.CPP14Parser;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;

public class Application {
  public static void main(String[] args) throws Exception {
    //prepare token stream
    CharStream stream = new ANTLRInputStream(new FileInputStream("/media/dominik/dane/github/PWSZ/Kompilatory/antlrllvm/samples/example.cc"));
    CPP14Lexer lexer = new CPP14Lexer(stream);
    TokenStream tokenStream = new CommonTokenStream(lexer);
    CPP14Parser parser = new CPP14Parser(tokenStream);
    ParseTree tree = parser.translationunit();

    //show AST in console
    System.out.println(tree.toStringTree(parser));

    Trees.inspect(tree, parser);
  }
}
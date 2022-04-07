// Generated from /media/dominik/dane/github/PWSZ/Kompilatory/trajectory_parser/src/utils/parsers/jps_core_trajectory_grammar/JpsCoreTrajectoryGrammar.g4 by ANTLR 4.9.2

    package antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JpsCoreTrajectoryGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BREAKPOINT=1, WS=2, INT=3, REAL=4, NEW_LINE=5;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "BREAKPOINT", "WS", "INT", "REAL", "NEW_LINE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\t'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BREAKPOINT", "WS", "INT", "REAL", "NEW_LINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JpsCoreTrajectoryGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JpsCoreTrajectoryGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\7V\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\7\2\23\n\2\f\2\16\2"+
		"\26\13\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5#\n\5\f\5\16\5"+
		"&\13\5\3\5\3\5\3\5\7\5+\n\5\f\5\16\5.\13\5\5\5\60\n\5\3\6\3\6\7\6\64\n"+
		"\6\f\6\16\6\67\13\6\3\6\3\6\7\6;\n\6\f\6\16\6>\13\6\3\6\3\6\7\6B\n\6\f"+
		"\6\16\6E\13\6\3\6\3\6\7\6I\n\6\f\6\16\6L\13\6\5\6N\n\6\3\7\5\7Q\n\7\3"+
		"\7\3\7\3\7\3\7\2\2\b\3\2\5\3\7\4\t\5\13\6\r\7\3\2\6\4\2\f\f\17\17\4\2"+
		"\13\f\"\"\3\2\63;\3\2\62;\2`\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\3\17\3\2\2\2\5\27\3\2\2\2\7\33\3\2\2\2\t/\3\2\2\2"+
		"\13M\3\2\2\2\rP\3\2\2\2\17\24\7%\2\2\20\21\n\2\2\2\21\23\13\2\2\2\22\20"+
		"\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25\4\3\2\2\2\26\24"+
		"\3\2\2\2\27\30\7\13\2\2\30\31\3\2\2\2\31\32\b\3\2\2\32\6\3\2\2\2\33\34"+
		"\t\3\2\2\34\35\3\2\2\2\35\36\b\4\2\2\36\b\3\2\2\2\37\60\7\62\2\2 $\t\4"+
		"\2\2!#\t\5\2\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\60\3\2\2\2&"+
		"$\3\2\2\2\'(\7/\2\2(,\t\4\2\2)+\t\5\2\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2"+
		",-\3\2\2\2-\60\3\2\2\2.,\3\2\2\2/\37\3\2\2\2/ \3\2\2\2/\'\3\2\2\2\60\n"+
		"\3\2\2\2\61N\5\t\5\2\62\64\t\5\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3"+
		"\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\28<\7\60\2\29;\t\5\2\2:9"+
		"\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=N\3\2\2\2><\3\2\2\2?C\7/\2\2@B"+
		"\t\5\2\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2F"+
		"J\7\60\2\2GI\t\5\2\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2"+
		"LJ\3\2\2\2M\61\3\2\2\2M\65\3\2\2\2M?\3\2\2\2N\f\3\2\2\2OQ\7\17\2\2PO\3"+
		"\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\f\2\2ST\3\2\2\2TU\b\7\2\2U\16\3\2\2\2\r"+
		"\2\24$,/\65<CJMP\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
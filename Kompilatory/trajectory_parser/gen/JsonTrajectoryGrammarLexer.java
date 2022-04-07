// Generated from /media/dominik/dane/github/PWSZ/Kompilatory/trajectory_parser/src/utils/parsers/json_trajectory_grammar/JsonTrajectoryGrammar.g4 by ANTLR 4.9.2

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
public class JsonTrajectoryGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BREAKPOINT=1, WS=2, LIST_START=3, LIST_END=4, ROW_START=5, ROW_END=6, 
		INT=7, REAL=8, NEW_LINE=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "BREAKPOINT", "WS", "LIST_START", "LIST_END", "ROW_START", 
			"ROW_END", "INT", "REAL", "NEW_LINE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", null, "'['", "']'", "'{'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BREAKPOINT", "WS", "LIST_START", "LIST_END", "ROW_START", "ROW_END", 
			"INT", "REAL", "NEW_LINE"
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


	public JsonTrajectoryGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JsonTrajectoryGrammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13g\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\7\2\33\n\2\f\2\16\2\36\13\2\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\5\b/\n\b\3\t\3\t\3\t\7\t\64\n\t\f\t"+
		"\16\t\67\13\t\3\t\3\t\3\t\7\t<\n\t\f\t\16\t?\13\t\5\tA\n\t\3\n\3\n\7\n"+
		"E\n\n\f\n\16\nH\13\n\3\n\3\n\7\nL\n\n\f\n\16\nO\13\n\3\n\3\n\7\nS\n\n"+
		"\f\n\16\nV\13\n\3\n\3\n\7\nZ\n\n\f\n\16\n]\13\n\5\n_\n\n\3\13\5\13b\n"+
		"\13\3\13\3\13\3\13\3\13\2\2\f\3\2\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n"+
		"\25\13\3\2\6\4\2\f\f\17\17\4\2\13\f\"\"\3\2\63;\3\2\62;\2r\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\37\3\2\2\2\7!\3\2\2\2"+
		"\t%\3\2\2\2\13\'\3\2\2\2\r)\3\2\2\2\17.\3\2\2\2\21@\3\2\2\2\23^\3\2\2"+
		"\2\25a\3\2\2\2\27\34\7%\2\2\30\31\n\2\2\2\31\33\13\2\2\2\32\30\3\2\2\2"+
		"\33\36\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\4\3\2\2\2\36\34\3\2\2\2"+
		"\37 \7.\2\2 \6\3\2\2\2!\"\t\3\2\2\"#\3\2\2\2#$\b\4\2\2$\b\3\2\2\2%&\7"+
		"]\2\2&\n\3\2\2\2\'(\7_\2\2(\f\3\2\2\2)*\7}\2\2*\16\3\2\2\2+/\7\177\2\2"+
		",-\7\177\2\2-/\7.\2\2.+\3\2\2\2.,\3\2\2\2/\20\3\2\2\2\60A\7\62\2\2\61"+
		"\65\t\4\2\2\62\64\t\5\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65"+
		"\66\3\2\2\2\66A\3\2\2\2\67\65\3\2\2\289\7/\2\29=\t\4\2\2:<\t\5\2\2;:\3"+
		"\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>A\3\2\2\2?=\3\2\2\2@\60\3\2\2\2@"+
		"\61\3\2\2\2@8\3\2\2\2A\22\3\2\2\2B_\5\21\t\2CE\t\5\2\2DC\3\2\2\2EH\3\2"+
		"\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IM\7\60\2\2JL\t\5\2\2KJ\3"+
		"\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N_\3\2\2\2OM\3\2\2\2PT\7/\2\2QS\t"+
		"\5\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2W[\7"+
		"\60\2\2XZ\t\5\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\_\3\2\2\2"+
		"][\3\2\2\2^B\3\2\2\2^F\3\2\2\2^P\3\2\2\2_\24\3\2\2\2`b\7\17\2\2a`\3\2"+
		"\2\2ab\3\2\2\2bc\3\2\2\2cd\7\f\2\2de\3\2\2\2ef\b\13\2\2f\26\3\2\2\2\16"+
		"\2\34.\65=@FMT[^a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
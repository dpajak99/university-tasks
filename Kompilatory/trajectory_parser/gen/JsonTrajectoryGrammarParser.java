// Generated from /media/dominik/dane/github/PWSZ/Kompilatory/trajectory_parser/src/utils/parsers/json_trajectory_grammar/JsonTrajectoryGrammar.g4 by ANTLR 4.9.2

    package antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JsonTrajectoryGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BREAKPOINT=1, WS=2, LIST_START=3, LIST_END=4, ROW_START=5, ROW_END=6, 
		INT=7, REAL=8, NEW_LINE=9;
	public static final int
		RULE_json_trajectory_grammar = 0, RULE_trajectory_list = 1, RULE_trajectory_object = 2, 
		RULE_id = 3, RULE_fr = 4, RULE_x = 5, RULE_y = 6, RULE_z = 7, RULE_a = 8, 
		RULE_b = 9, RULE_angle = 10, RULE_color = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"json_trajectory_grammar", "trajectory_list", "trajectory_object", "id", 
			"fr", "x", "y", "z", "a", "b", "angle", "color"
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

	@Override
	public String getGrammarFileName() { return "JsonTrajectoryGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JsonTrajectoryGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Json_trajectory_grammarContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(JsonTrajectoryGrammarParser.EOF, 0); }
		public Trajectory_listContext trajectory_list() {
			return getRuleContext(Trajectory_listContext.class,0);
		}
		public Json_trajectory_grammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json_trajectory_grammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterJson_trajectory_grammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitJson_trajectory_grammar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitJson_trajectory_grammar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Json_trajectory_grammarContext json_trajectory_grammar() throws RecognitionException {
		Json_trajectory_grammarContext _localctx = new Json_trajectory_grammarContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_json_trajectory_grammar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIST_START) {
				{
				setState(24);
				trajectory_list();
				}
			}

			setState(27);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Trajectory_listContext extends ParserRuleContext {
		public TerminalNode LIST_START() { return getToken(JsonTrajectoryGrammarParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(JsonTrajectoryGrammarParser.LIST_END, 0); }
		public List<Trajectory_objectContext> trajectory_object() {
			return getRuleContexts(Trajectory_objectContext.class);
		}
		public Trajectory_objectContext trajectory_object(int i) {
			return getRuleContext(Trajectory_objectContext.class,i);
		}
		public Trajectory_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trajectory_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterTrajectory_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitTrajectory_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitTrajectory_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Trajectory_listContext trajectory_list() throws RecognitionException {
		Trajectory_listContext _localctx = new Trajectory_listContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_trajectory_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(LIST_START);
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ROW_START) {
				{
				{
				setState(30);
				trajectory_object();
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Trajectory_objectContext extends ParserRuleContext {
		public TerminalNode ROW_START() { return getToken(JsonTrajectoryGrammarParser.ROW_START, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<TerminalNode> BREAKPOINT() { return getTokens(JsonTrajectoryGrammarParser.BREAKPOINT); }
		public TerminalNode BREAKPOINT(int i) {
			return getToken(JsonTrajectoryGrammarParser.BREAKPOINT, i);
		}
		public FrContext fr() {
			return getRuleContext(FrContext.class,0);
		}
		public XContext x() {
			return getRuleContext(XContext.class,0);
		}
		public YContext y() {
			return getRuleContext(YContext.class,0);
		}
		public ZContext z() {
			return getRuleContext(ZContext.class,0);
		}
		public AContext a() {
			return getRuleContext(AContext.class,0);
		}
		public BContext b() {
			return getRuleContext(BContext.class,0);
		}
		public AngleContext angle() {
			return getRuleContext(AngleContext.class,0);
		}
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public TerminalNode ROW_END() { return getToken(JsonTrajectoryGrammarParser.ROW_END, 0); }
		public Trajectory_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trajectory_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterTrajectory_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitTrajectory_object(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitTrajectory_object(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Trajectory_objectContext trajectory_object() throws RecognitionException {
		Trajectory_objectContext _localctx = new Trajectory_objectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_trajectory_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(ROW_START);
			setState(39);
			id();
			setState(40);
			match(BREAKPOINT);
			setState(41);
			fr();
			setState(42);
			match(BREAKPOINT);
			setState(43);
			x();
			setState(44);
			match(BREAKPOINT);
			setState(45);
			y();
			setState(46);
			match(BREAKPOINT);
			setState(47);
			z();
			setState(48);
			match(BREAKPOINT);
			setState(49);
			a();
			setState(50);
			match(BREAKPOINT);
			setState(51);
			b();
			setState(52);
			match(BREAKPOINT);
			setState(53);
			angle();
			setState(54);
			match(BREAKPOINT);
			setState(55);
			color();
			setState(56);
			match(ROW_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(JsonTrajectoryGrammarParser.INT, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FrContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(JsonTrajectoryGrammarParser.INT, 0); }
		public FrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterFr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitFr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitFr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FrContext fr() throws RecognitionException {
		FrContext _localctx = new FrContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XContext extends ParserRuleContext {
		public TerminalNode REAL() { return getToken(JsonTrajectoryGrammarParser.REAL, 0); }
		public XContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_x; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitX(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitX(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XContext x() throws RecognitionException {
		XContext _localctx = new XContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_x);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YContext extends ParserRuleContext {
		public TerminalNode REAL() { return getToken(JsonTrajectoryGrammarParser.REAL, 0); }
		public YContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_y; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitY(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitY(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YContext y() throws RecognitionException {
		YContext _localctx = new YContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_y);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ZContext extends ParserRuleContext {
		public TerminalNode REAL() { return getToken(JsonTrajectoryGrammarParser.REAL, 0); }
		public ZContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_z; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterZ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitZ(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitZ(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ZContext z() throws RecognitionException {
		ZContext _localctx = new ZContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_z);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AContext extends ParserRuleContext {
		public TerminalNode REAL() { return getToken(JsonTrajectoryGrammarParser.REAL, 0); }
		public AContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_a; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterA(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitA(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitA(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AContext a() throws RecognitionException {
		AContext _localctx = new AContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_a);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BContext extends ParserRuleContext {
		public TerminalNode REAL() { return getToken(JsonTrajectoryGrammarParser.REAL, 0); }
		public BContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitB(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitB(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BContext b() throws RecognitionException {
		BContext _localctx = new BContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_b);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AngleContext extends ParserRuleContext {
		public TerminalNode REAL() { return getToken(JsonTrajectoryGrammarParser.REAL, 0); }
		public AngleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_angle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterAngle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitAngle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitAngle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AngleContext angle() throws RecognitionException {
		AngleContext _localctx = new AngleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_angle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColorContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(JsonTrajectoryGrammarParser.INT, 0); }
		public ColorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_color; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).enterColor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonTrajectoryGrammarListener ) ((JsonTrajectoryGrammarListener)listener).exitColor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JsonTrajectoryGrammarVisitor ) return ((JsonTrajectoryGrammarVisitor<? extends T>)visitor).visitColor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorContext color() throws RecognitionException {
		ColorContext _localctx = new ColorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_color);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13O\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\5\2\34\n\2\3\2\3\2\3\3\3\3\7\3\"\n\3\f\3\16\3%\13\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2D"+
		"\2\33\3\2\2\2\4\37\3\2\2\2\6(\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\f@\3\2\2\2"+
		"\16B\3\2\2\2\20D\3\2\2\2\22F\3\2\2\2\24H\3\2\2\2\26J\3\2\2\2\30L\3\2\2"+
		"\2\32\34\5\4\3\2\33\32\3\2\2\2\33\34\3\2\2\2\34\35\3\2\2\2\35\36\7\2\2"+
		"\3\36\3\3\2\2\2\37#\7\5\2\2 \"\5\6\4\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2"+
		"#$\3\2\2\2$&\3\2\2\2%#\3\2\2\2&\'\7\6\2\2\'\5\3\2\2\2()\7\7\2\2)*\5\b"+
		"\5\2*+\7\3\2\2+,\5\n\6\2,-\7\3\2\2-.\5\f\7\2./\7\3\2\2/\60\5\16\b\2\60"+
		"\61\7\3\2\2\61\62\5\20\t\2\62\63\7\3\2\2\63\64\5\22\n\2\64\65\7\3\2\2"+
		"\65\66\5\24\13\2\66\67\7\3\2\2\678\5\26\f\289\7\3\2\29:\5\30\r\2:;\7\b"+
		"\2\2;\7\3\2\2\2<=\7\t\2\2=\t\3\2\2\2>?\7\t\2\2?\13\3\2\2\2@A\7\n\2\2A"+
		"\r\3\2\2\2BC\7\n\2\2C\17\3\2\2\2DE\7\n\2\2E\21\3\2\2\2FG\7\n\2\2G\23\3"+
		"\2\2\2HI\7\n\2\2I\25\3\2\2\2JK\7\n\2\2K\27\3\2\2\2LM\7\t\2\2M\31\3\2\2"+
		"\2\4\33#";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
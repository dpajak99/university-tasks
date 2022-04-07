// Generated from /media/dominik/dane/github/PWSZ/Kompilatory/trajectory_parser/src/utils/parsers/json_trajectory_grammar/JsonTrajectoryGrammar.g4 by ANTLR 4.9.2

    package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JsonTrajectoryGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JsonTrajectoryGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#json_trajectory_grammar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJson_trajectory_grammar(JsonTrajectoryGrammarParser.Json_trajectory_grammarContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#trajectory_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrajectory_list(JsonTrajectoryGrammarParser.Trajectory_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#trajectory_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrajectory_object(JsonTrajectoryGrammarParser.Trajectory_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(JsonTrajectoryGrammarParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#fr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFr(JsonTrajectoryGrammarParser.FrContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#x}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitX(JsonTrajectoryGrammarParser.XContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#y}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitY(JsonTrajectoryGrammarParser.YContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#z}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZ(JsonTrajectoryGrammarParser.ZContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA(JsonTrajectoryGrammarParser.AContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(JsonTrajectoryGrammarParser.BContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#angle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAngle(JsonTrajectoryGrammarParser.AngleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JsonTrajectoryGrammarParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(JsonTrajectoryGrammarParser.ColorContext ctx);
}
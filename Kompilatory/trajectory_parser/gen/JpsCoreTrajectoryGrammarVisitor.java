// Generated from /media/dominik/dane/github/PWSZ/Kompilatory/trajectory_parser/src/utils/parsers/jps_core_trajectory_grammar/JpsCoreTrajectoryGrammar.g4 by ANTLR 4.9.2

    package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JpsCoreTrajectoryGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JpsCoreTrajectoryGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#jps_core_trajectory_grammar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJps_core_trajectory_grammar(JpsCoreTrajectoryGrammarParser.Jps_core_trajectory_grammarContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#trajectory_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrajectory_list(JpsCoreTrajectoryGrammarParser.Trajectory_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#trajectory_object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrajectory_object(JpsCoreTrajectoryGrammarParser.Trajectory_objectContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(JpsCoreTrajectoryGrammarParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#fr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFr(JpsCoreTrajectoryGrammarParser.FrContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#x}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitX(JpsCoreTrajectoryGrammarParser.XContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#y}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitY(JpsCoreTrajectoryGrammarParser.YContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#z}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZ(JpsCoreTrajectoryGrammarParser.ZContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA(JpsCoreTrajectoryGrammarParser.AContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(JpsCoreTrajectoryGrammarParser.BContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#angle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAngle(JpsCoreTrajectoryGrammarParser.AngleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JpsCoreTrajectoryGrammarParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(JpsCoreTrajectoryGrammarParser.ColorContext ctx);
}

// Grammar for Trajectory .traj file format
grammar JpsCoreTrajectoryGrammar;

@header {
    package antlr;
}

jps_core_trajectory_grammar: (trajectory_list) EOF;

/*
 * Lexer Rules
 */

fragment COMMENT    : '#' (~[\n\r] .)* ;

trajectory_list : (trajectory_object)*;

trajectory_object : id fr x y z a b angle color;

// Token
id      : INT;
fr      : INT;
x       : REAL;
y       : REAL;
z       : REAL;
a       : REAL;
b       : REAL;
angle   : REAL;
color   : INT;
BREAKPOINT: '\t' -> skip;
WS : [ \t\n] -> skip;

INT
 : '0'
 | [1-9] [0-9]*
 | '-' [1-9] [0-9]*
 ;


REAL
 : INT
 | [0-9]* '.' ([0-9])*
 | '-' [0-9]* '.' ([0-9])*
 ;

NEW_LINE : '\r'? '\n' -> skip;

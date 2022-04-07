
// Grammar for Trajectory .traj file format
grammar JsonTrajectoryGrammar;

@header {
    package antlr;
}

json_trajectory_grammar: (trajectory_list)? EOF;

/*
 * Lexer Rules
 */

fragment COMMENT    : '#' (~[\n\r] .)* ;

trajectory_list : LIST_START (trajectory_object)* LIST_END;

trajectory_object : ROW_START id BREAKPOINT fr BREAKPOINT x BREAKPOINT y BREAKPOINT z BREAKPOINT a BREAKPOINT b BREAKPOINT angle BREAKPOINT color ROW_END;

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
BREAKPOINT: ',';
WS : [ \t\n] -> skip;
LIST_START: '[';
LIST_END: ']';
ROW_START: '{';
ROW_END: '}' | '},';

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

# Linear Programming

1. Read in from text file the linear program's variables, objective function, and constraints
2. Create a standard form LP out of this information
3. Put the standard form LP into a tableau
4. Attempt to put the tableau into canonical form (implement Method of Artificial Variables and Subproblem Technique)
5. Return infeasible if so
6. Solve for a single optimal solution (using simplex algorithm as well as revised simplex algorithm, and smallest index rule)
7. Solve for multiple optimal solutions and optimal rays
8. Give optimal set

# Context Free Grammar for the User File:

LP -> var_list obj_fn constr_list

var_list -> var
          | var_list var
          
var -> VAR COLON ID /* non_neg */ SEMI

//non_neg -> e          Taking out non_neg for now
//         | GREQZERO

obj_fn -> OPT COLON linear_sum SEMI

linear_sum -> product
            | linear_sum ADD product

product -> pcoef MUL ID
         | pcoef ID
         | pcoef
         | ID

pcoef -> NEG coef
       | LPAR NEG coef RPAR
       | LPAR coef RPAR
       | coef

coef -> CONST DOT CONST
      | DOT CONST
      | CONST
      | CONST SLASH CONST

constr_list -> const
             | constr_list constr

const -> ST linear_sum COMP linear_sum SEMI

The scanner tokens are:
ID -> [a-zA-z][a-zA-z0-9_]*
VAR -> "var" | "variable"
//GREQZERO -> ">= 0" | ">=0" NOT RIGHT NOW
OPT -> "min" | "minimize" | "max" | "maximize"
NEG -> "-"
ADD -> "+" | "-"
MUL -> "*"
DOT -> "."
CONST -> [0-9]+
ST -> "st" | "s.t." | "subject to"
COMP -> "=" | "==" | ">=" | "<=" | "<" | ">"
SEMI -> ";"
ERR -> .
SCANEOF -> 0

Might need to create a yylval structure in the parser for the tokens

Rules used in grammar

expression  -> signed_term sum_op
sum_op      -> PLUSMINUS term sum_op
sum_op      -> EPSILON

signed_term -> PLUSMINUS term
signed_term -> term

term        -> factor term_op
term_op     -> MULTDIV signed_factor term_op
term_op     -> EPSILON

signed_factor -> PLUSMINUS factor
signed_factor -> factor

factor      -> argument factor_op
factor_op   -> RAISED signed_factor
factor_op   -> EPSILON

argument    -> value
argument    -> FUNCTION argument
argument    -> OPEN_BRACKET expression CLOSE_BRACKET

value       -> NUMBER
value       -> VARIABLE
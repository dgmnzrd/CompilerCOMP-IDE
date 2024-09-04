import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}


/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} {/*IGNORAR*/}

{Numero} { return textColor(yychar, yylength(), new Color(219, 157, 99)); }

/*Palabras reservadas*/
"IMPORT" | "import" | "Import" { return textColor( yychar, yylength(), new Color(205, 116, 232)); }
"DEF" | "def" | "Def" { return textColor( yychar, yylength(), new Color(240, 198, 120)); }
"CLASS" | "class" | "Class" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }

/* condicion */
"IF" | "if" | "If" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"ELSE" | "else" | "Else" { return textColor(yychar, yylength(), new Color(205, 116, 232));}

/* ciclos */
"FOR" | "for" | "For" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"IN" | "in" | "In" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }
"WHILE" | "while" | "While" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"RANGE" | "range" | "Range" { return textColor(yychar, yylength(), new Color(240, 198, 120));  }

"SELF" | "self" | "Self" {return textColor(yychar, yylength(), new Color(240, 198, 120));  }
"TRY" | "try" | "Try" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"EXCEPT" | "except" | "Except" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }
"RETURN" | "return" | "Return" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"BREAK" | "break" | "Break" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"NEXT" | "next" | "Next" { return textColor(yychar, yylength(), new Color(240, 198, 120));  }
"INPUT" | "input" | "Input" | "PRINT" | "print" | "Print" { return textColor(yychar, yylength(), new Color(240, 198, 120));  }

/* Tipos de dato */
"STRING" | "string" | "String" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }
"INT" | "int" | "Int" {return textColor(yychar, yylength(), new Color(205, 116, 232));}
"FLOAT" | "float" | "Float" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"BOOLEAN" | "boolean" | "Boolean" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }

"TRUE" | "true" | "True" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }
"FALSE" | "false" | "False" { return textColor(yychar, yylength(), new Color(205, 116, 232)); }

"POW" | "pow" | "Pow" { return textColor(yychar, yylength(), new Color(92, 179, 250)); }
"SQRT" | "sqrt" | "Sqrt" { return textColor(yychar, yylength(), new Color(92, 179, 250)); }
"AND" | "and" | "And" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }
"OR" | "or" | "Or" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }
"NOT" | "not" | "Not" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }
"BEGIN" | "begin" | "Begin" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }
"END" | "end" | "End" { return textColor(yychar, yylength(), new Color(240, 198, 120)); }


\( { return textColor(yychar, yylength(), new Color(171, 178, 191)); }
\) { return textColor(yychar, yylength(), new Color(171, 178, 191)); }
\{ { return textColor(yychar, yylength(), new Color(171, 178, 191)); }
\} { return textColor(yychar, yylength(), new Color(171, 178, 191)); }
\[ { return textColor(yychar, yylength(), new Color(171, 178, 191)); }
\] { return textColor(yychar, yylength(), new Color(171, 178, 191)); }
"++" { return textColor(yychar, yylength(), new Color(171, 178, 191)); }
"--" { return textColor(yychar, yylength(), new Color(171, 178, 191)); }

\" [a-zA-Z0-9_.-]* \" {return textColor(yychar, yylength(), new Color(154, 204, 118));}

/* IDs */
{Identificador} {return textColor(yychar, yylength(), new Color(171, 178, 191));}

. { /* Ignorar */ }
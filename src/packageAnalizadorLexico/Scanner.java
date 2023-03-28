package packageAnalizadorLexico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner
{
    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    private int linea = 1;

    private static final Map<String, TipoToken> palabrasReservadas;
    private static final Map<String, TipoToken> simbolos;
    static {
        // HashMap para palabras reservadas
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and", TipoToken.AND);
        palabrasReservadas.put("class", TipoToken.CLASS);
        palabrasReservadas.put("else", TipoToken.ELSE );
        palabrasReservadas.put("false", TipoToken.FALSE );
        palabrasReservadas.put("for", TipoToken.FOR );
        palabrasReservadas.put("function", TipoToken.FUNCTION ); //definir funciones
        palabrasReservadas.put("if", TipoToken.IF );
        palabrasReservadas.put("nulL", TipoToken.NULL );
        palabrasReservadas.put("or", TipoToken.OR );
        palabrasReservadas.put("println", TipoToken.PRINTLN );
        palabrasReservadas.put("return", TipoToken.RETURN );
        palabrasReservadas.put("super", TipoToken.SUPER );
        palabrasReservadas.put("this", TipoToken.THIS );
        palabrasReservadas.put("true", TipoToken.TRUE );
        palabrasReservadas.put("var", TipoToken.VAR ); //definir variables
        palabrasReservadas.put("while", TipoToken.WHILE );
        // HashMap para simbolos
        simbolos.put("(", TipoToken.PARENTESIS_ABRE );
        simbolos.put(")", TipoToken.PARENTESIS_CIERRA );
        simbolos.put("{", TipoToken.LLAVE_ABRE );
        simbolos.put("}", TipoToken.LLAVE_CIERRA );
        simbolos.put(",", TipoToken.COMA );
        simbolos.put(".", TipoToken.PUNTO );
        simbolos.put(";", TipoToken.PUNTOYCOMA );
        simbolos.put("-", TipoToken.MENOS );
        simbolos.put("+", TipoToken.MAS );
        simbolos.put("*", TipoToken.ASTERISCO );
        simbolos.put("/", TipoToken.DIAGONAL );
        simbolos.put("!", TipoToken.ADMIRACION );
        simbolos.put("!=", TipoToken.DIFERENTE );
        simbolos.put("=", TipoToken.ASIGNACION );
        simbolos.put("==", TipoToken.IGUAL );
        simbolos.put("<", TipoToken.MENOR );
        simbolos.put("<=", TipoToken.MENORIGUAL );
        simbolos.put(">", TipoToken.MAYOR );
        simbolos.put(">=", TipoToken.MAYORIGUAL );
    }

    Scanner(String source){
        this.source = source;
    }

    List<Token> scanTokens(){
        //Aquí va el corazón del scanner.

        /*
        Analizar el texto de entrada para extraer todos los tokens
        y al final agregar el token de fin de archivo
         */
        tokens.add(new Token(TipoToken.EOF, "", null, linea));

        return tokens;
    }
}

/*
Signos o símbolos del lenguaje:
(
)
{
}
,
.
;
-
+
*
/
!
!=
=
==
<
<=
>
>=
// -> comentarios (no se genera token)
/* ... * / -> comentarios (no se genera token)
Identificador,
Cadena
Numero
Cada palabra reservada tiene su nombre de token

 */

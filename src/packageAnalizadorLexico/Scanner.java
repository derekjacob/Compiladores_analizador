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
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("clase", TipoToken.CLASE);
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

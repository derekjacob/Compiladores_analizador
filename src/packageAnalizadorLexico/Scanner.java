package packageAnalizadorLexico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

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
        palabrasReservadas.put("function", TipoToken.FUNCTION );
        palabrasReservadas.put("if", TipoToken.IF );
        palabrasReservadas.put("nulL", TipoToken.NULL );
        palabrasReservadas.put("or", TipoToken.OR );
        palabrasReservadas.put("println", TipoToken.PRINTLN );
        palabrasReservadas.put("return", TipoToken.RETURN );
        palabrasReservadas.put("super", TipoToken.SUPER );
        palabrasReservadas.put("this", TipoToken.THIS );
        palabrasReservadas.put("true", TipoToken.TRUE );
        palabrasReservadas.put("var", TipoToken.VAR );
        palabrasReservadas.put("while", TipoToken.WHILE );
        // HashMap para simbolos
        simbolos = new HashMap<>();
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
        // La linea leida es separada en caracteres.
        int posicion = 0;
        String aux = "", aux2;
        String[] caracteres = source.split("|");
        // Analisis de la linea leida caracter por caracter.
        while(posicion < caracteres.length){
            aux2 = aux + caracteres[posicion];
            // Comprobación si la cadena almacenada en el auxiliar es una palabra reservada.
            if(palabrasReservadas.containsKey(aux)){
                tokens.add(new Token(palabrasReservadas.get(aux),aux,null,linea));
                aux = "";
            }     
            // Comprobacion si el caracter actual es un simbolo.
            if(simbolos.containsKey(caracteres[posicion])){
                // Comprobacion si el caracter leido y el siguiente forman un simbolo compuesto.
                if(caracteres[posicion] + caracteres[posicion+1] == "!="){
                    tokens.add(new Token(simbolos.get("!="),"!=",null,linea));
                }
                // Comprobacion si el caracter leido y el siguiente forman un simbolo compuesto.
                else if(caracteres[posicion] + caracteres[posicion+1] == "=="){
                    tokens.add(new Token(simbolos.get("=="),"==",null,linea));
                }
                // Comprobacion si el caracter leido y el siguiente forman un simbolo compuesto.
                else if(caracteres[posicion] + caracteres[posicion+1] == "<="){
                    tokens.add(new Token(simbolos.get("<="),"<=",null,linea));
                }
                // Comprobacion si el caracter leido y el siguiente forman un simbolo compuesto.
                else if(caracteres[posicion] + caracteres[posicion+1] == ">="){
                    tokens.add(new Token(simbolos.get(">="),">=",null,linea));
                }
                else{
                    tokens.add(new Token(simbolos.get(caracteres[posicion]),caracteres[posicion],null,linea));
                }
            }
            // Auxiliar utilizado para almacenar lo analizado hasta el momento, se reiniciará cuando se detecte un token.
            
            
            // Fin de la comprobación.
            posicion++;
            aux = aux2;
        }
        linea++;
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

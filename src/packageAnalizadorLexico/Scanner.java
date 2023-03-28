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
    private static final Map<String, TipoToken> simbolosDobles;
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
        simbolos.put("=", TipoToken.ASIGNACION );
        simbolos.put("<", TipoToken.MENOR );
        simbolos.put(">", TipoToken.MAYOR );
        // HashMap para simbolos de dos digitos.
        simbolosDobles = new HashMap<>();
        simbolosDobles.put("!=", TipoToken.DIFERENTE );
        simbolosDobles.put("==", TipoToken.IGUAL );
        simbolosDobles.put("<=", TipoToken.MENORIGUAL );
        simbolosDobles.put(">=", TipoToken.MAYORIGUAL );
    }

    Scanner(String source){
        this.source = source;
    }

    List<Token> scanTokens(){
        int posicion = 0;
        // Lista para detectar los numeros
        List<String> numeros = new ArrayList<>();
        numeros.add("0");
        numeros.add("1");
        numeros.add("2");
        numeros.add("3");
        numeros.add("4");
        numeros.add("5");
        numeros.add("6");
        numeros.add("7");
        numeros.add("8");
        numeros.add("9");
        String aux = "", aux2 = "";
        // La linea leida es separada en caracteres.
        String[] caracteres = source.split("|");
        // Analisis de la linea leida caracter por caracter.
        while(posicion < caracteres.length){
            aux2 = aux + caracteres[posicion];
            // Comprobacion sobre el primer caracter leido despues de un token.
            if(aux == "" && numeros.contains(caracteres[posicion])){
                while(numeros.contains(caracteres[posicion])){
                    posicion++;
                }
                tokens.add(new Token(TipoToken.NUMERO, "Numero", null, linea));
            }
            // Comprobación si la cadena almacenada en el auxiliar es una palabra reservada.
            else if(palabrasReservadas.containsKey(aux)){
                tokens.add(new Token(palabrasReservadas.get(aux),aux,null,linea));
                aux2 = "";
            }
            // Comprobacion si el caracter actual es un simbolo.
            else if(simbolos.containsKey(caracteres[posicion])){
                tokens.add(new Token(simbolos.get(caracteres[posicion]),caracteres[posicion],null,linea));
                aux2 = "";
            }
        }
            // Auxiliar utilizado para almacenar lo analizado hasta el momento, se reiniciará cuando se detecte un token.
            // Fin de la comprobación.
            posicion++;
            aux = aux2;
        
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

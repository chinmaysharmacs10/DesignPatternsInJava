package Interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token{
    public enum Type{
        INTEGER,
        PLUS,
        MINUS,
        LEFT_PAREN,
        RIGHT_PAREN
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

public class Lexing {
    static List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        for (int i=0; i<input.length(); i++) {
            switch (input.charAt(i)) {
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LEFT_PAREN, "("));
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RIGHT_PAREN, ")"));
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder("" + input.charAt(i));
                    for (int j=i+1; j<input.length(); j++) {
                        if(Character.isDigit(input.charAt(j))) {
                            stringBuilder.append(input.charAt(j));
                            i++;
                        } else {
                            tokens.add(new Token(Token.Type.INTEGER, stringBuilder.toString()));
                            break;
                        }
                    }
                    break;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        String input = "(13+14)-(12+1)";
        List<Token> tokens = lex(input);

//        for (Token token : tokens) {
//            System.out.println(token);
//        }

        System.out.println(tokens.stream().map(Token::toString).collect(Collectors.joining("\t")));
    }
}

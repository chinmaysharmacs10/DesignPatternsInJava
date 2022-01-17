package Interpreter;

import java.util.List;
import java.util.stream.Collectors;

import static Interpreter.Lexing.lex;

interface Element{
    int eval();
}

class Integer implements Element{
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element{
    public enum Type{
        ADDITION,
        SUBTRACTION
    }

    public Type type;
    public Element left, right;

    @Override
    public int eval() {
        switch (type) {
            case ADDITION:
                return left.eval() + right.eval();
            case SUBTRACTION:
                return left.eval() - right.eval();
            default:
                return 0;
        }
    }
}

public class Parsing {
    static Element parse(List<Token> tokens) {
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;

        for (int i=0; i<tokens.size(); i++) {
            Token token = tokens.get(i);
            switch (token.type) {
                case INTEGER:
                    Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
                    if(!haveLHS) {
                        result.left = integer;
                        haveLHS = true;
                    } else {
                        result.right = integer;
                    }
                    break;
                case PLUS:
                    result.type = BinaryOperation.Type.ADDITION;
                    break;
                case MINUS:
                    result.type = BinaryOperation.Type.SUBTRACTION;
                    break;
                case LEFT_PAREN:
                    int j = i;  // location of right parenthesis
                    for (; j<tokens.size(); j++) {
                        if(tokens.get(j).type == Token.Type.RIGHT_PAREN) {
                            break;
                        }
                    }
                    List<Token> subexpression = tokens.stream().skip(i+1).limit(j-i-1).collect(Collectors.toList());
                    Element element =  parse(subexpression);
                    if(!haveLHS){
                        result.left = element;
                        haveLHS = true;
                    } else {
                        result.right = element;
                    }
                    i = j++;
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "(13+4)-(12+11)";
        List<Token> tokens = lex(input);
        System.out.println(tokens.stream().map(Token::toString).collect(Collectors.joining("\t")));

        Element parsed = parse(tokens);
        System.out.println(input + " = " + parsed.eval());
    }
}

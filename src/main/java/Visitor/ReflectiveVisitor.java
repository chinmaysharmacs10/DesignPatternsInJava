//package Visitor;
//
//// Code is very slow because of the if condition checking class
//// type is checked on each call
//class ExpressionPrinter {
//    public static void print(Expression expression, StringBuilder stringBuilder) {
//        if(expression.getClass() == DoubleExpression.class) {
//            stringBuilder.append(((DoubleExpression) expression).value);
//        } else if (expression.getClass() == AdditionExpression.class) {
//            AdditionExpression additionExpression = (AdditionExpression) expression;
//            stringBuilder.append('(');
//            print(additionExpression.left, stringBuilder);
//            stringBuilder.append('+');
//            print(additionExpression.right, stringBuilder);
//            stringBuilder.append(')');
//        }
//    }
//}
//
//public class ReflectiveVisitor {
//    public static void main(String[] args) {
//        AdditionExpression expression = new AdditionExpression(
//                new DoubleExpression(1),
//                new AdditionExpression(
//                        new DoubleExpression(2),
//                        new DoubleExpression(3)
//                )
//        );
//
//        StringBuilder stringBuilder = new StringBuilder();
//        ExpressionPrinter.print(expression, stringBuilder);
//        System.out.println(stringBuilder);
//    }
//}

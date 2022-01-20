//package Visitor;
//
//abstract class Expression {
//    public abstract void print(StringBuilder stringBuilder);
//}
//
//class DoubleExpression extends Expression {
//    public double value;
//
//    public DoubleExpression(double value) {
//        this.value = value;
//    }
//
//    @Override
//    public void print(StringBuilder stringBuilder) {
//        stringBuilder.append(value);
//    }
//}
//
//class AdditionExpression extends Expression {
//    public Expression left, right;
//
//    public AdditionExpression(Expression left, Expression right) {
//        this.left = left;
//        this.right = right;
//    }
//
//    @Override
//    public void print(StringBuilder stringBuilder) {
//        stringBuilder.append('(');
//        left.print(stringBuilder);
//        stringBuilder.append('+');
//        right.print(stringBuilder);
//        stringBuilder.append(')');
//    }
//}
//
//// Intrusive visitor violates SIP and OCP
//public class IntrusiveVisitor {
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
//        expression.print(stringBuilder);
//        System.out.println(stringBuilder);
//    }
//}

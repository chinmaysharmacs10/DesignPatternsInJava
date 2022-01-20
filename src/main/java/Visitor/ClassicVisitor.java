//package Visitor;
//
//interface ExpressionVisitor {
//    void visit(NewDoubleExpression doubleExpression);
//    void visit(NewAdditionExpression additionExpression);
//}
//
//abstract class NewExpression {
//    public abstract void accept(ExpressionVisitor expressionVisitor);
//}
//
//class NewDoubleExpression extends NewExpression {
//    public double value;
//
//    public NewDoubleExpression(double value) {
//        this.value = value;
//    }
//
//    @Override
//    public void accept(ExpressionVisitor expressionVisitor) {
//        expressionVisitor.visit(this);
//    }
//}
//
//class NewAdditionExpression extends NewExpression {
//    public NewExpression left, right;
//
//    public NewAdditionExpression(NewExpression left, NewExpression right) {
//        this.left = left;
//        this.right = right;
//    }
//
//    @Override
//    public void accept(ExpressionVisitor expressionVisitor) {
//        expressionVisitor.visit(this);
//    }
//}
//
//class NewExpressionPrinter implements ExpressionVisitor {
//    private StringBuilder stringBuilder = new StringBuilder();
//
//    @Override
//    public void visit(NewDoubleExpression doubleExpression) {
//        stringBuilder.append(doubleExpression.value);
//    }
//
//    @Override
//    public void visit(NewAdditionExpression additionExpression) {
//        stringBuilder.append('(');
//        additionExpression.left.accept(this);
//        stringBuilder.append('+');
//        additionExpression.right.accept(this);
//        stringBuilder.append(')');
//    }
//
//    @Override
//    public String toString() {
//        return stringBuilder.toString();
//    }
//}
//
//class ExpressionCalculator implements ExpressionVisitor {
//    public double result;
//
//    @Override
//    public void visit(NewDoubleExpression doubleExpression) {
//        result = doubleExpression.value;
//    }
//
//    @Override
//    public void visit(NewAdditionExpression additionExpression) {
//        additionExpression.left.accept(this);
//        double a = result;
//        additionExpression.right.accept(this);
//        double b = result;
//        result = a + b;
//    }
//}
//
//// Double dispatch
//public class ClassicVisitor {
//    public static void main(String[] args) {
//        NewAdditionExpression expression = new NewAdditionExpression(
//                new NewDoubleExpression(1),
//                new NewAdditionExpression(
//                        new NewDoubleExpression(2),
//                        new NewDoubleExpression(3)
//                )
//        );
//
//        NewExpressionPrinter expressionPrinter = new NewExpressionPrinter();
//        expressionPrinter.visit(expression);
//        System.out.println(expressionPrinter);
//
//        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
//        expressionCalculator.visit(expression);
//        System.out.println(expressionPrinter + " = " + expressionCalculator.result);
//    }
//}

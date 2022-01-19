package Visitor;

interface Visitor {}

interface ExpressionVisitor extends Visitor {
    void visit (Expression expression);
}

interface DoubleExpressionVisitor extends Visitor {
    void visit (DoubleExpression doubleExpression);
}

interface AdditionExpressionVisitor extends Visitor {
    void visit (AdditionExpression additionExpression);
}

abstract class Expression {
    public void accept(Visitor visitor) {
        if(visitor instanceof ExpressionVisitor) {
            ((ExpressionVisitor)visitor).visit(this);
        }
    }
}

class DoubleExpression extends Expression {
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        if(visitor instanceof DoubleExpressionVisitor) {
            ((DoubleExpressionVisitor)visitor).visit(this);
        }
    }
}

class AdditionExpression extends Expression {
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(Visitor visitor) {
        if(visitor instanceof AdditionExpressionVisitor) {
            ((AdditionExpressionVisitor)visitor).visit(this);
        }
    }
}

class ExpressionPrinter implements DoubleExpressionVisitor, AdditionExpressionVisitor {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void visit(DoubleExpression doubleExpression) {
        stringBuilder.append(doubleExpression.value);
    }

    @Override
    public void visit(AdditionExpression additionExpression) {
        stringBuilder.append('(');
        additionExpression.left.accept(this);
        stringBuilder.append('+');
        additionExpression.right.accept(this);
        stringBuilder.append(')');
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}

public class AcyclicVisitor {
    public static void main(String[] args) {
        AdditionExpression expression = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                )
        );

        ExpressionPrinter expressionPrinter = new ExpressionPrinter();
        expressionPrinter.visit(expression);
        System.out.println(expressionPrinter);
    }
}

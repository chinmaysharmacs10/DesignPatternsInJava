package Decorator;

interface Shape{
    public String info();
}

class Circle implements Shape{
    private double radius;

    public Circle(){}

    public Circle(double radius){
        this.radius = radius;
    }

    public void resizeCircle(double factor){
        radius = radius * factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square implements Shape{
    private double side;

    public Square(){}

    public Square(double side){
        this.side = side;
    }

    @Override
    public String info() {
        return "A square of side " + side;
    }
}

class ColouredShape implements Shape{
    private Shape shape;
    private String colour;

    public ColouredShape(Shape shape, String colour){
        this.shape = shape;
        this.colour = colour;
    }

    @Override
    public String info() {
        return shape.info() + " of " + colour + " colour";
    }
}

class TransparencyShape implements Shape{
    private Shape shape;
    private double transparency;

    public TransparencyShape(Shape shape, double transparency){
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has transparency of " + transparency;
    }
}


// Instead of modifying the original object, you use Decorators
// which take object in their constructor and make a private variable
// then they provide additional information or functionality
public class DynamicDecorator {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColouredShape colouredCircle = new ColouredShape(new Circle(10), "red");
        System.out.println(colouredCircle.info());

        Square square = new Square(5);
        System.out.println(square.info());

        TransparencyShape transparencySquare = new TransparencyShape(new Square(5), 1.5);
        System.out.println(transparencySquare.info());

        // here TransparencyShape calls shape.info() for ColouredShape
        // then ColouredShape calls shape.info() for Circle
        TransparencyShape transparencyColouredCircle = new TransparencyShape(
                new ColouredShape(new Circle(3), "blue"), 2.5
        );
        System.out.println(transparencyColouredCircle.info());
    }
}

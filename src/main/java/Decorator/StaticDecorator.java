package Decorator;

import java.util.function.Supplier;

class ColouredShape1<T extends Shape> implements Shape{
    private Shape shape;
    private String colour;

    public ColouredShape1(Supplier<? extends  T> constructor, String colour){
        shape = constructor.get();
        this.colour = colour;
    }

    @Override
    public String info() {
        return shape.info() + " has the colour " + colour;
    }
}

class TransparencyShape1<T extends Shape> implements Shape{
    private Shape shape;
    private double transparency;

    public TransparencyShape1(Supplier<? extends T> constructor, Double transparency){
        shape = constructor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has transparency of " + transparency;
    }
}

public class StaticDecorator {
    public static void main(String[] args) {
        ColouredShape1<Circle> colouredCircle = new ColouredShape1<>(
                () -> new Circle(10),
                "red"
        );
        System.out.println(colouredCircle.info());

        TransparencyShape1<ColouredShape1<Square>> transparencyColouredSquare = new TransparencyShape1<>(
                () -> new ColouredShape1(
                        () -> new Square(5),
                        "green"
                ),
                1.5
        );
        System.out.println(transparencyColouredSquare.info());
    }
}

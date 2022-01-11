package Composite;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
class GraphicObject{
    protected String name = "Group";
    public String color;
    public List<GraphicObject> children = new ArrayList<>();  // for a group of objects

    public GraphicObject(){

    }

    private void print(StringBuilder stringBuilder, int depth){
        stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());

        for(GraphicObject child : children){
            child.print(stringBuilder, depth+1);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        print(stringBuilder, 0);
        return stringBuilder.toString();
    }
}

class Circle extends GraphicObject{
    public Circle(String color){
        name = "Circle";
        this.color = color;
    }
}

class Square extends GraphicObject{
    public Square(String color){
        name = "Sqaure";
        this.color = color;
    }
}

public class GeometricShapes {
    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new Square("Red"));
        drawing.children.add(new Circle("Green"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Circle("Blue"));
        group.children.add(new Square("Blue"));
        drawing.children.add(group);

        System.out.println(drawing);
    }
}

package Adapter;

import java.util.*;
import java.util.function.Consumer;

class Point{
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Adapter.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // for caching --> preventing same point being created multiple times
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class Line{
    public Point start, end;

    public Line(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(start, line.start) && Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}

class VectorObject extends ArrayList<Line>{

}

class RectangleObject extends VectorObject{
    public RectangleObject(int x, int y, int width, int height){
        add(new Line(new Point(x,y), new Point(x+width,y)));
        add(new Line(new Point(x+width,y), new Point(x+width,y+height)));
        add(new Line(new Point(x,y), new Point(x,y+height)));
        add(new Line(new Point(x,y+height), new Point(x+width,y+height)));
    }
}

// Need of Adapter --> To convert Adapter.RectangleObject to Adapter.Point
// Adapter needs to take in a Adapter.Line and convert it to a bunch of Points
class LineToPointAdapter implements Iterable<Point>{       // earlier this was extends ArrayList<Adapter.Point>

    private static int pointCount = 0;
    private static HashMap<Integer, List<Point>> cache = new HashMap<>();
    private int hashCode;

    public LineToPointAdapter(Line line){

        hashCode = line.hashCode();
        if (cache.get(hashCode) != null) {
            return;
        }

        System.out.println(String.format("%d: Generating Points for line [%d,%d]-[%d,%d] (no caching)",
                ++pointCount, line.start.x, line.start.y, line.end.x, line.end.y));

        ArrayList<Point> points = new ArrayList<>();

        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);

        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0) {
            for (int y = top; y <= bottom; y++) {
                points.add(new Point(left,y));
            }
        } else if (dy == 0) {
            for (int x = left; x <= right; x++) {
                points.add(new Point(x, top));
            }
        }

        cache.put(hashCode, points);
    }

    @Override
    public Iterator<Point> iterator() {
        return cache.get(hashCode).iterator();
    }

    @Override
    public void forEach(Consumer<? super Point> action) {
        cache.get(hashCode).forEach(action);
    }

    @Override
    public Spliterator<Point> spliterator() {
        return cache.get(hashCode).spliterator();
    }
}

public class VectorAdapter {

    private final static List<VectorObject> vectorObjects =
            new ArrayList<>(Arrays.asList(
                    new RectangleObject(1,1,10,10),
                    new RectangleObject(3,3,6,6)
            ));

    public static void drawPoint(Point point){
        System.out.println(".");
    }

    public static void draw(){
        for (VectorObject vectorObject : vectorObjects) {
            for (Line line : vectorObject) {
                LineToPointAdapter lineToPointAdapter = new LineToPointAdapter(line);
                lineToPointAdapter.forEach(VectorAdapter::drawPoint);
            }
        }
    }

    public static void main(String[] args) {
        draw();

        // To check caching call draw method more than once
        draw();
    }
}

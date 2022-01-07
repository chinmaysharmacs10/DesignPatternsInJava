package Factory;

class Point{

    private double a, b;

    private Point(double a, double b){
        this.a = a;
        this.b = b;
    }

    /*public static Factory.Point cartesianPoint(double x, double y){
        return new Factory.Point(x,y);
    }

    public static Factory.Point polarPoint(double rho, double theta){
        return new Factory.Point(rho*Math.cos(theta), rho*Math.sin(theta));
    }*/

    public static class Factory{
        public static Point cartesianPoint(double x, double y){
            return new Point(x,y);
        }

        public static Point polarPoint(double rho, double theta){
            return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
        }
    }
}

public class FactoryMethods {
    public static void main(String[] args) {
        Point point = Point.Factory.polarPoint(2,4);
        System.out.println("point = " + point);
    }
}

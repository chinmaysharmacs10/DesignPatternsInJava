package Proxy;

interface Driveable{
    public void drive();
}

class Driver{
    public int age;

    public Driver(int age){
        this.age = age;
    }
}

// Car is a resource
class Car implements Driveable{
    public Driver driver;

    public Car(Driver driver){
        this.driver = driver;
    }

    @Override
    public void drive() {

    }
}

// CarProxy is a protection proxy for the resource Car
class CarProxy extends Car{

    public CarProxy(Driver driver){
        super(driver);
    }

    @Override
    public void drive() {
        if(driver.age >= 16) {
            super.drive();
        } else {
            System.out.println("Driver too young!");
        }
    }
}

// A protection proxy controls access to a particular resource
public class ProtectionProxy {
    public static void main(String[] args) {
        // Car car = new Car(new Driver(10));
        Car car = new CarProxy(new Driver(10));
        car.drive();
    }
}

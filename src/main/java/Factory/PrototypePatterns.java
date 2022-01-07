package Factory;

import java.util.Arrays;

class Address implements Cloneable {
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber){
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    // Copy Constructor --> better way
    public Address(Address other){
        this(other.streetName, other.houseNumber);
    }

    @Override
    public String toString() {
        return "Factory.Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new Address(streetName, houseNumber);
    }
}

class Person implements Cloneable{
    public String[] names;
    public Address address;

    public Person(String[] names, Address address){
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Factory.Person{" +
                "names=" + Arrays.toString(names) +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(names.clone(), (Address) address.clone());
    }
}

public class PrototypePatterns {
    public static void main(String[] args) throws CloneNotSupportedException{
        Person person = new Person(new String[]{"Chinmay","Sharma"}, new Address("Patna",10));

        // Factory.Person neighbour = person; --> This will do just a shallow copy
        Person neighbour = (Person) person.clone(); // This does a deep copy

        neighbour.names[0] = "Manaswi";
        neighbour.address.houseNumber = 20;
        neighbour.address.streetName = "Bihar";

        System.out.println(person);
        System.out.println(neighbour);

    }
}

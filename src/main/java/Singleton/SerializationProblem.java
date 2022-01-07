package Singleton;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

// When object is deserialized then the JVM creates new instance
// this defeats the point of Singleton.Singleton
@Getter
@Setter
class Singleton implements Serializable{
    private Singleton(){}

    private static final Singleton INSTANCE = new Singleton();

    public static Singleton getInstance(){
        return INSTANCE;
    }

    private int val = 0;

    // tells JVM that whenever serialization happens it happens wrt this object
    protected Object readResolve(){
        return INSTANCE;
    }
}

public class SerializationProblem {

    // Serialization
    public static void saveToFile(Singleton singleton, String fileName) throws Exception{
        try(FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)){
            out.writeObject(singleton);
        }
    }

    // Deserialization
    public static Singleton readFromFile(String fileName) throws Exception{
        try(FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (Singleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception{
        Singleton singleton = Singleton.getInstance();
        singleton.setVal(100);

        saveToFile(singleton,"singleton.bin");

        singleton.setVal(200);

        Singleton singleton2 = readFromFile("singleton.bin");

        System.out.println(singleton == singleton2);
        System.out.println(singleton.getVal());
        System.out.println(singleton2.getVal());
    }
}

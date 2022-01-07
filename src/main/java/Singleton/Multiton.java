package Singleton;

import java.util.HashMap;

enum Subsystem{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer{

    private static int instanceCount = 0;
    private Printer(){
        instanceCount++;
        System.out.println("instanceCount = " + instanceCount);
    }

    private static final HashMap<Subsystem, Printer> instances = new HashMap<>();

    public static Printer getInstance(Subsystem subsystem){
        if(!instances.containsKey(subsystem)){
            Printer instance = new Printer();
            instances.put(subsystem, instance);
        }
        return instances.get(subsystem);
    }
}

// Singleton ensures only one instance of a class
// Multiton ensure that there's only a finite set of instances of a class
public class Multiton {
    public static void main(String[] args) {
        Printer primaryPrinter = Printer.getInstance(Subsystem.PRIMARY);
        Printer auxiliaryPrinter = Printer.getInstance(Subsystem.AUXILIARY);
        Printer auxiliaryPrinter2 = Printer.getInstance(Subsystem.AUXILIARY);
        Printer fallbackPrinter = Printer.getInstance(Subsystem.FALLBACK);
        Printer fallbackPrinter2 = Printer.getInstance(Subsystem.FALLBACK);
    }
}

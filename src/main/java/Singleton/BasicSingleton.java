package Singleton;

import lombok.Getter;
import lombok.Setter;

// this class enforces the rule that there should be only 1 instance of this class
@Getter
@Setter
public class BasicSingleton {
    private BasicSingleton(){}

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    // If singleton throws some exception then INSTANCE is to be made this way
    // private Singleton.BasicSingleton() throws Exception{}
    /* static {
        try{
            INSTANCE = new Singleton.BasicSingleton();
        } catch (Exception e){
            System.out.println("Some Exception Occurred!");
        }
    } */

    public static BasicSingleton getInstance(){
        return INSTANCE;
    }

    private int val = 0;

}

class Demo{
    public static void main(String[] args) {
        BasicSingleton basicSingleton = BasicSingleton.getInstance();
        basicSingleton.setVal(1000);
        System.out.println("basicSingleton.getVal() = " + basicSingleton.getVal());
    }
}

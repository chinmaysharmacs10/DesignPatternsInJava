package Singleton;

public class LazySingleton {

    private static LazySingleton INSTANCE;

    private LazySingleton(){
        System.out.println("Created Lazy Singleton");
    }

    public static synchronized LazySingleton getInstance(){
        // this can cause race condition
        // separate threads check that INSTANCE is null and instantiate separately
        // therefore synchronized is used for Thread Safety
        if(INSTANCE == null){
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }

    // double-checked locking
    /* public static Singleton.LazySingleton getInstance(){
        if(INSTANCE == null){
            synchronized (Singleton.LazySingleton.class){
                if(INSTANCE == null){
                    INSTANCE = new Singleton.LazySingleton();
                }
            }
        }
        return INSTANCE;
    } */
}


// For thread safety
class InnerStaticSingleton{
    private InnerStaticSingleton(){}

    private static class Implementation{
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance(){
        return Implementation.INSTANCE;
    }
}

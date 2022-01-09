package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human{
    public void walk();
    public void talk();
}

class Person implements Human{
    @Override
    public void walk() {
        System.out.println("I am walking");
    }

    @Override
    public void talk() {
        System.out.println("I am talking");
    }
}

class LoggingHandler implements InvocationHandler{

    private final Object target;
    private Map<String, Integer> calls = new HashMap<>();

    public LoggingHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.contains("toString")) {
            return calls.toString();
        }
        calls.merge(methodName, 1, Integer::sum);
        return method.invoke(target, args);
    }
}

public class DynamicProxy {

    // utility function to create a dynamic proxy with logging for any object
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf){
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[] { itf },
                new LoggingHandler(target)
        );
    }

    public static void main(String[] args) {
        Person person = new Person();
        Human logged = withLogging(person, Human.class);
        logged.talk();
        logged.walk();
        logged.walk();
        System.out.println(logged);
    }
}

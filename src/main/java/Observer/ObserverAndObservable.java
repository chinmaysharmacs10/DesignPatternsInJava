package Observer;

import java.util.ArrayList;
import java.util.List;

class PropertyChangedEventArgs<T>{
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

// Observer --> interface that is to be implemented by anyone interested in observing an object of type T
interface Observer<T>{
    void handle(PropertyChangedEventArgs<T> args);
}

// Observable --> object on which you can look at
class Observable<T>{
    public List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    protected void propertyChanged(T source, String propertyName, Object newValue){
        for(Observer<T> observer : observers) {
            observer.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
        }
    }
}

class Person extends Observable<Person>{
    public int age;

    public void setAge(int age) {
        if(this.age == age) return;
        this.age = age;
        propertyChanged(this, "age", age);
    }

    public int getAge() {
        return age;
    }
}

public class ObserverAndObservable implements Observer<Person>{
    public static void main(String[] args) {
        new ObserverAndObservable();
    }

    public ObserverAndObservable() {
        Person person = new Person();
        person.subscribe(this);
        for (int i=20; i<25; i++) {
            person.setAge(i);
        }
    }

    @Override
    public void handle(PropertyChangedEventArgs<Person> args) {
        System.out.println("Person's " + args.propertyName + " has changed to " + args.newValue);
    }
}

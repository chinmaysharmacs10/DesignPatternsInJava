package Proxy;

import java.util.Objects;

class Property<T>{
    public T value;

    public Property(T value){
        this.value = value;
    }

    // getter
    public T getValue(){
        return value;
    }

    // setter
    public void setValue(T val){
        // can add logging info here, so we can track when value was modified
        value = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property<?> property = (Property<?>) o;
        return Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

class Creature{
    public Property<Integer> agility = new Property<>(10);

    public int getAgility(){
        return agility.getValue();
    }

    public void setAgility(int value){
        agility.setValue(value);
    }
}

public class PropertyProxy {
    public static void main(String[] args) {
        Creature creature = new Creature();
        System.out.println(creature.getAgility());
        creature.setAgility(12);
        System.out.println(creature.getAgility());
    }
}

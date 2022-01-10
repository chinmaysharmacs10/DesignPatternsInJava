package Iterator;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class Creature implements Iterable<Integer>{

    private int[] stats = new int[3];

    public int getStrength() { return stats[0]; }
    public void setStrength(int strength) { stats[0] = strength; }

    public int getAgility() { return stats[1]; }
    public void setAgility(int agility) { stats[0] = agility; }

    public int getSpeed() { return stats[2]; }
    public void setSpeed(int speed) { stats[2] = speed; }

    public int sum(){
        return IntStream.of(stats).sum();
    }

    public int max(){
        return IntStream.of(stats).max().getAsInt();
    }

    public double average(){
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for(int stat : stats){
            action.accept(stat);
        }
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();
    }
}

public class ArrayBackedProperties {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setSpeed(5);
        creature.setStrength(100);
        creature.setAgility(3);

        System.out.println("sum = " + creature.sum());
        System.out.println("max = " + creature.max());
        System.out.println("average = " + creature.average());
    }
}

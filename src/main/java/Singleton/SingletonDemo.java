package Singleton;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingletonDemo {
    // This is just an integration test
    @Test
    public void singletonTotalPopulationTest(){
        SingletonRecordFinder singletonRecordFinder = new SingletonRecordFinder();
        List<String> cities = new ArrayList<>(Arrays.asList("Patna", "Ranchi", "Delhi", "Bangalore"));
        int totalPopulation = singletonRecordFinder.getTotalPopulation(cities);
        System.out.println("totalPopulation = " + totalPopulation);
    }

    // Proper Unit Test
    @Test
    public void dependentPopulationTest(){
        DummyDatabase dummyDatabase = new DummyDatabase();
        ConfigurableRecordFinder configurableRecordFinder = new ConfigurableRecordFinder(dummyDatabase);
        List<String> cities = new ArrayList<>(Arrays.asList("Patna", "Ranchi", "Delhi", "Bangalore"));
        int totalPopulation = configurableRecordFinder.getTotalPopulation(cities);
        System.out.println("totalPopulation = " + totalPopulation);
    }
}

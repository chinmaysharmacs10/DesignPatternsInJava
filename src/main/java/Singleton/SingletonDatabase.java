package Singleton;

import com.google.common.collect.Iterables;
import jdk.nashorn.internal.codegen.DumpBytecode;
import lombok.Getter;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

interface Database{
    int getPopulation(String city);
}

@Getter
public class SingletonDatabase implements Database{

    private static int instanceCount = 0;
    private static HashMap<String, Integer> cities = new HashMap<>();

    private SingletonDatabase(){
        instanceCount++;
        System.out.println("Initializing Database");

        try {
            File file = new File(
                    SingletonDatabase.class.getProtectionDomain()
                            .getCodeSource().getLocation().getPath()
            );
            Path fullPath = Paths.get(file.getPath(), "C:\\Users\\chinm\\IdeaProjects\\DesignPatternsCourse\\src\\main\\java\\Singleton\\CityData.txt");
            List<String> lines = Files.readAllLines(fullPath);
            Iterables.partition(lines,2)
                    .forEach(kv -> cities.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))
                    ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance(){
        return INSTANCE;
    }

    @Override
    public int getPopulation(String city){
        return cities.get(city);
    }

}

class DummyDatabase implements Database{

    private HashMap<String, Integer> dummyData = new HashMap<>();

    public DummyDatabase(){
        dummyData.put("Patna",1);
        dummyData.put("Ranchi",1);
        dummyData.put("Delhi",1);
        dummyData.put("Bangalore",1);
    }

    @Override
    public int getPopulation(String city) {
        return dummyData.get(city);
    }
}

class SingletonRecordFinder{
    public int getTotalPopulation(List<String> cities){
        int totalPopulation = 0;
        for(String city : cities){
            totalPopulation += SingletonDatabase.getInstance().getPopulation(city);
        }
        return totalPopulation;
    }
}


// This depends on an abstraction (Database) so it does not violate DIP
class ConfigurableRecordFinder{
    private Database database;

    public ConfigurableRecordFinder(Database database){
        this.database = database;
    }

    public int getTotalPopulation(List<String> cities){
        int totalPopulation = 0;
        for(String city : cities){
            totalPopulation += database.getPopulation(city);
        }
        return totalPopulation;
    }
}

package SOLID;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal{
    private List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String entry){
        entries.add("" + (++count) + ": " + entry);
    }

    public void removeEntry(int index){
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence{
    public void saveToFile(Journal journal, String fileName, boolean override) throws FileNotFoundException {
        if (override || new File(fileName).exists()){
            try (PrintStream out = new PrintStream(fileName)){
                out.println(toString());
            }
        }
    }
}

public class SingleResponsibilityPrinciple {
    public static void main(String[] args) throws Exception {
        Journal journal = new Journal();
        journal.addEntry("I ate today");
        journal.addEntry("I slept today");
        System.out.println(journal);

        Persistence persistence = new Persistence();
        persistence.saveToFile(journal, "C:\\Users\\chinm\\OneDrive\\Desktop\\journal.txt", false);
    }
}

package Flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User{
    private String fullName;

    public User(String fullName){
        this.fullName = fullName;
    }
}

class User2{
    static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2(String fullName) {
        Function<String,Integer> getOrAdd = (String s) -> {
            int index = strings.indexOf(s);
            if (index != -1){
                return index;
            } else {
                strings.add(s);
                return strings.size() - 1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(s -> getOrAdd.apply(s))
                .toArray();
    }
}

public class RepeatingUserNames {
    public static void main(String[] args) {
        User user1 = new User("John Smith");
        User user2 = new User("Jane Smith");
    }
}

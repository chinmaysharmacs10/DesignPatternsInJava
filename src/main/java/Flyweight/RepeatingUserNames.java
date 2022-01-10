package Flyweight;

class User{
    private String fullName;

    public User(String fullName){
        this.fullName = fullName;
    }
}

public class RepeatingUserNames {
    public static void main(String[] args) {
        User user1 = new User("John Smith");
        User user2 = new User("Jane Smith");
    }
}

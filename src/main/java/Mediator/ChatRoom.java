package Mediator;

import java.util.ArrayList;
import java.util.List;

class Person{
    public String name;
    public ChatRoom chatRoom;
    private List<String> chatLog = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void receiveMessage (String sender, String message) {
        String str = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + str);
        chatLog.add(str);
    }

    public void say (String message) {
        chatRoom.broadcast(name, message);
    }

    public void privateMessage(String receiver, String message){
        chatRoom.message(name, receiver, message);
    }
}

// ChatRoom is the Mediator between 2 Person objects
// Person objects communicate via the ChatRoom
public class ChatRoom {
    private List<Person> people = new ArrayList<>();

    public void join (Person person) {
        if (people.contains(person)) {
            System.out.println(person.name + ": You are already in this chat room");
        } else {
            String joiningMessage = person.name + " joined the chat room";
            broadcast("room", joiningMessage);
            person.chatRoom = this;
            people.add(person);
        }
    }

    public void remove(Person person){
        String removeMessage = person.name + " was removed from the chat room";
        broadcast("room", removeMessage);
        people.remove(person);
    }

    public void broadcast (String sender, String message) {
        for (Person person : people) {
            if (!person.name.equals(sender)) {
                person.receiveMessage(sender, message);
            }
        }
    }

    public void message (String sender, String receiver, String message) {
        people.stream()
                .filter(person -> person.name.equals(receiver))
                .findFirst()
                .ifPresent(person -> person.receiveMessage(sender, message));
    }
}

class demo{
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        Person john = new Person("John");
        Person jane = new Person("Jane");

        chatRoom.join(john);
        chatRoom.join(john);
        chatRoom.join(jane);

        john.say("Heya fuckers!");
        jane.say("Wassup homies");

        Person simon = new Person("Simon");
        chatRoom.join(simon);
        simon.say("Howdy niggas");

        jane.privateMessage(simon.name, "wanna come over?");
        chatRoom.remove(simon);
    }
}

package SOLID;

import org.javatuples.Triplet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum Relationship{
    PARENT,
    CHILD,
    SIBLING
}

class Person{
    public String name;

    public Person(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

interface RelationshipBrowser{
    List<Person> findAllChildren(String name);
}

// Low-level module
class Relationships implements RelationshipBrowser{
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    // data should not be given public access
    /*public List<Triplet<Person, Relationship, Person>> getRelations(){
        return relations;
    }*/

    public void addParentAndChild(Person parent, Person child){
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildren(String name) {
        return relations.stream().filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

// High-level module as it allows of get data from a module
class Research{
    /* public SOLID.Research(SOLID.Relationships relationships){
        List<Triplet<SOLID.Person, SOLID.Relationship, SOLID.Person>> relations = relationships.getRelations();
        relations.stream().filter(x -> x.getValue0().name.equals("John") && x.getValue1() == SOLID.Relationship.PARENT)
                .forEach(ch -> System.out.println("John has a child named " + ch.getValue2().name));
    }*/

    public Research(RelationshipBrowser relationshipBrowser, String name){
        List<Person> children = relationshipBrowser.findAllChildren(name);
        for(Person child : children){
            System.out.println(name + " has a child named " + child);
        }
    }
}

public class DependencyInversionPrinciple {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Wick");
        Person child2 = new Person("Snow");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        Research research = new Research(relationships, "John");
    }
}

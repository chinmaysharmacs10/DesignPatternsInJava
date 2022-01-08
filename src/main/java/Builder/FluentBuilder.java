package Builder;

class Person{
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Builders.Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<SELF extends PersonBuilder<SELF>>{
    protected Person person = new Person();

    public SELF withName(String name){
        person.name = name;
        return self();
    }

    public Person build(){
        return person;
    }

    // So that we can override the behavior of self in derived classes
    protected SELF self(){
        return (SELF) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder>{

    public EmployeeBuilder worksAt(String position){
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class FluentBuilder {
    public static void main(String[] args) {
        /*Builders.PersonBuilder personBuilder = new Builders.PersonBuilder();
        Builders.Person john = personBuilder.withName("John").build();
        System.out.println(john);*/

        EmployeeBuilder employeeBuilder = new EmployeeBuilder();
        Person person = employeeBuilder.withName("John").worksAt("Google").build();
        System.out.println(person);
    }
}

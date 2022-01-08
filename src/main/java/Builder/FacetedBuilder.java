package Builder;

class Person1{

    // address info
    public String address;
    public String postcode;
    public String city;

    // employment info
    public String companyName;
    public String position;
    public int income;

    @Override
    public String toString() {
        return "Builders.Person1{" +
                "address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", income=" + income +
                '}';
    }
}

// Builders.Builder Facade
class Person1Builder{
    protected Person1 person = new Person1();

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works(){
        return new PersonJobBuilder(person);
    }

    public Person1 build(){
        return person;
    }
}

class PersonAddressBuilder extends Person1Builder{

    public PersonAddressBuilder(Person1 person){
        this.person = person;
    }

    public PersonAddressBuilder address(String address){
        person.address = address;
        return this;
    }

    public PersonAddressBuilder postcode(String postcode){
        person.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder city(String city){
        person.city = city;
        return this;
    }
}

class PersonJobBuilder extends Person1Builder{

    public PersonJobBuilder(Person1 person){
        this.person = person;
    }

    public PersonJobBuilder companyName(String companyName){
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder position(String position){
        person.position = position;
        return this;
    }

    public PersonJobBuilder income(int income){
        person.income = income;
        return this;
    }
}

public class FacetedBuilder {
    public static void main(String[] args) {
        Person1Builder person1Builder = new Person1Builder();
        Person1 person = person1Builder
                .lives()
                    .address("B3/6")
                    .postcode("800023")
                    .city("Patna")
                .works()
                    .companyName("Google")
                    .position("SDE")
                    .income(1000000)
                .build();
        System.out.println(person);
    }
}

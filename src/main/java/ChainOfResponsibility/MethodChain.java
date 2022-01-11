package ChainOfResponsibility;

class Creature{
    public String name;
    public int attack, defence;

    public Creature(String name, int attack, int defence){
        this.name = name;
        this.attack = attack;
        this.defence = defence;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defence=" + defence +
                '}';
    }
}

class CreatureModifier{
    protected Creature creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature creature){
        this.creature = creature;
    }

    public void add(CreatureModifier creatureModifier){
        if(next == null){
            next = creatureModifier;
        } else {
            next.add(creatureModifier);
        }
    }

    public void handle(){
        if(next != null){
            next.handle();
        }
    }
}

class DoubleAttackModifier extends CreatureModifier{

    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle(){
        System.out.println("Doubling " + creature.name + "'s attack");
        creature.attack *= 2;
        super.handle();
    }
}

class IncreaseDefence extends CreatureModifier{

    public IncreaseDefence(Creature creature) {
        super(creature);
    }

    @Override
    public void handle(){
        System.out.println("Increasing " + creature.name + "'s defence");
        creature.defence += 3;
        super.handle();
    }
}

class NoBonusesModifier extends CreatureModifier{

    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle(){
        System.out.println("No bonuses for you");
    }
}

public class MethodChain {
    public static void main(String[] args) {
        Creature goblin = new Creature("goblin",2,2);
        System.out.println("goblin = " + goblin);

        CreatureModifier root = new CreatureModifier(goblin);

        root.add(new NoBonusesModifier(goblin));

        System.out.println("Let's double goblin's attack");
        root.add(new DoubleAttackModifier(goblin));

        System.out.println("Let's increase goblin's defence");
        root.add(new IncreaseDefence(goblin));

        root.handle();
        System.out.println("goblin = " + goblin);
    }
}

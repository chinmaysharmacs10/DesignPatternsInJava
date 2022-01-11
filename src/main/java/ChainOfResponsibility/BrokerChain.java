package ChainOfResponsibility;

import lombok.val;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<Args>{
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler){
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key){
        handlers.remove(key);
    }

    public void fire(Args args){
        for(Consumer<Args> handler : handlers.values()){
            handler.accept(args);
        }
    }
}

class Query{
    public String creatureName;
    enum Argument{
        ATTACK,
        DEFENCE
    }
    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result){
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

// Mediator
class Game{
    public Event<Query> queries = new Event<>();
}

class NewCreature{
    private Game game;
    public String name;
    public int baseAttack, baseDefence;

    public NewCreature(Game game, String name, int baseAttack, int baseDefence){
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefence = baseDefence;
    }

    int getAttack(){
        Query query = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(query);
        return query.result;
    }

    int getDefence(){
        Query query = new Query(name, Query.Argument.DEFENCE, baseDefence);
        game.queries.fire(query);
        return query.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "game=" + game +
                ", name='" + name + '\'' +
                ", baseAttack=" + getAttack() +
                ", baseDefence=" + getDefence() +
                '}';
    }
}

class NewCreatureModifier{
    protected Game game;
    protected NewCreature creature;

    public NewCreatureModifier(Game game, NewCreature creature){
        this.game = game;
        this.creature = creature;
    }
}

class NewDoubleAttackModifier extends NewCreatureModifier implements AutoCloseable{

    private final int token;

    public NewDoubleAttackModifier(Game game, NewCreature creature) {
        super(game, creature);
        token = game.queries.subscribe(q -> {
            if(q.creatureName.equals(creature.name) && q.argument == Query.Argument.ATTACK){
                q.result *= 2;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

class NewIncreaseDefence extends NewCreatureModifier {

    public NewIncreaseDefence(Game game, NewCreature creature) {
        super(game, creature);

        game.queries.subscribe(q -> {
            if(q.creatureName.equals(creature.name) && q.argument == Query.Argument.DEFENCE){
                q.result += 3;
            }
        });
    }
}

public class BrokerChain {
    public static void main(String[] args) {
        Game game = new Game();
        NewCreature goblin = new NewCreature(game,"Goblin", 2, 2);
        System.out.println("goblin = " + goblin);

        NewIncreaseDefence newIncreaseDefence = new NewIncreaseDefence(game, goblin);
        System.out.println("goblin = " + goblin);

        NewDoubleAttackModifier newDoubleAttackModifier = new NewDoubleAttackModifier(game, goblin);
        System.out.println("goblin = " + goblin);
    }
}

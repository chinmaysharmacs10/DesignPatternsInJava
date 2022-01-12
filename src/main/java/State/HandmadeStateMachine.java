package State;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

enum State1 {
    OFF_HOOK, // starting state
    ON_HOOK, // terminal state
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Trigger{
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

class Rules{
    public static Map<State1, List<Pair<Trigger, State1>>> rules = new HashMap<>();

    static {
        rules.put(State1.OFF_HOOK, new ArrayList<>(Arrays.asList(
                new Pair<>(Trigger.CALL_DIALED, State1.CONNECTING),
                new Pair<>(Trigger.STOP_USING_PHONE, State1.ON_HOOK)
        )));
        rules.put(State1.CONNECTING, new ArrayList<>(Arrays.asList(
                new Pair<>(Trigger.HUNG_UP, State1.OFF_HOOK),
                new Pair<>(Trigger.CALL_CONNECTED, State1.CONNECTED)
        )));
        rules.put(State1.CONNECTED, new ArrayList<>(Arrays.asList(
                new Pair<>(Trigger.LEFT_MESSAGE, State1.OFF_HOOK),
                new Pair<>(Trigger.HUNG_UP, State1.OFF_HOOK),
                new Pair<>(Trigger.PLACED_ON_HOLD, State1.ON_HOLD)
        )));
        rules.put(State1.ON_HOLD, new ArrayList<>(Arrays.asList(
                new Pair<>(Trigger.TAKEN_OFF_HOLD, State1.CONNECTED),
                new Pair<>(Trigger.HUNG_UP, State1.OFF_HOOK)
        )));
    }

    public static State1 currentState = State1.OFF_HOOK;
    public static State1 exitState = State1.ON_HOOK;
}

public class HandmadeStateMachine {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("The phone is currently: " + Rules.currentState);
            System.out.println("Select a trigger:");

            for (int i=0; i<Rules.rules.get(Rules.currentState).size(); i++){
                Trigger trigger = Rules.rules.get(Rules.currentState).get(i).getKey();
                System.out.println("" + i + ". " + trigger);
            }

            boolean parseOk;
            int choice = 0;
            do{
                try{
                    System.out.println("Please enter a choice:");
                    choice = Integer.parseInt(bufferedReader.readLine());
                    parseOk = choice >= 0 && choice < Rules.rules.get(Rules.currentState).size();
                } catch (Exception e){
                    parseOk = false;
                    e.printStackTrace();
                }
            } while (!parseOk);

            Rules.currentState = Rules.rules.get(Rules.currentState).get(choice).getValue();
            if (Rules.currentState == Rules.exitState){
                break;
            }
        }
        System.out.println("We are done!");
    }
}

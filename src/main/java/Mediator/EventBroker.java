package Mediator;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

import java.util.ArrayList;
import java.util.List;

public class EventBroker extends Observable<Integer> {
    private List<Observer<? super Integer>> observers = new ArrayList<>();

    @Override
    protected void subscribeActual(@NonNull Observer<? super Integer> observer) {
        observers.add(observer);
    }

    public void publish(int n) {
        for (Observer<? super Integer> observer : observers) {
            observer.onNext(n);
        }
    }
}

class FootballPlayer{
    private int goalsScored = 0;
    private EventBroker eventBroker;
    public String name;

    public FootballPlayer(String name, EventBroker eventBroker) {
        this.name = name;
        this.eventBroker = eventBroker;
    }

    public void score() {
        eventBroker.publish(++goalsScored);
    }
}

class FootballCoach{
    public FootballCoach(EventBroker eventBroker) {
        eventBroker.subscribe(i -> {
            System.out.println("Hey, you scored " + i + " goals!");
        });
    }
}

class Demo{
    public static void main(String[] args) {
        EventBroker eventBroker = new EventBroker();
        FootballPlayer messi = new FootballPlayer("Messi", eventBroker);
        FootballCoach coach = new FootballCoach(eventBroker);

        messi.score();
        messi.score();
        messi.score();
    }
}

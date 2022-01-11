package Composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

interface SomeNeuron extends Iterable<Neuron>{
    default void connectTo(SomeNeuron other){
        if(this == other){
            return;
        }

        for(Neuron from : other){
            for(Neuron to : this){
                from.out.add(to);
                to.out.add(from);
            }
        }
    }
}

class Neuron implements SomeNeuron{
    public ArrayList<Neuron> in, out;

    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeuron{

}

public class NeuralNetwork {
    public static void main(String[] args) {
        Neuron neuron = new Neuron();
        Neuron neuron2 = new Neuron();
        NeuronLayer layer = new NeuronLayer();
        NeuronLayer layer2 = new NeuronLayer();

        neuron.connectTo(neuron2);
        neuron.connectTo(layer);
        layer.connectTo(neuron2);
        layer.connectTo(layer2);
    }
}

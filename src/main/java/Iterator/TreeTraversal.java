package Iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Node<T>{
    public T value;
    public Node<T> left, right, parent;

    public Node(T value){
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right){
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }
}

class InOrderIterator<T> implements Iterator<T>{

    private Node<T> current, root;
    private boolean yieldedStart;

    public InOrderIterator(Node<T> root){
        this.root = current = root;

        while(current.left != null){
            current = current.left;
        }
    }

    private boolean hasRightmostParent(Node<T> node){
        if(node.parent == null){
            return false;
        } else {
            return (node == node.parent.left || hasRightmostParent(node.parent));
        }
    }

    @Override
    public boolean hasNext() {
        return (current.left != null || current.right != null || hasRightmostParent(current));
    }

    @Override
    public T next() {
        if(!yieldedStart){
            yieldedStart = true;
            return current.value;
        }

        if(current.right != null){
            current = current.right;
            while(current.left != null){
                current = current.left;
            }
            return current.value;
        }
        else{
            Node<T> node = current.parent;
            while (node != null && current == node.right){
                current = node;
                node = node.parent;
            }
            current = node;
            return current.value;
        }
    }
}

class BinaryTree<T> implements Iterable<T>{

    private Node<T> root;

    public BinaryTree(Node<T> root){
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<T>(root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for(T item : this){
            action.accept(item);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}

public class TreeTraversal {
    public static void main(String[] args) {
        //   1
        //  / \
        // 2   3

        Node<Integer> root = new Node<Integer>(1, new Node<Integer>(2), new Node<Integer>(3));
        InOrderIterator inOrderIterator = new InOrderIterator(root);
        while(inOrderIterator.hasNext()){
            System.out.print("" + inOrderIterator.next() + ",");
        }
        System.out.println();

        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(root);
        for(int n : binaryTree){
            System.out.print("" + n + ",");
        }
    }
}

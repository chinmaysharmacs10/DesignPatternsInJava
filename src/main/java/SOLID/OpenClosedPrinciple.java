package SOLID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

enum Color{
    GREEN,
    RED,
    BLUE
}

enum Size{
    SMALL,
    MEDIUM,
    LARGE
}

class Product{
    String name;
    Color color;
    Size size;

    public Product(String name, Color color, Size size){
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter{
    public Stream<Product> filterByColor(List<Product> products, Color color){
        return products.stream().filter(product -> product.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return products.stream().filter(product -> product.size == size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products, Color color, Size size){
        return products.stream().filter(product -> product.color == color && product.size == size);
    }
}

interface Specification<T>{
    public boolean isSatisfied(T item);
}

interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> specification);
}

class ColorSpecification implements Specification<Product>{
    private Color color;

    public ColorSpecification(Color color){
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product product){
        return product.color == color;
    }
}

class SizeSpecification implements Specification<Product>{
    private Size size;

    public SizeSpecification(Size size){
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product product){
        return product.size == size;
    }
}

class AndSpecification<T> implements Specification<T>{
    private Specification<T> specification1, specification2;

    public AndSpecification(Specification<T> specification1, Specification<T> specification2){
        this.specification1 = specification1;
        this.specification2 = specification2;
    }

    @Override
    public boolean isSatisfied(T item) {
        return specification1.isSatisfied(item) && specification2.isSatisfied(item);
    }
}

class BetterFilter implements Filter<Product>{
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(specification::isSatisfied);
    }
}

public class OpenClosedPrinciple {
    public static void main(String[] args) {
        Product apple = new Product("apple", Color.RED, Size.SMALL);
        Product tree = new Product("tree", Color.GREEN, Size.MEDIUM);
        Product leaf = new Product("leaf", Color.GREEN, Size.SMALL);
        Product house = new Product("house", Color.BLUE, Size.LARGE);

        List<Product> products = new ArrayList<>(Arrays.asList(apple, tree, leaf, house));

        ProductFilter productFilter = new ProductFilter();
        // productFilter.filterByColor(products, SOLID.Color.GREEN).forEach(p -> System.out.println(p.name));

        BetterFilter betterFilter = new BetterFilter();
        // betterFilter.filter(products, new SOLID.ColorSpecification(SOLID.Color.GREEN)).forEach(p -> System.out.println(p.name));
        betterFilter.filter(products, new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE))).forEach(p -> System.out.println(p.name));
    }
}

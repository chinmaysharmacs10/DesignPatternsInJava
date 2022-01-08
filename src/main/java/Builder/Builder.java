package Builder;

import java.util.ArrayList;
import java.util.Collections;

class HtmlElement{
    public String name;
    public String text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement(){
    }

    public HtmlElement(String name, String text){
        this.name = name;
        this.text = text;
    }

    public void addElement(HtmlElement htmlElement){
        elements.add(htmlElement);
    }

    public String toStringImplementation(int indent){
        StringBuilder str = new StringBuilder();
        String s = String.join("", Collections.nCopies(indent*indentSize," "));
        str.append(String.format("%s<%s>%s",s,name,newLine));
        if (text!=null && !text.isEmpty()){
            str.append(String.join("",Collections.nCopies(indentSize*(indent+1)," ")))
                    .append(text)
                    .append(newLine);
        }

        for(HtmlElement element : elements){
            str.append(element.toStringImplementation(indent+1));
        }

        str.append(String.format("%s<%s>%s",s,name,newLine));
        return str.toString();
    }

    @Override
    public String toString(){
        return toStringImplementation(0);
    }
}

class HtmlBuilder{
    private String rootName;
    private HtmlElement htmlElement = new HtmlElement();

    public HtmlBuilder(String rootName){
        this.rootName = rootName;
        htmlElement.name = rootName;
    }

    // FLUENT BUILDER
    // in StringBuilder the return type of sb.append() is StringBuilder itself
    // therefore we can add multiple appends --> sb.append().append()
    public HtmlBuilder addChild(String name, String text){
        HtmlElement e = new HtmlElement(name, text);
        htmlElement.elements.add(e);
        return this;
    }

    public void clear(){
        htmlElement = new HtmlElement();
        htmlElement.name = rootName;
    }

    @Override
    public String toString() {
        return htmlElement.toString();
    }
}

public class Builder {

    public static void main(String[] args) {
        HtmlBuilder htmlBuilder = new HtmlBuilder("ul");
        htmlBuilder.addChild("li","hello").addChild("li","world");
        System.out.println(htmlBuilder);
    }
}

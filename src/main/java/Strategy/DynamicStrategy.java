package Strategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum OutputFormat{
    MARKDOWN,
    HTML
}

interface ListStrategy{
    default void start(StringBuilder stringBuilder){}
    void addListItem(StringBuilder stringBuilder, String item);
    default void end(StringBuilder stringBuilder){}
}

class MarkdownListStrategy implements ListStrategy{

    @Override
    public void addListItem(StringBuilder stringBuilder, String item) {
        stringBuilder.append(" * ").append(item).append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy{

    @Override
    public void start(StringBuilder stringBuilder) {
        stringBuilder.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder stringBuilder, String item) {
        stringBuilder.append(" <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder stringBuilder) {
        stringBuilder.append("</ul>").append(System.lineSeparator());
    }
}

class TextProcessor{
    private StringBuilder stringBuilder = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(OutputFormat outputFormat){
        setOutputFormat(outputFormat);
    }

    public void setOutputFormat(OutputFormat outputFormat){
        switch (outputFormat) {
            case MARKDOWN:
                listStrategy = new MarkdownListStrategy();
                break;
            case HTML:
                listStrategy = new HtmlListStrategy();
                break;
        }
    }

    public void appendList(List<String> items) {
        listStrategy.start(stringBuilder);
        for (String item : items) {
            listStrategy.addListItem(stringBuilder, item);
        }
        listStrategy.end(stringBuilder);
    }

    public void clear(){
        stringBuilder.setLength(0);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}

public class DynamicStrategy {
    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor(OutputFormat.MARKDOWN);
        textProcessor.appendList(new ArrayList<>(Arrays.asList("hello","world","its","me")));
        System.out.println(textProcessor);

        textProcessor.clear();
        textProcessor.setOutputFormat(OutputFormat.HTML);
        textProcessor.appendList(new ArrayList<>(Arrays.asList("hola","amigos","espanyol")));
        System.out.println(textProcessor);
    }
}

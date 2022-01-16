package Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

class TextProcessorStatic<LS extends ListStrategy> {
    private StringBuilder stringBuilder = new StringBuilder();
    private LS listStrategy;

    public TextProcessorStatic(Supplier<? extends LS> constructor){
        listStrategy = constructor.get();
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

public class StaticStrategy {
    public static void main(String[] args) {
        TextProcessorStatic<MarkdownListStrategy> textProcessorStaticMarkdown = new TextProcessorStatic<>(MarkdownListStrategy::new);
        textProcessorStaticMarkdown.appendList(new ArrayList<>(Arrays.asList("hello","world")));
        System.out.println(textProcessorStaticMarkdown);

        TextProcessorStatic<HtmlListStrategy> textProcessorStaticHtml = new TextProcessorStatic<>(HtmlListStrategy::new);
        textProcessorStaticHtml.appendList(new ArrayList<>(Arrays.asList("hola","espanya")));
        System.out.println(textProcessorStaticHtml);
    }
}

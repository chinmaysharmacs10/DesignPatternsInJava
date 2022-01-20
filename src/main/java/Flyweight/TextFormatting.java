package Flyweight;

import java.util.ArrayList;
import java.util.List;

class FormattedText {
    private String plainText;
    private boolean [] capitalize;

    public FormattedText(String plainText) {
        this.plainText = plainText;
        capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end) {
        for (int i=start; i<=end; i++) {
            capitalize[i] = true;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i< plainText.length(); i++) {
            char c = plainText.charAt(i);
            stringBuilder.append(capitalize[i] ? Character.toUpperCase(c) : c);
        }
        return stringBuilder.toString();
    }
}

class BetterFormattedText{
    private String plainText;
    private List<TextRange> formatting = new ArrayList<>();

    public BetterFormattedText(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end) {
        TextRange textRange = new TextRange(start, end);
        formatting.add(textRange);
        return textRange;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<plainText.length(); i++) {
            char c = plainText.charAt(i);
            for (TextRange range : formatting) {
                if (range.covers(i) && range.capitalize) {
                    c = Character.toUpperCase(c);
                }
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public class TextRange{
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean covers(int position) {
            return position >= start && position <= end;
        }
    }
}

public class TextFormatting {
    public static void main(String[] args) {
        FormattedText text = new FormattedText("hello world");
        text.capitalize(2,8);
        System.out.println(text);

        BetterFormattedText newText = new BetterFormattedText("hola espanya");
        newText.getRange(2,8).capitalize = true;
        System.out.println(newText);
    }
}

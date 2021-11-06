package com.matveyakulov.weppageparser.parser.html;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HtmlParser {

    private static final char[] delimiters = new char[]{',', '.', '!', '?', '"', ';', ':',
            '[', ']', '(', ')', '\n', '\r', '\t'};


    public static Map<String, Integer> parseFromURI(String path) {

        Map<String, Integer> result = new ConcurrentHashMap<>();
        try {
            Document context = Jsoup.connect(path).get();

            String bodyText = context.getElementsByTag("body").text();
            bodyText = replaceDelimiters(bodyText);

            String[] words = bodyText.split("\\s+");
            putElemToMap(words, result);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Map<String, Integer> parseText(String path) {

        Map<String, Integer> result = new ConcurrentHashMap<>();

        Document context = Jsoup.parse(path);

        String bodyText = context.text();
        bodyText = replaceDelimiters(bodyText);

        String[] words = bodyText.split("\\s+");
        putElemToMap(words, result);

        return result;
    }

    private static String replaceDelimiters(String line) {

        StringBuilder builder = new StringBuilder(line);

        for(int i = 0; i < builder.length(); i++) {
            for (char delimiter : HtmlParser.delimiters) {
                if (builder.charAt(i) == delimiter) {
                    builder.setCharAt(i, ' ');
                    break;
                }
            }
        }

        return builder.toString();
    }

    private static Map<String, Integer> putElemToMap(String[] elems, Map<String, Integer> mapWords) {

        for (String elem : elems) {
            if (!mapWords.containsKey(elem)) {
                mapWords.put(elem, 0);
            }
            mapWords.put(elem, mapWords.get(elem) + 1);
        }

        return mapWords;
    }

}

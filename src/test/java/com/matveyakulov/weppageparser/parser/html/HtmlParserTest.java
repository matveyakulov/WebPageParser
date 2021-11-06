package com.matveyakulov.weppageparser.parser.html;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class HtmlParserTest extends TestCase {

    public void testParseTextDelimiter(){
        String inText = "http:\n [:][;].]\r,[][]]";
        Map<String, Integer> resultWords = HtmlParser.parseText(inText);
        Map<String, Integer> expectedWords = new HashMap<>();
        expectedWords.put("http", 1);
        Assert.assertEquals(expectedWords, resultWords);
    }

    public void testParseTextCount(){
        String inText = "http:http,http   ;http";
        Map<String, Integer> resultWords = HtmlParser.parseText(inText);
        int resultCount = resultWords.get("http");
        Assert.assertEquals(4, resultCount);
        Assert.assertEquals(1, resultWords.size());
    }

}

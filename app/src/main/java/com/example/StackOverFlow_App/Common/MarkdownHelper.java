package com.example.StackOverFlow_App.Common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarkdownHelper {

    private Map<String,String> specialChars;

    private void fillUpTheSpecialCharsHashMap() {
        specialChars = new HashMap<>();
        specialChars.put("&lt;","<");
        specialChars.put("&gt;",">");
        specialChars.put("&quot;","\"");
        specialChars.put("&nbsp;"," ");
        specialChars.put("&amp;","&");
        specialChars.put("&apos;","'");
        specialChars.put("&#39;","'");
        specialChars.put("&#40;","(");
        specialChars.put("&#41;",")");
        specialChars.put("&#215;","x");
        specialChars.put("&#160;"," ");
    }

    public String handleSpecialChars(String text) {
        if (specialChars == null || specialChars.isEmpty())
            fillUpTheSpecialCharsHashMap();
        Set set=specialChars.entrySet();//Converting to Set so that we can traverse
        for (Object o : set) {
            //Converting to Map.Entry so that we can get key and value separately
            Map.Entry entry = (Map.Entry) o;
            text = text.replace(entry.getKey().toString(),entry.getValue().toString());
        }
        return text;
    }

}
